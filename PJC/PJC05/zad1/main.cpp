#include <iostream>

//template <typename T>
//T biggest_value (T* tab, size_t length) {
//    T biggest = tab[0];
//
//    for(int i=0; i<length; i++) {
//        if (tab[i] > biggest)
//            biggest = tab[i];
//    }
//    return biggest;
//}
template <typename T>
int biggest_value (T* tab, size_t length) {
    T biggest = tab[0];
    int biggest_index = 0;

    for(int i=0; i<length; i++) {
        if (tab[i] > biggest) {
             biggest = tab[i];
            biggest_index = i;
            }
    }
    return biggest_index;
}


int main() {
    int int_tab[] = {6, 2, 1};
    double doub_tab[] = {5.1, 5.5, 7.8};
    std::string str_tab[] = {"a", "ab", "abc"};

    std::cout << "Index: " << biggest_value(int_tab, sizeof(int_tab)/sizeof(*int_tab)) << std::endl;
    std::cout << "Index: " << biggest_value(doub_tab, sizeof(doub_tab)/sizeof(*doub_tab)) << std::endl;
    std::cout << "Index: " << biggest_value(str_tab, sizeof(str_tab)/sizeof(*str_tab)) << std::endl;

}