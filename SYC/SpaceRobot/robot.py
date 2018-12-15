import serial
import socket
import io
import threading
import SocketServer
import sys
import json
import os

from datetime import datetime
from random import randint
from time import sleep, time


serial_interface = serial.serial_for_url('loop://', timeout=1)
#serial_interface = serial.Serial('/dev/ttyS0')
console_ip = ''
storage = {}
measurements = 0
measurements_frequency = 10
robot_id = 1
mission = True
mission_duration = 600 # w sekundach
mission_start_time = time()

class threaded_tcp_server(SocketServer.ThreadingMixIn, SocketServer.TCPServer): 
    allow_reuse_address = True
    pass

class tcp_command_handler(SocketServer.BaseRequestHandler):
    def handle(self):
    	global console_ip
    	global storage
        global robot_id
        global measurements_frequency
        global mission_duration
        if console_ip == '':
            console_ip = self.request.getpeername()[0]
        command = self.request.recv(1024)
        print '[cmd] %s ' % command
        if command[0:3] == 'set':
        	if command[4:6] == 'mf':
        		measurements_frequency = int(command[6:].strip())
        		print '[!] Measurements frequency changed to ' + command[6:].strip()
        	elif command[4:6] == 'mt':
        		mission_duration = int(command[6:].strip())
        		print '[!] Mission duration changed to ' + command[6:].strip()

        # Przeslanie wszystkich dokonanych do tej pory pomiarow.
        if command == 'sendall':
            send_measurement(json.dumps(storage))\
        # Odebranie komendy powrotu do stacji bazowej.
        elif command == 'back':
        	self.request.sendall('Ok! Comming back')
        elif command == 'ping':
            self.request.sendall('robot #%s => pong' % robot_id)

def generate_measurements(s):
    sio = io.TextIOWrapper(io.BufferedRWPair(s,s))
    while True:
        # format stringa z pomiarami:
        # czas|oswietlenie|temperatura|cisnienie|przeszkoda
        sio.write(unicode('%s|%i|%i|%i|%i\n' % (
        	str(datetime.now()), 
        	randint(450,700), 
        	randint(15,25), 
        	randint(950,1100), 
        	randint(0,1)))
        )
        sio.flush()
        sleep(3)

# Przetwarzanie pomiarow i zapisywanie co 10-tego pomiaru.
# Jesli zostanie napotkana przeszkoda wywolywana jest funkcja
# wysylu informacji o napotkaniu przeszkody i wykonaniu manewru.
def parse_data(data):
    global measurements
    global storage
    global robot_id
    global measurements_frequency
    measurements += 1
    time,lighting_level,temp,pressure,obstacle = data.strip().split('|')
    store_data(time,lighting_level,temp,pressure,obstacle)
    if obstacle.strip() == unicode('1'):
        send_obstacle_info()
    if measurements % measurements_frequency == 0:
        measurement = storage[measurements]
        measurement['robot_id'] = robot_id
    	send_measurement(storage[measurements])
    	print '[*] Measurement sent!'

def send_obstacle_info():
    # przesylanie informacji nie powinno sie zaczac dopoki z konsoli
    # nie zostanie wyslany dowolny, komunikat (przypisanie IP konsoli)
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

# Funckja dodawania pomiarow do lokalnego slownika pomiarow.
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

# Sprawdzanie czy minal okreslony czas misji. Jesli minal robot
# wysyla powiadomienie do konsoli i konczy prace/pomiary.
def check_mission_time():
    global mission
    global mission_start_time
    global console_ip
    global robot_id
    current_time = time()
    elapsed_time = abs(int(mission_start_time - current_time))
    if elapsed_time >= mission_duration:
        mission = False
        try:
            s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            s.connect((console_ip,2424))
            s.sendall('[#%s] [!] Mission ended, going home!' % robot_id)
        except:
            print '[!] Failed to send mission over notification'
            print sys.exc_info()[0]
        finally:
            s.close()
        print '[!] Mission ended, going home!'
        os._exit(0)

def start_listener():
    server = threaded_tcp_server(('0.0.0.0', 2323), tcp_command_handler)
    server_thread = threading.Thread(target=server.serve_forever)
    server_thread.start()

# TODO 'Umozliwic zmiane parametrow (czestosc pomiarow,
# czas misji) robota poprzez komendy ze stacji bazowej.'


generating_thread = threading.Thread(target=generate_measurements, args=(serial_interface,))
print '[*] Starts generating data'
generating_thread.start()
start_listener()
print '[*] Command listener started'
print '[*] Starts reading from serial intereface'
while mission:
    output = serial_interface.readline().strip().rstrip('\n\r')
    if output:
        print '[serial] %s' % output
        parse_data(output)
    check_mission_time()
