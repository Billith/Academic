#pragma once
#include <cstring>
#include <string>
#include <sstream>
#include <iostream>
#include <iterator>
#include <fstream>
#include <streambuf>
#include <vector>
#include <map>
#include <netinet/in.h>
#include <unistd.h>
#include <algorithm>
#include <sys/stat.h>
#include <filesystem>


namespace request {
    void request_handler(int socket);
    void parse(std::string request, std::map<std::string, std::string>* request_parsed);
    void send_response(int socket, std::map<std::string, std::string>* request);
    std::string get_directory_listing(const char* requested_file);
    uint get_file_size(std::ifstream*);
    std::string prepare_headers(std::string* requested_file, off_t* f_size);
    void send_file(int* socket, std::ifstream* file, off_t* f_size);
    void send_file(int* socket, std::ifstream* file, off_t start_offset, off_t end_offset);

    inline bool exists(const std::string& name) {
        std::ifstream file(name.c_str());
        return file.good();
    }

    inline bool is_dir(const std::string& path) {
        struct stat buf;
        stat(path.c_str(), &buf);
        return S_ISDIR(buf.st_mode);
    }
}
