#include <iostream>
#define GETMAX
#define GETMIN

int main() {
        int liczba_1;
        int liczba_2;
        int liczba_3;
        int liczba_min;
        int liczba_max;

        std::cout << "Podaj pokolei trzy liczby" << std::endl;
        std::cin >> liczba_1 >> liczba_2 >> liczba_3;

        liczba_min = liczba_1;
        liczba_max = liczba_1;
        if (liczba_2 < liczba_min)
            liczba_min = liczba_2;
        if (liczba_2 > liczba_max)
            liczba_max = liczba_2;
        if (liczba_3 < liczba_min)
            liczba_min = liczba_3;
        if (liczba_3 > liczba_max)
            liczba_max = liczba_3;

#if defined(GETMAX) && !defined(GETMIN)
    std::cout << "MAX " << liczba_max << std::endl;
#endif
#if defined(GETMIN) && !defined(GETMAX)
    std::cout << "MIN " << liczba_min << std::endl;
#endif
#if defined(GETMAX) && defined(GETMIN)
    std::cout << "MAX " << liczba_max << std::endl << "MIN " << liczba_min << std::endl;
#endif
#if !(defined(GETMAX) && defined(GETMIN))
#error Nie zdefiniowano żadnej funkcji
#endif
}