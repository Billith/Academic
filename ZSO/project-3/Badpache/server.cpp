#include "server.h"
#include "engine.h"

Server::Server(int port) : options(1) {
    addrlen = sizeof(address);
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(port);
}

void Server::start_listening() {

    if ((this->server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) {
        perror("[!] Socket creation failed!");
        exit(EXIT_FAILURE);
    }
    if (setsockopt(
            this->server_fd,
            SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT,
            &this->options,
            sizeof(this->options)
        )) {
        perror("[!] Socket's options setting failed!");
        exit(EXIT_FAILURE);   
    }
    if (bind(
            this->server_fd,
            (struct sockaddr*)&this->address,
            this->addrlen
        ) < 0) {
        perror("[!] Socket bind failed!");
        exit(EXIT_FAILURE);    
    }
    if (listen(this->server_fd, 3) < 0) {
        perror("[!] Socket listening failed!");
        exit(EXIT_FAILURE);   
    }

    printf("[*] Started listening on port %i\n", ntohs(this->address.sin_port));
}

void Server::start_accepting() {
    std::cout << "[*] Starting accepting connections\n";
    std::cout << "\n=================================\n\n";

    int server_socket;
    while ((server_socket = accept(
            this->server_fd,
            (struct sockaddr*)(&this->address),
            (socklen_t*)(&this->addrlen)
        )) >= 0
    ) {
        std::thread connection_thread(request::request_handler, server_socket);
        connection_thread.detach();
    }
}

int main() {
    std::unique_ptr<Server> server = std::make_unique<Server>(8080);
    std::signal(SIGPIPE, SIG_IGN); // needed to ignore SIGPIPE (which terminates program) while writing response to socket which is closed
    server->start_listening();
    server->start_accepting();
}
