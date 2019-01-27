import socket
import sys
import os
import threading
import SocketServer
import pprint
import ast

from cmd import Cmd


class Terminal(Cmd):
    prompt = 'console # '

    def __init__(self, server, robot_ip):
        Cmd.__init__(self)
        self.server = server
        self.robot_ip = robot_ip

    def do_exit(self, args):
        self.server.shutdown()
        return True

    def do_ping(self, args):
        send_command('ping', self.robot_ip)

    def do_back(self, args):
        send_command('back', self.robot_ip)

    def do_sendall(self, args):
        send_command('sendall', self.robot_ip)

    def do_set(self, args):
        params = args.strip().split()
        if len(params) == 2 and params[0] == 'mf':
            send_command('set mf' + params[1], self.robot_ip)
        elif len(params) == 2 and params[0] == 'mt':
            send_command('set mt' + params[1], self.robot_ip)
        else:
            self.default('set ' + args)

    def emptyline(self):
        pass

    def default(self, args):
        print "[!] error: unknown command: '%s'\nuse 'help' to see all available commands" % args

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

def send_command(cmd, ip):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        s.connect((ip,2323))
        s.sendall(cmd)
        if cmd == 'sendall':
            pprint.pprint(ast.literal_eval(s.recv(1024 * 1024)))
        else:
            print '[answer] %s' % s.recv(1024 * 1024)
    except:
        pass
    finally:
        s.close()

def send_hello_packet(ip):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(5)
    try:
        s.connect((ip,2323))
        s.sendall('hello')
        return s.recv(1024 * 1024)
    except:
        return ''
    finally:
        s.close()

def start_listener():
    server = threaded_tcp_server(('0.0.0.0', 2424), tcp_command_handler)
    server_thread = threading.Thread(target=server.serve_forever)
    server_thread.start()
    return server

    
server = start_listener()
res = ''
while res == '':
    ip = raw_input("[o] Enter robot's IP: ")
    res = send_hello_packet(ip)
    if res == '':
        print '[!] Cannot connect to the robot! Check ip and try again.'

terminal = Terminal(server, ip)
terminal.cmdloop()