#include <sys/types.h>
#include <sys/socket.h>
#include <iostream>
#include <string.h>
#include <iostream>
#include <fstream>
#include <stdio.h>
#include <string>
#include <regex>
#include <list>
#include <map>

#include "tasks.h"


int global_task_id = 1;
std::list<Task*> tasks;

Task::Task(int id, std::string script) {
    this->id = id;
    this->script = script;
    this->status = NOT_RUNNING;
    tasks.push_back(this);
}

void Task::run() {
    std::ofstream tmp_script;

    char buffer[1024];
    sprintf(buffer, "/tmp/%i.sh", this->id);
    tmp_script.open(buffer);
    tmp_script << this->script;
    tmp_script.close();

    // std::array<char, 128> buffer;
    // std::string result;
    // FILE* task_subprocess = popen("", "r");
    // std::unique_ptr<FILE, decltype(&pclose)> pipe("", pclose);
    // if (!pipe) {
    //     throw std::runtime_error("popen() failed!");
    // }
    // while (fgets(buffer.data(), buffer.size(), pipe.get()) != nullptr) {
    //     result += buffer.data();
    // }
    // return result;
}

void handle_job_requests(int socket, std::map<std::string, std::string>* request) {
    std::string target = request->at("request_target");
    if (target == "/tasks") {
        std::cout << "OK" << std::endl;        
        std::string body = request->at("body");
        if (request->at("method") == "POST") {
            std::string ctype = request->at("Content-Type");
            if (ctype.rfind("multipart/form-data; boundary=", 0) == 0) {
                std::string boundary = ctype.substr(ctype.find("boundary=") + 9);
                std::cout << "\nBoundary:\n" << boundary << std::endl;
                std::string script = body.substr(body.find(boundary) + boundary.length());
                script = script.substr(1, script.find(boundary) - 4);
                script = script.substr(script.find("\r\n") + 2); // skip header
                script = script.substr(script.find("\r\n") + 2); // skip header
                script = script.substr(script.find("\r\n") + 2); // skip \r\n
                std::cout << "\nScript:\n" << script << std::endl;
                Task* task = new Task(global_task_id, script);
                task->run();
                global_task_id++;
            }
        }
        
        std::string response = "HTTP/1.1 200 OK\r\n"
            "Server: l33t server\r\n"
            "\r\n"
            "<html>\r\n"
            "\t<form method=\"post\" enctype=\"multipart/form-data\">\r\n"
            "\t\tAdd new job:\r\n"
            "\t\t<input type=\"file\" name=\"fileToUpload\" id=\"fileToUpload\">\r\n"
            "\t\t<input type=\"submit\" value=\"Upload script\" name=\"submit\">\r\n"
            "\t</form>\r\n"
            "\t<b>Tasks:</b></br>\r\n";
        
        for (auto const& task : tasks) {
            response.append(std::to_string(task->id));
            response.append(" | ");
            switch (task->status) {
                case NOT_RUNNING: response.append("not running"); break;
                case RUNNING: response.append("running"); break;
                case FINISHED: response.append("finished"); break;
                case FAILED: response.append("failed"); break;
            }
            response.append(" | ");
            response.append("<a href=\"/tasks/");
            response.append(std::to_string(task->id));
            response.append("\">details</a>");
            response.append("</br>\r\n");
        }
        response.append("</br>\r\n");
        response.append("</html>");
        
        send(socket, response.c_str(), strlen(response.c_str()), 0);
    } else {
        std::regex task_regex("\/tasks\/\\d+\/*$");
        if (std::regex_search(target, task_regex)) {
            int id = 0;
            std::regex digit_regex("(\\d+)");
            auto digit_begin = std::sregex_iterator(target.begin(), target.end(), digit_regex);
            auto digit_end = std::sregex_iterator();
            for (std::sregex_iterator i = digit_begin; i != digit_end; i++) {
                std::smatch match = *i;
                std::string match_str = match.str();
                int res = 0;
                if ((res = atoi(match_str.c_str())) > 0) {
                    id = res;
                    std::cout << "id: " << match_str << std::endl;
                    break;
                }
            }
            std::string response = "HTTP/1.1 200 OK\r\n"
                "Server: l33t server\r\n"
                "\r\n";

            for (auto const& task : tasks) {
                if (task->id == id) {
                    response.append("<html>\r\n");
                    response.append(std::to_string(task->id));
                    response.append("</br>\r\n");
                    switch (task->status) {
                        case NOT_RUNNING: response.append("not running"); break;
                        case RUNNING: response.append("running"); break;
                        case FINISHED: response.append("finished"); break;
                        case FAILED: response.append("failed"); break;
                    }
                    response.append("</br>\r\n");
                    response.append(task->script);
                    response.append("<html>\r\n");
                    response.append("</html>\r\n");
                    break;
                }
            }
            send(socket, response.c_str(), strlen(response.c_str()), 0);
        }
    }
}
