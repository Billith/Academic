#include <iostream>

enum Banks {PKO, BGZ, BRE, BPH};

struct Account {
    Banks bank;
    int balance;
};

struct Person {
    char name[20];
    Account account;
};

struct Couple {
    Person he;
    Person she;
};

const Couple* bestClient(const Couple* cpls, int size, Banks bank) {

    int highest_savings_index = -1;
    int highest_savings = 0;

    for(int i=0; i<size; i++) {
        if(cpls[i].he.account.bank == bank || cpls[i].she.account.bank == bank) {
            if((cpls[i].he.account.balance + cpls[i].she.account.balance) > highest_savings)
                highest_savings_index = i;
        }
    }

    if(highest_savings_index >= 0)
        return &cpls[highest_savings_index];
    else
        return nullptr;
}

int main() {

    Couple tab[] { {{"Johny", {PKO, 1200}}, {"Mary", {BGZ, 1500}}},
                   {{"Peter", {BGZ, 1400}}, {"Suzy", {BRE, 1300}}},
                   {{"Kevin", {PKO, 1600}}, {"Katy", {BPH, 1500}}},
                   {{"Kenny", {BPH, 1800}}, {"Lucy", {BRE, 1700}}}
    };

    const Couple* p = bestClient(tab, 4, BGZ);
    std::cout << p->he.name << " and " << p->she.name
              << ": " << p->he.account.balance + p->she.account.balance << std::endl;
}