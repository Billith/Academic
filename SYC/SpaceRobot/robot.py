import serial
import socket
import io
import threading
import SocketServer
from datetime import datetime
from random import randint
from time import sleep


s = serial.serial_for_url('loop://', timeout=1)
console_ip = ''

class threaded_tcp_server(SocketServer.ThreadingMixIn, SocketServer.TCPServer): 
    allow_reuse_address = True
    pass

class tcp_command_handler(SocketServer.BaseRequestHandler):
    def handle(self):
    	global console_ip
        if console_ip == '':
            console_ip = self.request.getpeername()[0]
        command = self.request.recv(1024)
        print '[cmd] %s ' % command
        self.request.sendall('Ok!')

def generate_reads(s):
    sio = io.TextIOWrapper(io.BufferedRWPair(s,s))
    print '[*] Starts generating data'
    while True:
        # format danych: czas|oswietlenie|temperatura|cisnienie|przeszkoda
        sio.write(unicode('%s|%i|%i|%i|%i\n' % (str(datetime.now()), randint(450,700), randint(15,25), randint(950,1100), randint(0,1))))
        sio.flush()
        sleep(1)

def parse_data(data):
    time,lighting_level,temp,pressure,obstacle = data.split('|')
    # Debug
    #print 'czas: %s\npoziom oswietlenia: %s\ntemperatura: %s\ncisnienie: %s\nprzeszkoda: %s' % (time,lighting_level,temp,pressure,obstacle)
    if obstacle.strip() == unicode('1'):
        send_obstacle_info()

def send_obstacle_info():
    if console_ip == '':
        return
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((console_ip,2424))
        s.sendall('Przeszkoda! Procedura omijania wykonana')
    except:
        pass
    finally:
        s.close()

def store_data():
    pass

def start_listener():
    server = threaded_tcp_server(('0.0.0.0', 2323), tcp_command_handler)
    server_thread = threading.Thread(target=server.serve_forever)
    server_thread.start()
    print '[*] Command listener started'



generating_thread = threading.Thread(target=generate_reads, args=(s,))
generating_thread.start()
start_listener()

sio = io.TextIOWrapper(io.BufferedRWPair(s,s))
print '[*] Starts reading from serial loopback'
while True:
    output = sio.readline()
    parse_data(output)

