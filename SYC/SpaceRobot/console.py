import socket
import sys
import threading
import SocketServer


class threaded_tcp_server(SocketServer.ThreadingMixIn, SocketServer.TCPServer):
    allow_reuse_address = True
    pass

class tcp_command_handler(SocketServer.BaseRequestHandler):
    def handle(self):
        command = self.request.recv(1024)
        sys.stdout.write('\n[robot] %s ' % command)

def send_command(cmd, (ip,port)):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((ip, 2323))
    try:
        s.sendall(cmd)
        print s.recv(1024)
    finally:
        s.close()

def start_listener():
    server = threaded_tcp_server(('0.0.0.0', 2424), tcp_command_handler)
    server_thread = threading.Thread(target=server.serve_forever)
    server_thread.start()
    print '[*] Robot responses listener started'


ip = raw_input('IP: ')
while True:
    cmd = raw_input('console # ')
    if cmd.lower() == 'quit' or cmd.lower() == 'exit':
        server.shutdown()
        sys.exit(0)
    if not cmd:
        continue
    send_command(cmd, (ip,2323))
