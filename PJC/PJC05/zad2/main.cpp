#include <iostream>
#include <functional>

double f1(double x) { return x/4; }
double f2(double x) { return -2*x; }
double f3(double x) { return x*x; }

using D2D = std::function<double(double)>;

D2D maxfun (D2D funs[], size_t size, double a, double b, double* pmax) {
    static constexpr double eps = 1e-6;

    double max_value = funs[0](a);
    double max_x = a;
    double tmp = a;
    D2D max_fun = funs[0];
    double result;

    for(int i=0; i<size; i++) {
        while(a<b) {
            result = funs[i](a);
            if(result>max_value) {
                max_value = result;
                max_fun = funs[i];
                max_x = a;
            }
            a = a + eps;
        }
        a = tmp;
    }
    *pmax = max_x;
    return max_fun;
}

int main() {
    double xmax;
    D2D funs[] = { f1, f2, f3 };
    //D2D funs[] = { f1, f2, [](double x = 2) -> { return 2*x; } };
    D2D pf = maxfun(funs, 3, 0, 3, &xmax);
    std::cout << "xmax = " << xmax << "; f(xmax) = "
         << pf(xmax) << std::endl;
}