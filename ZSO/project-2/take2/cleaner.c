#include <unistd.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/sem.h>
#include <sys/shm.h>

int main() {
    for (int i=0; i < 6; i++) {
        int mq = msgget(ftok("main.c", i), IPC_CREAT);
        msgctl(mq, IPC_RMID, NULL);
    }
    for (int i=0; i < 3; i++) {
        int sem = semget(ftok("main.c", i), 1, IPC_CREAT | 0660);
        semctl(sem, 0, IPC_RMID);
        int shm_id = shmget(ftok("main.c", i), 200, 0);
        shmctl(shm_id, IPC_RMID, NULL);
    }
}