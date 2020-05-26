#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/shm.h>

#include "communication.h"

static char receive_stats[100] = {0};
static char send_stats[100] = {0};

void stats_sig_handler() {
    key_t mem_key = get_mem_key();
    size_t sz = sizeof(receive_stats) + sizeof(send_stats);
    int shm_id = shmget(mem_key, sz, IPC_CREAT | IPC_EXCL | 0666);
    if (shm_id != -1) {
        char* shm_stats = shmat(shm_id, NULL, 0);
        memcpy(shm_stats, receive_stats, sizeof(receive_stats));
        memcpy(shm_stats + sizeof(receive_stats), send_stats, sizeof(send_stats));
    }
    printf("[%i] statitics sent\n", getpid());
}

int main(int argc, char** argv) {
    signal(10, stats_sig_handler);  // SIGUSR1
    pid_t pid = getpid();
    key_t que_key = get_que_key();
    printf("[%i] message queues key:\n  in: %i\n", pid, que_key);
    
    int mq = msgget(que_key, IPC_CREAT | 0666);

    struct my_msg init_msg;
    init_msg.type = 1;
    init_msg.ttl = 5;
    for (int i=0; i < 5; i++) {
        send_stats[init_msg.ttl]++;
        msgsnd(mq, &init_msg, sizeof(init_msg), 0);
        printf("[%i] message sent:     type => %ld  ttl => %lu\n", pid, init_msg.type, init_msg.ttl);
        sleep(1);
    }

    struct my_msg msg;
    struct msqid_ds ds;
    while (msgctl(mq, IPC_STAT, &ds) != -1) {
        if (ds.msg_qnum > 0) {
            int res = msgrcv(mq, &msg, sizeof(msg), 2L, 0);
            if (res == -1)
                continue;
            receive_stats[msg.ttl]++;
            printf("[%i] message recived:  type => %ld  ttl => %lu  size => %i\n", pid, msg.type, msg.ttl, res);
            if (msg.ttl <= 0) {
                puts("ttl <= 0, removing");
                continue;
            } else {
                msg.ttl -= 1;
            }
            msg.type = 1;
            msgsnd(mq, &msg, sizeof(msg), 0);
            send_stats[msg.ttl]++;
            printf("[%i] response sent:    type => %ld  ttl => %lu\n", pid, msg.type, msg.ttl);

            memset(&msg, 0, sizeof(msg));
            memset(&ds, 0, sizeof(ds));
        } else {
            break;
        }
        sleep(1);
    }
    msgctl(mq, IPC_RMID, NULL);
}
