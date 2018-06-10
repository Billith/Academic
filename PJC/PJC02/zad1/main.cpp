#include <iostream>

using std::cout; using std::endl;

const double* aver(const double* arr, size_t size, double& average) {
    double suma = 0;
    for(int i=0; i<size; i++) {
        suma = suma + arr[i];
    }
    average = suma/size;

    double smallest_index = std::abs(average-arr[0]);
    int si = 0;
    for(int i=0; i<size; i++) {
        double diff = average - arr[i];
        if(std::abs(diff) < smallest_index) {
            si = i;
            smallest_index = std::abs(diff);
        }
    }
    return &arr[si];
}

int main () {

    double arr[] = {1,2,3,4,5,7};
    size_t size = sizeof(arr)/sizeof(arr[0]);
    double average = 0;
    const double* p = aver(arr, size, average);
    cout << *p << " " << average << endl;

}