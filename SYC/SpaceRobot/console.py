import socket
import sys
import threading
import SocketServer


verbose = True

class threaded_tcp_server(SocketServer.ThreadingMixIn, SocketServer.TCPServer):
    allow_reuse_address = True
    pass

class tcp_command_handler(SocketServer.BaseRequestHandler):
    def handle(self):
        command = self.request.recv(1024 * 1024)
        # Ignoruje informacje o przeszkodzie gdy gadatliwy tryb jest wylaczony
        if command == 'Przeszkoda! Procedura omijania wykonana' and not verbose:
            return
        print '\n[robot] %s ' % command


def send_command(cmd, (ip,port)):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((ip,port))
    try:
        s.sendall(cmd)
        print '[answer] %s' % s.recv(1024)
    finally:
        s.close()

def start_listener():
    global server
    server = threaded_tcp_server(('0.0.0.0', 2424), tcp_command_handler)
    server_thread = threading.Thread(target=server.serve_forever)
    server_thread.start()


ip = raw_input("[o] Enter robot's IP: ")
start_listener()
print '[*] Robot responses listener started'
while True:
    cmd = raw_input('console # ')
    cmd_array = cmd.split(' ')
    #print 'debug: ' + str(len(cmd_array))
    if cmd.lower() == 'quit' or cmd.lower() == 'exit':
        server.shutdown()
        sys.exit(0)
    elif cmd == 'verbose off':
    	verbose = False
    	continue
    elif cmd == 'verbose on':
    	verbose = True
    	continue
    elif cmd == 'back' or cmd == 'sendall' or cmd == 'ping':
    	pass
    elif not cmd:
    	continue
    elif cmd == 'help':
        print '\tverbose \t[on/off]\n\tback\n\tsendall\n\tping'
        continue
    elif cmd_array[0] == 'set':
    	if len(cmd_array) != 3:
    		print '[!] error: wrong set command!'
    		continue
    	if cmd_array[1] == 'mf':
    		pass
    	elif cmd_array[1] == 'mt':
    		pass
    	else:
    		print '[!] error: wrong set command!'
    		continue

    else:
    	print "[!] error: unknown command: '%s'\nuse 'help' to see all available commands" % cmd
    	continue
    	
    try:
        send_command(cmd, (ip,2323))
    except:
        pass
