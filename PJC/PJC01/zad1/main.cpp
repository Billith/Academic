#include <iostream>

int main() {
    double wzrost;
    double waga;

    std::cout << "Podaj swoj wzrost w metrach" << std::endl;
    std::cin >> wzrost;

    std::cout << "Podaj swoja wage w kilogramach" << std::endl;
    std::cin >> waga;

    std::cout << "BMI: " << waga / (wzrost*wzrost) << std::endl;

}