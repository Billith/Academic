#include <iostream>
#include <thread>
#include <chrono>
#include <mutex>

#define DEBUG
#define DELAY


std::mutex g_lock;

void dummy(int count) {
    for(int i=0; i<count; i++) {
        g_lock.lock();
        #ifdef DEBUG
        std::cout << "Thread id: " << std::this_thread::get_id() << std::endl;
        #endif
        g_lock.unlock();
        #ifdef DELAY
        std::this_thread::sleep_for(std::chrono::seconds(1));
        #endif
    }
}

int main(int argc, char* argv[]) {
    std::thread t1(dummy, 5);
    std::thread t2(dummy, 5);

    t1.join();
    t2.join();

    std::cout << "All threads has finished" << std::endl;
}