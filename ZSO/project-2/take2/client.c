#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/types.h>

static int sem_arr[3] = {0};
static int mem_arr[3] = {0};

int main() {
    char input[10];
    for (int i=0; i < 3; i++) {
        sem_arr[i] = semget(ftok("main.c", i), 1, 0);
        mem_arr[i] = shmget(ftok("main.c", i), 200, 0);
    }

    puts("Enter exit or process child id");
    puts("Choose one from 1-3");
    while (1) {
        printf("> ");
        fgets(input, sizeof(input), stdin);

        if (strcmp(input, "exit\n") == 0) {
            return 0;
        }
        
        int id = atoi(input);
        if (id > 0 && id < 4) {
            //kill(pid, SIGUSR1);
            int sem = sem_arr[id];
            struct sembuf sop;
            sop.sem_num = 0;
            sop.sem_flg = 0;
            sop.sem_op = -1;
            semop(sem, &sop, 1);
            char* shm_stats = shmat(mem_arr[id - 1], NULL, 0);
            puts("in:");
            for (int i=0; i < 100; i++) {
                if (shm_stats[i] != 0)
                    printf("\tttl %i: %i\n", i, shm_stats[i]);
            }
            puts("out:");
            for (int i=100; i < 200; i++) {
                if (shm_stats[i] != 0)
                    printf("\tttl %i: %i\n", i-100, shm_stats[i]);
            }
            sop.sem_op = 1;
            semop(sem, &sop, 1);
        }
    }
}