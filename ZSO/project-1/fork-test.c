#include <stdio.h>


int main(int argc, char** argv) {
    if (fork() == 0) {
        puts("I'm child");
        return 1;
    } else {
        sleep(1);
        puts("I'm parent");
    }
    
    return 0;
}