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
    print '[*] Robot responses listener started'


ip = raw_input("[o] Enter robot's IP: ")
start_listener()
while True:
    cmd = raw_input('console # ')
    if cmd.lower() == 'quit' or cmd.lower() == 'exit':
        server.shutdown()
        sys.exit(0)
    elif not cmd:
        continue
    elif cmd == 'verbose off':
    	global verbose
    	verbose = False
    	continue
    elif cmd == 'verbose on':
    	global verbose
    	verbose = True
    	continue
    elif cmd == 'back':
    	pass
    elif cmd == 'sendall':
    	pass
    else:
    	print "[!] error: unknown command: '%s'" % cmd
    	continue
    send_command(cmd, (ip,2323))
