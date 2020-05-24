#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>
#include <sys/shm.h>
#include <unistd.h>

#include "communication.h"

int main() {
    char input[10];
    key_t mem_key = get_mem_key();

    puts("Enter process PID or exit command");
    while (1) {
        printf("> ");
        fgets(input, 10, stdin);

        if (strcmp(input, "exit\n") == 0) {
            return 0;
        }
        
        int pid = atoi(input);
        if (pid > 0) {
            kill(pid, SIGUSR1);
            //printf("%i\n", pid);
            int shm_id = 0;
            while ((shm_id = shmget(mem_key, 200, 0)) == -1) {
                sleep(1);
            }

            char* stats;
            while ((stats = shmat(shm_id, 0, 0)) == -1) {
                puts("failed 2");
                continue;
            }

            puts("IN:");
            for (int i=0; i<100; i++) {
                if (stats[i] != 0)
                    printf("\tttl %i: %i\n", i, stats[i]);
            }

            puts("OUT:");
            for (int i=0; i<100; i++) {
                if ((stats + 100)[i] != 0)
                    printf("\tttl %i: %i\n", i, (stats + 100)[i]);
            }
            shmctl(shm_id, IPC_RMID, NULL);
        }
    }
}