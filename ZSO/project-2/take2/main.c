#include <stdio.h>
#include <signal.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <sys/types.h>

static int que_arr[6] = {0};
static int sem_arr[3] = {0};
static int mem_arr[3] = {0};
static int fork_child_id = -1;

struct my_msg {
    long type;
    unsigned long ttl;
};

union semun {
    int val;
    struct semid_ds* buf;
    unsigned short* array;
    struct seminfo* __buf;
};

void stats_sig_handler() {
    printf("[%i] statitics sent\n", fork_child_id);
}

void* start_in_que(void* child_id) {
    char* shm_stats = shmat(mem_arr[fork_child_id], NULL, 0);
    unsigned long cid = (unsigned long)child_id;
    struct my_msg msg;
    int sem = sem_arr[cid];
    struct sembuf sop;
    sop.sem_num = 0;
    sop.sem_flg = 0;
    while (1) {
        int res = msgrcv(que_arr[cid], &msg, sizeof(msg), 1L, 0);
        if (res == -1)
            continue;
        sop.sem_op = -1;
        semop(sem, &sop, 1);
        shm_stats[msg.ttl] += 1;
        sop.sem_op = 1;
        semop(sem, &sop, 1);
        printf("[%lu] msg recived:  type => %ld  ttl => %lu  size => %i\n", cid, msg.type, msg.ttl, res);
        if (cid == 0) {
            if (msg.ttl <= 0) {
                puts("ttl <= 0, removing");
                continue;
            } else {
                msg.ttl -= 1;
            }
        }
        sleep(1);
        msgsnd(que_arr[cid + 1], &msg, sizeof(msg), 0);
        sop.sem_op = -1;
        semop(sem, &sop, 1);
        shm_stats[msg.ttl + 100] += 1;
        sop.sem_op = 1;
        semop(sem, &sop, 1);
        printf("[%lu] msg sent:    type => %ld  ttl => %lu\n", cid, msg.type, msg.ttl);
        memset(&msg, 0, sizeof(msg));
        sleep(1);
    }
}

void* start_out_que(void* child_id) {
    char* shm_stats = shmat(mem_arr[fork_child_id], NULL, 0);
    unsigned long cid = (unsigned long)child_id;
    struct my_msg msg;
    int sem = sem_arr[cid-3];
    struct sembuf sop;
    sop.sem_num = 0;
    sop.sem_flg = 0;
    while (1) {
        int res = msgrcv(que_arr[cid], &msg, sizeof(msg), 1L, 0);
        if (res == -1)
            continue;
        sop.sem_op = -1;
        semop(sem, &sop, 1);
        shm_stats[msg.ttl] += 1;
        sop.sem_op = 1;
        semop(sem, &sop, 1);
        printf("[%lu] msg recived:  type => %ld  ttl => %lu  size => %i\n", cid, msg.type, msg.ttl, res);
        sleep(1);
        msgsnd(que_arr[cid == 5 ? 0 : cid + 1], &msg, sizeof(msg), 0);
        sop.sem_op = -1;
        semop(sem, &sop, 1);
        shm_stats[msg.ttl + 100] += 1;
        sop.sem_op = 1;
        semop(sem, &sop, 1);
        printf("[%lu] msg sent:     type => %ld  ttl => %lu\n", cid, msg.type, msg.ttl);
        memset(&msg, 0, sizeof(msg));
        sleep(1);
    }
}

int main() {
    for (int i=0; i < 3; i++) {
        que_arr[i]   = msgget(ftok("main.c", i), IPC_CREAT | 0666);
        que_arr[i+3] = msgget(ftok("main.c", i+3), IPC_CREAT | 0666);
        sem_arr[i]   = semget(ftok("main.c", i), 1, IPC_CREAT | 0660);
        mem_arr[i]   = shmget(ftok("main.c", i), 200, IPC_CREAT | IPC_EXCL | 0666);

        union semun su;
        su.val = 1;
        semctl(sem_arr[i], 0, SETVAL, su);
    }

    for (unsigned long i=0; i < 3; i++) {
        if (fork() == 0) {
            fork_child_id = i;
            printf("[%lu] child %i\n", i, getpid());
            signal(10, stats_sig_handler);  // SIGUSR1

            if (i == 0) {
                struct my_msg init_msg;
                init_msg.type = 1;
                init_msg.ttl = 10;
                for (int n=0; n < 3; n++) {
                    msgsnd(que_arr[i], &init_msg, sizeof(init_msg), 0);
                }
            }

            pthread_t tid1, tid2;
            pthread_create(&tid1, NULL, start_in_que,  (void*)i);
            pthread_create(&tid2, NULL, start_out_que, (void*)i+3);

            pthread_exit(NULL);
            exit(0);
        }
    }
    wait(NULL);
    return 0;
}
