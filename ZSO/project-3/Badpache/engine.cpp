#include "engine.h"
#include "tasks.h"


namespace response {

    std::map<std::string, std::string> mime_types = {
        {"", "application/octet-stream"},
        {"txt", "text/plain"},
        {"css", "text/css"},
        {"js", "application/javascript"},
        {"json", "application/json"},
        {"png", "image/png"},
        {"jpg", "image/jpeg"},
        {"jpeg", "image/jpeg"},
        {"ico", "image/x-icon"},
        {"mp4", "video/mp4"},
        {"html", "text/html"},
        {"mkv", "video/x-matroska"}
    };

    const char* FILE_NOT_FOUND = "HTTP/1.1 404 File not found\r\n"
                            "Content-Type: text/html\r\n"
                            "Server: l33t server\r\n"
                            "\r\n"
                            "<html>\r\n"
                            "\t<head>\r\n"
                            "\t\t<title>404 Not found</title>\r\n"
                            "\t</head>\r\n"
                            "<body>\r\n"
                            "\t<h1>404 Not found</h1>\r\n"
                            "\t<hr>\r\n"
                            "\tWebserver v0.1 ALPHA\r\n"
                            "</body>\r\n"
                            "</html>\r\n\r\n";

    const char* BAD_REQUEST = "HTTP/1.1 400 Bad Request\r\n"
                            "Content-Type: text/html\r\n"
                            "Server: l33t server\r\n"
                            "\r\n"
                            "<html>\r\n"
                            "\t<head>\r\n"
                            "\t\t<title>400 Bad Request</title>\r\n"
                            "\t</head>\r\n"
                            "<body>\r\n"
                            "\t<h1>Bad Request</h1>\r\n"
                            "\t<hr>\r\n"
                            "\tWebserver v0.1 ALPHA\r\n"
                            "</body>\r\n"
                            "</html>\r\n\r\n";

    const char* SERVER_ERROR = "HTTP/1.1 500 Internal Server Error\r\n"
                            "Content-Type: text/html\r\n"
                            "Server: l33t server\r\n"
                            "\r\n"
                            "<html>\r\n"
                            "\t<head>\r\n"
                            "\t\t<title>500 Internal Server Error</title>\r\n"
                            "\t</head>\r\n"
                            "<body>\r\n"
                            "\t<h1>Internal Server Error</h1>\r\n"
                            "\t<hr>\r\n"
                            "\tWebserver v0.1 ALPHA\r\n"
                            "</body>\r\n"
                            "</html>\r\n\r\n";

    const char* OK_DEFAULT = "HTTP/1.1 200 OK\r\n"
                            "Content-Type: text/html\r\n"
                            "Server: l33t server\r\n"
                            "\r\n"
                            "<html>\r\n"
                            "\t<head>\r\n"
                            "\t\t<title>Badpache - Welcome</title>\r\n"
                            "\t</head>\r\n"
                            "<body>\r\n"
                            "\t<h1>Welcome on the best web server ever created!</h1>\r\n"
                            "\t<p>That's Badpache default welcome page, try add your on by adding index.html in root directory</p>\r\n"
                            "\t<hr>\r\n"
                            "\tWebserver v0.1 ALPHA\r\n"
                            "</body>\r\n"
                            "</html>\r\n\r\n";

    const char* OK = "HTTP/1.1 200 OK\r\n"
                     "Server: l33t server\r\n";  
                     
}

namespace request {

    std::vector<std::string> allowed_methods = { "GET", "POST", "HEAD", "PUT", "DELETE", "CONNECT", "OPTIONS", "TRACE" };

    void request_handler(int socket) {
        std::cout << "\n=================================\n\n";
        char* buffer = new char[8*1024];
        memset(buffer, 0, 8*1024);
        try {
            read(socket, buffer, 8*1024);
        } catch (std::exception& ex) {
            std::cout << "[!] Failed to read request, ignoring!\n";
            delete [] buffer;
            return;
        }

        std::string request = buffer;
        //std::cout << "Request buffer:\n" << buffer << std::endl << std::endl;
        std::map<std::string, std::string> request_parsed;
        try {
            parse(request, &request_parsed);
        } catch (const std::exception& ex) {
            printf("[!] Something went wrong while parsing this request:\n%s\n", request.c_str());
            send(socket, response::BAD_REQUEST, std::strlen(response::BAD_REQUEST), 0);
            shutdown(socket, SHUT_RDWR);
            delete [] buffer;
            return;
        }
        for (auto item : request_parsed) {
            std::cout << item.first << " : " << item.second << std::endl;
        }
        delete [] buffer;
        send_response(socket, &request_parsed);
        shutdown(socket, SHUT_RDWR);
    }

    void parse(std::string request, std::map<std::string, std::string>* request_parsed) {
        int separator_offset(request.find("\r\n\r\n"));
        //std::cout << "Request:\n" << request << std::endl << std::endl;
        std::istringstream headers;
        std::string body;
        //std::istringstream body;
        try {
            headers = std::istringstream(request.substr(0, separator_offset));
            body = request.substr(separator_offset + 4, request.size());
            // body = std::istringstream(request.substr(separator_offset + 4, request.size()));
            // std::cout << "Request body : " << "\n|\n" << body.str() << "\n|\n"  << std::endl;
            // std::cout << "Request body :\n" << request.substr(separator_offset + 4, request.size()) << std::endl;
        } catch (const std::out_of_range& e) {
            throw std::exception();
        }

        std::string request_line;
        std::getline(headers, request_line);
        std::istringstream iss(request_line);
        std::vector<std::string> request_line_items(
            std::istream_iterator<std::string>{iss},
            std::istream_iterator<std::string>()
        );

        if (request_line_items.size() != 3) {
            throw std::exception();
        }

        if(std::find(allowed_methods.begin(), allowed_methods.end(), request_line_items.at(0)) == allowed_methods.end()) {
            throw std::exception();
        }

        request_parsed->insert(std::pair<std::string, std::string>("method", request_line_items.at(0)));
        request_parsed->insert(std::pair<std::string, std::string>("request_target", request_line_items.at(1)));
        request_parsed->insert(std::pair<std::string, std::string>("http_version", request_line_items.at(2)));
        request_parsed->insert(std::pair<std::string, std::string>("body", body));

        std::string line;
        while(std::getline(headers, line)) {
            int colon_offset(line.find_first_of(":"));
            request_parsed->insert(
                std::pair<std::string, std::string>(line.substr(0, colon_offset), line.substr(colon_offset + 2, line.size() - 1)
            ));
        } 
    }

    void send_response(int socket, std::map<std::string, std::string>* request) {
        std::string requested_file = request->at("request_target");
        size_t index = 0;
        while ((index = requested_file.find("..")) != std::string::npos) {
            printf("Found '..' at index %lu in requested path: %s\n", index, requested_file.c_str());
            requested_file = requested_file.erase(index, 2);
        }
        while ((index = requested_file.find("//")) != std::string::npos) {
            printf("Found '//' at index %lu in requested path: %s\n", index, requested_file.c_str());
            requested_file = requested_file.replace(index, 2, "/");
        }
        printf("Requested target after sanitization %s\n", requested_file.c_str());

        if (requested_file.rfind("/tasks", 0) == 0) {
            try {
                handle_job_requests(socket, request);
            } catch (const std::exception &e) {
                std::cout << "[!] Something went wrong while handling job request" << std::endl;
                send(socket, response::SERVER_ERROR, strlen(response::SERVER_ERROR), 0);
            }
            return;
        } else if (requested_file == "/") {
            if(exists("index.html")) {
                std::ifstream ifs("index.html");
                std::string content((std::istreambuf_iterator<char>(ifs)),
                                    std::istreambuf_iterator<char>());
                std::stringstream ss;
                ss << response::OK << "Content-Length: " << content.size() << "\r\n\r\n" << content;
                send(socket, ss.str().c_str(), ss.str().size(), 0);
            } else {
                std::string dir_listing = get_directory_listing(".");
                send(socket, dir_listing.c_str(), dir_listing.length(), 0);
            }
            return;
        }

        requested_file.erase(0,1);
        if(exists(requested_file)) {
            if (is_dir(requested_file)) {
                std::string dir_listing = get_directory_listing(requested_file.c_str());
                send(socket, dir_listing.c_str(), dir_listing.length(), 0);
            } else {
                std::ifstream file(requested_file, std::ifstream::binary);
                off_t f_size = get_file_size(&file);

                if(f_size <= 0) {
                    send(socket, response::OK, strlen(response::OK), 0);
                    return;
                }

                std::string response_headers = prepare_headers(&requested_file, &f_size);
                send(socket, response_headers.c_str(), response_headers.size(), 0);
                send_file(&socket, &file, &f_size);
            }
        } else {
            send(socket, response::FILE_NOT_FOUND, strlen(response::FILE_NOT_FOUND), 0);
        }
    }

    std::string get_directory_listing(const char* requested_file) {
        std::string dir_listing = response::OK;
        dir_listing.append("\r\n<html>\r\n\t<head>\r\n\t\t<title>Directory listing</title>\r\n\t</head>\r\n<body>\r\n\t<h1>Directory listing:</h1>\r\n\t<ul>\r\n");

        for (auto& f : std::filesystem::directory_iterator(requested_file)) {
            dir_listing.append("\t\t<li><a href=\"/");
            dir_listing.append(f.path().c_str());
            dir_listing.append("\">");
            dir_listing.append(f.path().filename());
            if (!std::filesystem::is_directory(f)) {
                dir_listing.append(" (");
                std::uintmax_t size = std::filesystem::file_size(f);
                if (size > 1000000) {
                    dir_listing.append(std::to_string(std::filesystem::file_size(f) / 1048576));
                    dir_listing.append("MB)");
                } else {
                    dir_listing.append(std::to_string(std::filesystem::file_size(f)));
                    dir_listing.append("B)");
                }
            }
            dir_listing.append("</a></li>\r\n");
        }

        dir_listing.append("\t<ul>\r\n</body>\r\n</html>\r\n");
        return dir_listing;
    }
    
    uint get_file_size(std::ifstream* file) {
        std::streampos begin, end;
        begin = file->tellg();
        file->seekg(0, std::ios::end);
        end = file->tellg();
        file->seekg(0, std::ios::beg);
        return end - begin;
    }

    std::string prepare_headers(std::string* requested_file, off_t* f_size) {
        std::stringstream ss;
        ss << response::OK;
        ss << "Content-Length: " << *f_size << "\r\n";
        ss << "Content-Type: ";
        size_t ext_offset;
        if ((ext_offset = requested_file->find_last_of('.')) != std::string::npos) {
            std::string file_extension(requested_file->substr(ext_offset + 1, requested_file->size()));
            if (response::mime_types.find(file_extension) != response::mime_types.end()) {
                ss << response::mime_types.at(file_extension);
            } else {
                ss << response::mime_types.at("");
            }
        } else {
            ss << response::mime_types.at("");
        }
        ss << "\r\n\r\n";
        return ss.str();
    }

    void send_file(int* socket, std::ifstream* file, off_t* f_size) {
        off_t bytes_sent = 0;
        off_t bytes_left_to_send = *f_size;
        uint buf_size = (*f_size < 8 * 1024) ? *f_size : 8*1024;
        std::vector<char> buf(8*1024, 0);

        while(file->read(buf.data(), buf_size)) {
            uint buf_size = (*f_size < 8*1024) ? *f_size : 8*1024;
            bytes_sent = send(*socket, buf.data(), buf_size, 0);
            bytes_left_to_send -= bytes_sent;
            printf("bytes_sent: %lu, left_to_sent: %lu\n", bytes_sent, bytes_left_to_send);
            if(bytes_left_to_send < buf_size) {
                buf_size = bytes_left_to_send;
                buf.resize(buf_size);
                file->read(buf.data(), buf_size);
                bytes_sent = send(*socket, buf.data(), buf_size, 0);
                bytes_left_to_send -= bytes_sent;
                printf("bytes_sent: %lu, left_to_sent: %lu\n", bytes_sent, bytes_left_to_send);
            }
        }
    }
    // TODO sending file in repsonse for request with Range header
}
