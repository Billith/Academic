#include <sys/types.h>
#include <sys/socket.h>
#include <iostream>
#include <string.h>
#include <iostream>
#include <fstream>
#include <stdio.h>
#include <thread>
#include <string>
#include <regex>
#include <array>
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
    this->status = RUNNING;
    char path[256];
    sprintf(path, "/tmp/%i.sh", this->id);
    std::ofstream tmp_script(path, std::ios::binary);
    tmp_script << this->script;
    tmp_script.close();

    char cmd[266];
    sprintf(cmd, "bash %s 2>&1", path);
    FILE* pipe = popen(cmd, "r");
    std::array<char, 128> buffer;
    std::string result;
    if (!pipe) {
        std::cout << "popen failed" << std::endl;
    }
    while (fgets(buffer.data(), buffer.size(), pipe) != nullptr) {
        result += buffer.data();
    }
    this->exit_code = pclose(pipe);
    this->stdout = result;
    this->status = FINISHED;
    std::cout << "return code: " << this->exit_code << std::endl;
    std::cout << "result: " << this->stdout << std::endl;
}

void handle_job_requests(int socket, std::map<std::string, std::string>* request) {
    std::string target = request->at("request_target");
    if (target == "/tasks") {
        std::string body = request->at("body");
        if (request->at("method") == "POST") {
            std::string ctype = request->at("Content-Type");
            if (ctype.rfind("multipart/form-data; boundary=", 0) == 0) {
                std::string boundary = ctype.substr(ctype.find("boundary=") + 9);
                std::cout << "\nBoundary:\n" << boundary << std::endl;
                std::cout << "body:\n" << body.length() << std::endl;
                std::string script = body.substr(body.find(boundary) + boundary.length());
                script = script.substr(1, script.find(boundary) - 4);
                script = script.substr(script.find("\r\n") + 2); // skip header
                script = script.substr(script.find("\r\n") + 2); // skip header
                script = script.substr(script.find("\r\n") + 2); // skip \r\n
                script = script.substr(0, script.size() - 2);    // remove \r\n at the end
                std::cout << "\nScript:\n" << script << std::endl;
                Task* task = new Task(global_task_id, script);
                std::thread task_thread(&Task::run, task);
                task_thread.detach();
                global_task_id++;
            }
        }
        
        std::string response = "HTTP/1.1 200 OK\r\n"
            "Server: l33t server\r\n"
            "\r\n"
            "<html>\r\n"
            "<head>\r\n"
            "<style>\n"
                "table, th, td {"
                "border: 1px solid black;"
                "border-collapse: collapse;"
                "}"
                "th, td {"
                "padding: 5px;"
                "}"
                "th {"
                "text-align: left;"
                "}"
                "</style>"
            "<\/head>\r\n"
            "\t<form method=\"post\" enctype=\"multipart/form-data\">\r\n"
            "\t\tAdd new job:\r\n"
            "\t\t<input type=\"file\" name=\"fileToUpload\" id=\"fileToUpload\">\r\n"
            "\t\t<input type=\"submit\" value=\"Upload script\" name=\"submit\">\r\n"
            "\t</form>\r\n"
            "\t<b>Tasks:</b></br>\r\n"
            "\t<table>\r\n"
            "\t\t<tr>\r\n"
            "\t\t\t<th>id</th>\r\n"
            "\t\t<th>status</th>\r\n"
            "\t\t<th></th>\r\n"
            "\t\t<\/tr>\r\n";
        
        for (auto const& task : tasks) {
            response.append("\t\t<tr>\r\n");
            response.append("\t\t\t<td>\r\n");
            response.append(std::to_string(task->id));
            response.append("\n\t\t\t<\/td>\r\n");
            response.append("\t\t\t<td>\r\n");
            switch (task->status) {
                case NOT_RUNNING:   response.append("not running"); break;
                case RUNNING:       response.append("running"); break;
                case FINISHED:      response.append("finished"); break;
            }
            response.append("\n\t\t\t<\/td>\r\n");
            response.append("\t\t\t<td>\r\n");
            response.append("<a href=\"/tasks/");
            response.append(std::to_string(task->id));
            response.append("\">details</a>");
            response.append("\n\t\t\t<\/td>\r\n");
            response.append("\t\t<\/tr>\r\n");
        }
        response.append("\t</table>\r\n");
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
                "\r\n"
                "<html>\r\n"
                "<head>\r\n"
                "<style>\n"
                    "table, th, td {"
                    "border: 1px solid black;"
                    "border-collapse: collapse;"
                    "}"
                    "th, td {"
                    "padding: 5px;"
                    "}"
                    "th {"
                    "text-align: left;"
                    "}"
                    "</style>"
                "<\/head>\r\n"
                "\t<table>\r\n"
                "\t\t<tr>\r\n"
                "\t\t\t<th>id</th>\r\n"
                "\t\t\t<th>status</th>\r\n"
                "\t\t\t<th>script</th>\r\n"
                "\t\t\t<th>return code</th>\r\n"
                "\t\t\t<th>output</th>\r\n"
                "\t\t<\/tr>\r\n"
                "\t\t<tr>\r\n";

            for (auto const& task : tasks) {
                if (task->id == id) {
                    response.append("\t\t<td>\r\n");
                    response.append(std::to_string(task->id));
                    response.append("\n\t\t<\/td>\r\n");
                    response.append("\t\t<td>\r\n");
                    switch (task->status) {
                        case NOT_RUNNING:   response.append("not running"); break;
                        case RUNNING:       response.append("running"); break;
                        case FINISHED:      response.append("finished"); break;
                    }
                    response.append("\n\t\t<\/td>\r\n");
                    response.append("\t\t<td>\r\n");
                    response.append("<pre>\r\n");
                    response.append(task->script);
                    response.append("</pre>\r\n");
                    response.append("\n\t\t<\/td>\r\n");
                    if (task->status == FINISHED) {
                        response.append("\t\t<td>\r\n");
                        response.append(std::to_string(task->exit_code));
                        response.append("\n\t\t<\/td>\r\n");
                        response.append("\t\t<td>\r\n");
                        response.append("<pre>\r\n");
                        response.append(task->stdout);
                        response.append("</pre>\r\n");
                        response.append("\n\t\t<\/td>\r\n");
                    }
                    response.append("</html>\r\n");
                    break;
                }
            }
            send(socket, response.c_str(), strlen(response.c_str()), 0);
        }
    }
}
