#pragma once
#include <iostream>
#include <unistd.h>
#include <netinet/in.h>
#include <thread>
#include <csignal>

class Server {
    int options;
    struct sockaddr_in address;
    int addrlen;
public:
    int server_fd;
    int server_socket;
    
    Server(int port);
    void start_listening();
    void start_accepting();
};
