#include<iostream>

using namespace std;
int sizeOfArr(const char* s) {
    int i=0;
    while(s[i] != '\0') {
        i++;
    }
    return i;
}

bool isLegal(const char* s) {
    int i=0;
    while(s[i] != '\0') {
        if(!((s[i]>=65 && s[i]<=90) || (s[i]>=97 && s[i]<=122) || s[i] == 32 || s[i] == 46 || s[i] == 44)) {
            cout << "Illegal char " << s[i] << endl;
            return false;
        }
    i++;
    }
    return true;
}
int numbOfLetters(const char* s) {
    int letterCounter = 0;
    for(int i=0; i<sizeOfArr(s); i++) {
        if((s[i]>=65 && s[i]<=90) || (s[i]>=97 && s[i]<=122)) {
            letterCounter++;
        }
    }
    return letterCounter;
}
int numbOfDiffLetters(const char* s) {
    int letters = sizeOfArr(s);
    int counter = 0;
    bool check = false;
    for(int i=0; i<letters; i++) {
        for (int o=i-1; o>=0; o--) {
            if (s[o] == s[i]) {
                 check = true;
                break;
            }
            if((s[o] == s[i]+32) || (s[o]+32 == s[i])) {
                check = true;
                break;
            }
        }
        if (check == false && !(s[i] == 32 || s[i] == 44 || s[i] == 46))
            counter++;
        check = false;
    }
    return counter;
}
void mostFrequentLetter(const char* s) {
    int max_count = 0;
    char max_char;
    for(int i=0; i<sizeOfArr(s);i++) {
        int counter = 1;
        for(int o=i+1; o<sizeOfArr(s);o++) {
            if(s[i] == s[o]) {
                counter++;
            }
            if((s[i] == s[o]+32) || (s[i] == s[o]-32)) {
                counter++;
            }
        }
        if(counter >= max_count && (!(s[i] == 32 || s[i] == 44 || s[i]==46))) {
            max_count = counter;
            max_char = s[i];
        }
    }
    cout << max_char << "/" << (char)(max_char+32) << " occurs " << max_count << " times" << endl;
}
void allLetters(const char* s) {
    int letters = sizeOfArr(s);
    bool check = false;
    for(int i=0; i<letters; i++) {
        for (int o=i-1; o>=0; o--) {
            if (s[o] == s[i]) {
                check = true;
                break;
            }
            if((s[o] == s[i]+32) || (s[o]+32 == s[i])) {
                check = true;
                break;
            }
        }
        if (check == false && !(s[i] == 32 || s[i] == 44 || s[i] == 46))
            if(s[i] >=97 && s[i] <=122)
                cout << (char)(s[i]-32);
            else
                cout << s[i];
        check = false;
    }
    cout << endl;
}
int main() {
    const char str[]=
            "To be, or not to be, that is the question";
// 1.
    bool legal = isLegal(str);
    if(!legal) {
        cout << "Illegal string" << endl;
        return 1;
    }
    cout << "String OK" << endl;
// 2.
    int letters = numbOfLetters(str);
    cout << letters << " letters" << endl;
// 3.
    int diffLett = numbOfDiffLetters(str);
    cout << diffLett << " different letters" << endl;
// 4.
    mostFrequentLetter(str);
// 5.
    allLetters(str);
}