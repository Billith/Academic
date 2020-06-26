#pragma once

enum task_status {
    NOT_RUNNING = 0,
    RUNNING     = 1,
    FINISHED    = 2,
};

class Task {
public:
    Task(int id, std::string script);
    void run();
    int id;
    task_status status;
    std::string script;
    int exit_code;
    std::string stdout;
};

void handle_job_requests(int socket, std::map<std::string, std::string>* request);
