#pragma once
#include <sys/ipc.h>
#include <sys/types.h>

struct my_msg {
    long type;
    unsigned long ttl;
};

key_t get_que_key() {
    return ftok("communication.h", 0);
}

key_t get_mem_key() {
    return ftok("communication.h", 1);
}
