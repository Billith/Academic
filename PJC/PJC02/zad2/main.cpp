#include <iostream>
#include <limits>

int main() {

    int input;
    int max_value = std::numeric_limits<int>::min();
    int max_count = 0;
    int min_value = std::numeric_limits<int>::max();
    int min_count = 0;

    while(true) {
        std::cin >> input;
        if (input == 0) {
            break;
        } else {
            if(input > max_value) {
                max_value = input;
                max_count = 0;
            }
            if(input == max_value) {
                max_count++;
            }

            if(input < min_value) {
                min_value = input;
                min_count = 0;
            }
            if(input == min_value) {
                min_count++;
            }
        }
    }
    std::cout << "Min = " << min_value << " " << min_count << " razy" << std::endl;
    std::cout << "Max = " << max_value << " " << max_count << " razy" << std::endl;

}