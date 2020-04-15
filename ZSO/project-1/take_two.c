#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <stdbool.h>
#include <time.h>
#include <stdlib.h>

pthread_mutex_t print_mutex  = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t parent_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t  parent_cv    = PTHREAD_COND_INITIALIZER;
pthread_mutex_t child_mutex  = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t  child_cv     = PTHREAD_COND_INITIALIZER;

const unsigned int CHILDREN_GROUPS = 10;
unsigned int children = -1;  // no parent around
unsigned int leave_count = 0;
bool kindergarden = false;   // flag if is kindergarden already taken


void* child() {
    pthread_mutex_lock(&print_mutex);
    printf("child %lu created\n", pthread_self());
    pthread_mutex_unlock(&print_mutex);

    pthread_mutex_lock(&parent_mutex);
    while (kindergarden || children == -1 || children == 3) {
        // pthread_mutex_lock(&print_mutex);
        // printf("child %lu waiting for parent\n", pthread_self());
        // pthread_mutex_unlock(&print_mutex);
        pthread_cond_wait(&parent_cv, &parent_mutex);
    }

    pthread_mutex_lock(&print_mutex);
    printf("child %lu found parent\n", pthread_self());
    pthread_mutex_unlock(&print_mutex);

    children++;
    sleep(2);
    pthread_cond_broadcast(&parent_cv);
    pthread_mutex_unlock(&parent_mutex);

    pthread_mutex_lock(&parent_mutex);
    while (children != 3) {
        // pthread_mutex_lock(&print_mutex);
        // printf("child %lu waiting for other children\n", pthread_self());
        // pthread_mutex_unlock(&print_mutex);
        pthread_cond_wait(&parent_cv, &parent_mutex);
    }
    pthread_mutex_unlock(&parent_mutex);

    pthread_mutex_lock(&print_mutex);
    printf("child %lu is in the kindergarden\n", pthread_self());
    pthread_mutex_unlock(&print_mutex);

    sleep(2 * (rand() % 4 + 1));

    // pthread_mutex_lock(&child_mutex);
    // pthread_mutex_lock(&print_mutex);
    // printf("child %lu want to leave the kindergarden: %i\n", pthread_self(), children);
    // pthread_mutex_unlock(&print_mutex);
    // leave_count++;
    // pthread_cond_broadcast(&child_cv);

    // while (leave_count % 2 != 0) {
    //     pthread_mutex_lock(&print_mutex);
    //     printf("child %lu waiting for someone to leave the kindergarden: %i\n", pthread_self(), children);
    //     pthread_mutex_unlock(&print_mutex);
    //     pthread_cond_wait(&child_cv, &child_mutex);
    // }

    // pthread_mutex_unlock(&child_mutex);

    pthread_mutex_lock(&parent_mutex);
    children--;

    pthread_mutex_lock(&print_mutex);
    printf("child %lu leaving the kindergarden\n", pthread_self());
    pthread_mutex_unlock(&print_mutex);

    pthread_cond_broadcast(&parent_cv);
    pthread_mutex_unlock(&parent_mutex);

    return NULL;
}

void* parent(void* CHILDREN_GROUPS) {
    pthread_mutex_lock(&print_mutex);
    printf("parent %lu created\n", pthread_self());
    pthread_mutex_unlock(&print_mutex);
    unsigned int child_count = (const unsigned int)CHILDREN_GROUPS * 3;
    sleep(4);

    while(child_count != 0) {
        pthread_mutex_lock(&parent_mutex);
        children = 0;
        pthread_mutex_lock(&print_mutex);
        printf("parent %lu started gathering children\n", pthread_self());
        pthread_mutex_unlock(&print_mutex);
        sleep(3);
        pthread_cond_broadcast(&parent_cv);
        pthread_mutex_unlock(&parent_mutex);

        pthread_mutex_lock(&parent_mutex);
        while (children != 3) {
            // pthread_mutex_lock(&print_mutex);
            // printf("parent %lu waiting for children\n", pthread_self());
            // pthread_mutex_unlock(&print_mutex);
            pthread_cond_wait(&parent_cv, &parent_mutex);
        }
        kindergarden = true;
        pthread_mutex_unlock(&parent_mutex);

        pthread_mutex_lock(&print_mutex);
        printf("parent %lu is in kindergadren\n", pthread_self());
        pthread_mutex_unlock(&print_mutex);

        pthread_mutex_lock(&parent_mutex);
        while (children != 0) {
            // pthread_mutex_lock(&print_mutex);
            // printf("parent %lu waiting for children to leave kinder garden\n", pthread_self());
            // pthread_mutex_unlock(&print_mutex);
            pthread_cond_wait(&parent_cv, &parent_mutex);
        }
        pthread_mutex_lock(&print_mutex);

        sleep(2);
        printf("parent %lu going back for other children\n", pthread_self());
        pthread_mutex_unlock(&print_mutex);
        kindergarden = false;
        children = -1;
        pthread_mutex_unlock(&parent_mutex);
        child_count -= 3;
        sleep(5);
    }
    printf("parent %lu going home, no more children left\n", pthread_self());

    return NULL;
}

int main() {
    srand(time(NULL));

    for (int i=0; i < CHILDREN_GROUPS; i++) {
        pthread_t c1;
        pthread_t c2;
        pthread_t c3;
        pthread_create(&c1, NULL, &child, NULL);
        pthread_create(&c2, NULL, &child, NULL);
        pthread_create(&c3, NULL, &child, NULL);  
    }

    pthread_t p;
    pthread_create(&p, NULL, &parent, CHILDREN_GROUPS);
    pthread_exit(NULL);

    pthread_cond_destroy(&child_cv);
    pthread_mutex_destroy(&child_mutex);
    pthread_cond_destroy(&parent_cv);
    pthread_mutex_destroy(&parent_mutex);
    pthread_mutex_destroy(&print_mutex);

    return 0;
}
