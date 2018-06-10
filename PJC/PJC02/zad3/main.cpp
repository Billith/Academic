#include <iostream>
//#define STAR
#define EQU
#if defined(STAR) && defined(EQU)
#error Zdefiniuj tylko jedno makro!
#elif !(defined(STAR) || defined(EQU))
#error Zdefiniuj któreś makro!
#endif
void histo(int arr[], size_t size) {

    int max = arr[0];

    for(int i=0; i<size; i++) {
        if(arr[i]>max) {
            max = arr[i];
        }
    }

    for(int i=max; i>=0; i--) {
        for(int o=0; o<size; o++) {
            if (arr[o] > i) {
#ifdef STAR
                std::cout << "* ";
#elif defined(EQU)
                std::cout << "= ";
#endif
            } else {
                std::cout << "  ";
            }
            if (o == size) {
                std::cout << std::endl;
            }
        }
        std::cout << std::endl;
    }

}

int main() {
    int arr[] = {2, 1, 10, 7, 1, 9};
    size_t size = sizeof(arr)/sizeof(*arr);
    histo(arr, size);
}