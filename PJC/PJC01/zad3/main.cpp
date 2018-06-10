#include <iostream>
#include <math.h>

int main() {

    int n1;
    int n2;
    int n3;
    int max;

    std::cout << "Enter three non-negative numbers:  ";
    std::cin >> n1 >> n2 >> n3;

    if(n1<0 || n2<0 || n3<0) {
        std::cout << "You entered atleast 1 negative number!" << std::endl;
        return 0;
    }

    max = n1;
    if (n2 > max)
        max = n2;
    if (n3 > max)
        max = n3;

    for(int i=max; i>=0; i--) {
        if(n1 <= i) {
            std::cout << "  ";
        } else {
            std::cout << "* ";
        }
        if(n2 <= i) {
            std::cout << "  ";
        } else {
            std::cout << "* ";
        }
        if(n3 <= i) {
            std::cout << "  " << std::endl;
        } else {
            std::cout << "* " << std::endl;
        }
    }

}