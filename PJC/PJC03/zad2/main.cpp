#include <iostream>

void merge(const int* a, size_t dima, const int* b, size_t dimb, int* c) {

    int index_a = 0;
    int index_b = 0;

    for(int i=0; i<(dima+dimb); i++) {
        if(index_a <= dima) {
            if(a[index_a] < b[index_b]) {
                c[i] = a[index_a];
                index_a++;
            } else {
                c[i] = b[index_b];
                index_b++;
            }
        }
        if(index_a > dima && index_b <= dimb) {
            if(a[index_a] < b[index_b]) {
                c[i] = a[index_a];
                index_a++;
            } else {
                c[i] = b[index_b];
                index_b++;
            }
        }
    }
}

void printArr(const int* a, size_t dima, const char* m) {
    std::cout << m << " [ ";
    for (size_t i = 0; i < dima; ++i)
        std::cout << a[i] << " ";
    std::cout << "]\n";
}

int main() {
    int a[] = {1,4,4,5,8};
    int b[] = {1,2,2,4,6,6,9};
    constexpr size_t dima = std::size(a);
    constexpr size_t dimb = std::size(b);
    constexpr size_t dimc = dima + dimb;
    int c[dimc];

    merge(a,dima,b,dimb,c);

    printArr(a,dima,"a");
    printArr(b,dimb,"b");
    printArr(c,dimc,"c");
}