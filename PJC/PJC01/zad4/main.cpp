#define ENG
#if   defined(POL) && defined(ENG)
#error Please define only one country
#elif !(defined(POL) || defined(ENG))
#error Please define a country
#endif

#include <iostream>

int main() {
    int current_number;
    int max = 0;
    int max_suma = 0;

    while(current_number!=0) {
#ifdef ENG
        std::cout << "enter a natural number (0 if done): ";
#elif defined(POL)
        std::cout << "wprowadz liczbe naturalna (0 aby skonczyc): ";
#endif
        std::cin >> current_number;

        int reszta;
        int max2 = current_number;
        int suma = 0;

        while(max2 > 0) {
            reszta = max2%10;
            max2 = max2/10;
            suma += reszta;
        }
        if(suma > max_suma) {
            max = current_number;
            max_suma = suma;
        }
    }

#ifdef ENG
    std::cout << "Max sum of digits was " << max_suma << " for " << max << std::endl;
#elif defined(POL)
    std::cout << "Maksymalna suma cyfr to " << max_suma << " dla " << max << std::endl;
#endif

}