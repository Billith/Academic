import serial
import socket
import io
import threading
import SocketServer
import sys
import json

from datetime import datetime
from random import randint
from time import sleep


serial_interface = serial.serial_for_url('loop://', timeout=1)
#serial_interface = serial.Serial('/dev/ttyS0')
console_ip = ''
storage = {}
measurements = 0

class threaded_tcp_server(SocketServer.ThreadingMixIn, SocketServer.TCPServer): 
    allow_reuse_address = True
    pass

class tcp_command_handler(SocketServer.BaseRequestHandler):
    def handle(self):
    	global console_ip
    	global storage
        if console_ip == '':
            console_ip = self.request.getpeername()[0]
        command = self.request.recv(1024)
        print '[cmd] %s ' % command
        if command == 'sendall':
            send_measurement(json.dumps(storage))
        elif command == 'back':
        	self.request.sendall('Ok! Comming back')

def generate_measurements(s):
    sio = io.TextIOWrapper(io.BufferedRWPair(s,s))
    print '[*] Starts generating data'
    while True:
        # format danych: czas|oswietlenie|temperatura|cisnienie|przeszkoda
        sio.write(unicode('%s|%i|%i|%i|%i\n' % (
        	str(datetime.now()), 
        	randint(450,700), 
        	randint(15,25), 
        	randint(950,1100), 
        	randint(0,1)))
        )
        sio.flush()
        sleep(1)

def parse_data(data):
    global measurements
    global storage
    measurements += 1
    time,lighting_level,temp,pressure,obstacle = data.strip().split('|')
    store_data(time,lighting_level,temp,pressure,obstacle)
    if obstacle.strip() == unicode('1'):
        send_obstacle_info()
    if measurements % 10 == 0:
    	send_measurement(storage[measurements])
    	print '[*] Measurement sent!'

def send_obstacle_info():
	# przesylanie informacji nie zacznie sie dopoki z konsoli nie 
	# zostanie wyslany dowolny, komunikat (przypisanie IP konsoli)
    global console_ip
    if console_ip == '':
        return
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((console_ip,2424))
        s.sendall('Przeszkoda! Procedura omijania wykonana')
    except:
        print '[!] Failed to send information about obstacle'
        print sys.exc_info()[0]
    finally:
        s.close()

def send_measurement(data):
    global console_ip
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((console_ip,2424))
        s.sendall(json.dumps(data))
    except:
        print '[!] Failed to send measurement'
        print sys.exc_info()[0]
    finally:
        s.close()

def store_data(time,lighting_level,temp,pressure,obstacle):
    global storage
    global measurements
    storage[measurements] = {
    	'time'				: time,
    	'lighting_level'	: lighting_level,
    	'temp'				: temp,
    	'pressure'			: pressure,
    	'obstacle'			: obstacle,
    }

def start_listener():
    server = threaded_tcp_server(('0.0.0.0', 2323), tcp_command_handler)
    server_thread = threading.Thread(target=server.serve_forever)
    server_thread.start()
    print '[*] Command listener started'


generating_thread = threading.Thread(target=generate_measurements, args=(serial_interface,))
generating_thread.start()
start_listener()

print '[*] Starts reading from serial intereface'
while True:
    output = serial_interface.readline().strip().rstrip('\n\r')
    if output:
        print '[serial] %s' % output
        parse_data(output)
