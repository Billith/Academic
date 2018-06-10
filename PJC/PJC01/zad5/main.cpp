#include <iostream>
#include <stdio.h>

int main() {
    int max = 1000000;
    int current = 500000;
    int min = 1;
    int counter=0;

    while(true) {
        std::cout << "Czy jest to " << current << "?" <<std::endl;
        char check;
        std::cin >> check;
        if(check == 'y') {
            std::cout << "Pomyślana liczba to " << current << std::endl;
            return 0;
        }
        if(check == 's') {
            max = current;
            current = (max-min)/2+min;
        }
        if(check == 'b') {
            min = current;
            current = (max-min)/2+min;
        }
        counter++;
        std::cout << "Counter: " << counter << std::endl;
        std::cout << "Range: " << min << " - " << max << std::endl << std::endl;
    }
}