#include <iostream>
#include <cstring>

int simil(const char a[], const char b[]) {

    int score = 0;

    if(strlen(a) == strlen(b)) {

        for(int i=0; i<strlen(a); i++) {
            int small_score = 0;
            while(a[i] == b[i] && a[i] != '\0') {
                small_score++;
                i++;
            }
            score = score + (small_score * small_score);
        }

    } else {

        const char* longer_array;
        const char* shorter_array;
        int highest_score = 0;

        if(strlen(a)>strlen(b)) {
            longer_array = a;
            shorter_array = b;
        } else {
            longer_array = b;
            shorter_array = a;
        }

        for(int i=0; i<(strlen(longer_array)-(strlen(shorter_array)-1)); i++) {

            int small_score = 0;
            int substring_score = 0;

            for(int o=0; o<strlen(shorter_array); o++) {
                if(longer_array[i + o] == shorter_array[o]) {
                    while(longer_array[i + o] == shorter_array[o] && shorter_array[o] != '\0' && longer_array[i + o] != '\0') {
                        small_score++;
                        o++;
                    }
                    substring_score = substring_score + (small_score * small_score);
                    small_score = 0;
                }
            }
            if(substring_score>highest_score)
                highest_score = substring_score;
        }
        score = highest_score;
    }
    return score;
}

int main() {
    char a[] = "AACTACGTC",
            b[] = "ACGTA";
    std::cout << a << " and " << b << " -> "
              << simil(a,b) << std::endl;
    char c[] = "GCGC",
            d[] = "AGGGCA";
    std::cout << c << " and " << d << " -> "
              << simil(c,d) << std::endl;
    char e[] = "AAACA",
            f[] = "AAAAA";
    std::cout << e << " and " << f << " -> "
              << simil(e, f) << std::endl;
    char g[] = "ACCTATTACC",
            h[] = "AGTA";
    std::cout << g << " and " << h << " -> "
              << simil(g, h) << std::endl;
    char i[] = "AGTACCT",
            j[] = "ACC";
    std::cout << i << " and " << j << " -> "
              << simil(i, j) << std::endl;
    char k[] = "AGTACCT",
            l[] = "ACC";
    std::cout << l << " and " << k << " -> "
              << simil(l, k) << std::endl;
    char m[] = "AGTACCT",
            n[] = "ACCTGGC";
    std::cout << m << " and " << n << " -> "
              << simil(m, n) << std::endl;
    char o[] = "AGTACCT",
            u[] = "AGTAGCT";
    std::cout << o << " and " << u << " -> "
              << simil(o, u) << std::endl;
    char p[] = "AGTACGGCAG",
            r[] = "CGGAGG";
    std::cout << p << " and " << r << " -> "
              << simil(p, r) << std::endl;
}