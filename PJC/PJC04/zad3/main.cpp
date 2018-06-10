#include <iostream>

inline  int sizeOfArr(const char* s) {
    int i=0;
    while(s[i] != '\0') {
        i++;
    }
    return i;
}

inline bool check_num_of_chars(const char* pass) {
    int i=sizeOfArr(pass);
    if(i >= 8)
        return true;
    return false;
}
bool check_num_of_diff_chars(const char* pass) {
    int chars = sizeOfArr(pass);
    int counter = 0;
    bool check = false;
    for(int i=0; i<chars; i++) {
        for (int o=i-1; o>=0; o--) {
            if (pass[o] == pass[i]) {
                check = true;
                break;
            }
        }
        if (check == false)
            counter++;
        check = false;
    }
    if(counter >= 6)
        return true;
    return false;
}

bool check_for_digit(const char* pass) {
    for(int i=0; i<sizeOfArr(pass); i++) {
        if((pass[i] >=48) && (pass[i] <=57))
            return true;
    }
    return false;
}

bool check_for_uppercase(const char *pass) {
    for(int i=0; i<sizeOfArr(pass); i++) {
        if((pass[i] >=65) && (pass[i] <=90))
            return true;
    }
    return false;
}

bool check_for_lowercase(const char* pass) {
    for(int i=0; i<sizeOfArr(pass); i++) {
        if((pass[i] >=97) && (pass[i] <=122))
            return true;
    }
    return false;
}

bool check_for_special_char(const char* pass) {
    for(int i=0; i<sizeOfArr(pass); i++) {
        if(!(pass[i] >= 48 && pass[i] <=57) &&
           !(pass[i] >=65 && pass[i] <=90) &&
           !(pass[i] >= 97 && pass[i] <= 122))
            return true;
    }
    return false;
}

bool checkpass(const char* pass) {
    bool check = true;

    if(!check_num_of_chars(pass)) {
        check = false;
        std::cout << "Too short!" << std::endl;
    }
    if(!check_num_of_diff_chars(pass)) {
        check = false;
        std::cout << "Too few different characters!" << std::endl;
    }
    if(!check_for_digit(pass)) {
        check = false;
        std::cout << "No digit!" << std::endl;
    }
    if(!check_for_uppercase(pass)) {
        check = false;
        std::cout << "No uppercase latter!" << std::endl;
    }
    if(!check_for_lowercase(pass)) {
        check = false;
        std::cout << "No lowercase latter!" << std::endl;
    }
    if(!check_for_special_char(pass)) {
        check = false;
        std::cout << "No special character!" << std::endl;
    }
    return check;
}

int main() {
    using std::cout; using std::endl;
    const char* passes[]=
            {
                    "AbcDe93", "A1b:A1b>", "Ab:Acb<",
                    "abc123><", "Zorro@123", nullptr
            };
    for(int i=0; passes[i]!=nullptr;++i) {
        cout << "checking " << passes[i] << endl;
        if(checkpass(passes[i]))
            cout<<"OK"<<endl;
        cout << endl;
    }
}