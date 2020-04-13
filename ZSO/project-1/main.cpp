#include <iostream>
#include <thread>
#include <chrono>
#include <mutex>
//#include <experimental/barrier>

#define DEBUG
#define DELAY
#define COUNT 1


//std::barrier g_barrier;
std::mutex g_lock;

void child(int counter) {
    g_lock.lock();
    
    for (int i=0; i < counter; i++) {
        #ifdef DEBUG
        std::cout << "Thread id: " << std::this_thread::get_id() << std::endl;
        #endif

        g_lock.unlock();

        #ifdef DELAY
        std::this_thread::sleep_for(std::chrono::seconds(1));
        #endif
    }
}

void parent() {
    std::thread c1(child, COUNT);
    std::thread c2(child, COUNT);
    std::thread c3(child, COUNT);
    c1.detach();
    c2.detach();
    c3.detach();
}

int main() {
    for (int i=0; i < 10; i++) {
        std::thread p1(parent);
        p1.join();
    }
}
