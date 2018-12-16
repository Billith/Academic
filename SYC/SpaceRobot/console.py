import socket
import sys
import os
import threading
import SocketServer
import pprint
import ast


class threaded_tcp_server(SocketServer.ThreadingMixIn, SocketServer.TCPServer):
    allow_reuse_address = True

class tcp_command_handler(SocketServer.BaseRequestHandler):
    def handle(self):
        command = self.request.recv(1024 * 1024)
        if command.startswith('{"'):
            print ''
            pprint.pprint(ast.literal_eval(command))
        else:
            print '\n[robot] %s' % command

def send_command(cmd, (ip,port)):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((ip,port))
    try:
        s.sendall(cmd)
        if cmd == 'sendall':
            pprint.pprint(ast.literal_eval(s.recv(1024 * 1024)))
        else:
        	print '[answer] %s' % s.recv(1024 * 1024)
    finally:
        s.close()

def start_listener():
	server = threaded_tcp_server(('0.0.0.0', 2424), tcp_command_handler)
	server_thread = threading.Thread(target=server.serve_forever)
	server_thread.start()
	return server

    
server = start_listener()
ip = raw_input("[o] Enter robot's IP: ")
print '[*] Robot responses listener started'
while True:
    cmd = raw_input('console # ').strip().lower().split(' ')
    if cmd[0] == '':
    	continue
    elif cmd[0] == 'quit' or cmd[0] == 'exit':
        server.shutdown()
        break
    elif cmd[0] == 'back' or cmd[0] == 'sendall' or cmd[0] == 'ping':
    	pass
    elif cmd[0] == 'help':
        print '   back\n   sendall\n   ping\n   set\t[mf\\mt]\t[value]'
        continue
    elif cmd[0] == 'set' and len(cmd) == 3 and (cmd[1] == 'mf' or cmd[1] == 'mt'):
    	pass
    else:
    	print "[!] error: unknown command: '%s'\nuse 'help' to see all available commands" % ' '.join(cmd)
    	continue
    	
    try:
        send_command(' '.join(cmd), (ip,2323))
    except:
        pass
