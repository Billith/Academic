#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

#define DEBUG
#define DELAY


pthread_cond_t cv = PTHREAD_COND_INITIALIZER;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
//static int kindergarten = 0;

void* child() {
#ifdef DEBUG
    printf("child %lu waiting for mutex\n", pthread_self());
#endif
    pthread_mutex_lock(&mutex);
#ifdef DEBUG
    printf("child %lu locked mutex\n", pthread_self());
#endif
#ifdef DEBUG
    printf("child %lu is in the kindergarden\n", pthread_self());
#endif
#ifdef DELAY
    sleep(1);
#endif
    printf("child %lu unlocking mutex\n", pthread_self()); 
    pthread_mutex_unlock(&mutex);
    return NULL;
}

void* parent() {
    printf("parent %lu came with childens\n", pthread_self());
    pthread_t c1;
    pthread_t c2;
    pthread_t c3;

    pthread_create(&c1, NULL, &child, NULL);
    pthread_create(&c2, NULL, &child, NULL);
    pthread_create(&c3, NULL, &child, NULL);
    
    pthread_exit(NULL);
#ifdef DEBUG
    puts("kindergarten is empty, parent can leave");
#endif
    return NULL;
}

int main() {
    pthread_t p1;
    pthread_create(&p1, NULL, &parent, NULL);
   
    pthread_exit(NULL); 
    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&cv);

    return 0;
}
