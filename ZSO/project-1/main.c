#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <stdbool.h>

#ifdef DEBUG
pthread_mutex_t print_mutex  = PTHREAD_MUTEX_INITIALIZER;
#endif
pthread_mutex_t parent_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t  parent_cv    = PTHREAD_COND_INITIALIZER;
pthread_mutex_t child_mutex  = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t  child_cv     = PTHREAD_COND_INITIALIZER;

const unsigned int CHILDREN_GROUPS = 10;
unsigned int children = -1;  // no parent around
unsigned int leave_count = 0;
bool kindergarden = false;   // flag if is kindergarden already taken


void* child() {
#ifdef DEBUG
    printf("child %lu created\n", pthread_self());
#endif

    pthread_mutex_lock(&parent_mutex);
    while (kindergarden || children == -1 || children == 3) {
        pthread_cond_wait(&parent_cv, &parent_mutex);
    }

#ifdef DEBUG
    pthread_mutex_lock(&print_mutex);
    printf("child %lu found parent\n", pthread_self());
    pthread_mutex_unlock(&print_mutex);
#endif

    children++;
#ifdef DELAY
    sleep(2);
#endif
    pthread_cond_broadcast(&parent_cv);
    pthread_mutex_unlock(&parent_mutex);

    pthread_mutex_lock(&parent_mutex);
    while (children != 3) {
        pthread_cond_wait(&parent_cv, &parent_mutex);
    }
#ifdef DEBUG
    pthread_mutex_lock(&print_mutex);
    printf("child %lu is in the kindergarden\n", pthread_self());
    pthread_mutex_unlock(&print_mutex);
#endif
    pthread_mutex_unlock(&parent_mutex);

#ifdef DELAY
    sleep(4);
#endif

    pthread_mutex_lock(&child_mutex);
#ifdef DEBUG
    printf("child %lu want to leave the kindergarden: %i\n", pthread_self(), children);
#endif
    leave_count++;
    pthread_cond_broadcast(&child_cv);
    
    while (leave_count % 2 != 0) {
#ifdef DEBUG
        printf("child %lu waiting for someone to leave the kindergarden: %i\n", pthread_self(), children);
#endif
        pthread_cond_wait(&child_cv, &child_mutex);
    }

    pthread_mutex_unlock(&child_mutex);

    pthread_mutex_lock(&parent_mutex);
    children--;

#ifdef DEBUG
    pthread_mutex_lock(&print_mutex);
    printf("child %lu is leaving the kindergarden\n", pthread_self());
    pthread_mutex_unlock(&print_mutex);
#endif

    pthread_cond_broadcast(&parent_cv);
    pthread_mutex_unlock(&parent_mutex);

    return NULL;
}

void* parent(void* CHILDREN_GROUPS) {
#ifdef DEBUG
    printf("parent %lu created\n", pthread_self());
#endif
    unsigned int child_count = (const unsigned int)CHILDREN_GROUPS * 3;
#ifdef DELAY
    sleep(4);
#endif

    while(child_count != 0) {
        pthread_mutex_lock(&parent_mutex);
        children = 0;
#ifdef DEBUG
        printf("parent %lu started gathering children\n", pthread_self());
#endif
#ifdef DELAY
        sleep(3);
#endif
        pthread_cond_broadcast(&parent_cv);
        pthread_mutex_unlock(&parent_mutex);

        pthread_mutex_lock(&parent_mutex);
        while (children != 3) {
            // printf("parent %lu waiting for children\n", pthread_self());
            pthread_cond_wait(&parent_cv, &parent_mutex);
        }
        kindergarden = true;
        pthread_mutex_unlock(&parent_mutex);

#ifdef DEBUG
        pthread_mutex_lock(&print_mutex);
        printf("parent %lu is in kindergadren\n", pthread_self());
        pthread_mutex_unlock(&print_mutex);
#endif

        pthread_mutex_lock(&child_mutex);
#ifdef DEBUG
        printf("parent %lu want to leave the kindergarden: %i\n", pthread_self(), children);
#endif
        leave_count++;
        pthread_cond_broadcast(&child_cv);

        while (leave_count % 2 != 0) {
#ifdef DEBUG
            printf("parent %lu waiting for someone to leave the kindergarden: %i\n", pthread_self(), children);
#endif
            pthread_cond_wait(&child_cv, &child_mutex);
        }

        pthread_mutex_unlock(&child_mutex);

#ifdef DEBUG
        pthread_mutex_lock(&print_mutex);
        printf("parent %lu is leaving the kindergarden\n", pthread_self());
        pthread_mutex_unlock(&print_mutex);
#endif

        pthread_mutex_lock(&parent_mutex);
        while (children != 0) {
            pthread_cond_wait(&parent_cv, &parent_mutex);
        }
        
#ifdef DELAY
        sleep(2);
#endif
#ifdef DEBUG
        printf("parent %lu going back for other children\n", pthread_self());
#endif
        kindergarden = false;
        children = -1;
        pthread_mutex_unlock(&parent_mutex);
        child_count -= 3;
#ifdef DELAY
        sleep(5);
#endif
    }
#ifdef DEBUG
    printf("parent %lu going home, no more children left\n", pthread_self());
#endif

    return NULL;
}

int main() {
    for (int i=0; i < CHILDREN_GROUPS; i++) {
        pthread_t c1;
        pthread_t c2;
        pthread_t c3;
        pthread_create(&c1, NULL, &child, NULL);
        pthread_create(&c2, NULL, &child, NULL);
        pthread_create(&c3, NULL, &child, NULL);  
    }

    pthread_t p;
    pthread_create(&p, NULL, &parent, (void*)CHILDREN_GROUPS);
    pthread_exit(NULL);

    pthread_cond_destroy(&child_cv);
    pthread_mutex_destroy(&child_mutex);
    pthread_cond_destroy(&parent_cv);
    pthread_mutex_destroy(&parent_mutex);
#ifdef DEBUG
    pthread_mutex_destroy(&print_mutex);
#endif

    return 0;
}
