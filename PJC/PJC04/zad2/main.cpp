#include <iostream>

unsigned short koduj(int plec, int stan_cw,
                     int grupa_wiek, int edu,
                     int zam, int region,
                     int odp) {

    unsigned short wynik = 0;

    wynik = wynik | plec;
    wynik = wynik << 2;
    wynik = wynik | stan_cw;
    wynik = wynik << 2;
    wynik = wynik | grupa_wiek;
    wynik = wynik << 2;
    wynik = wynik | edu;
    wynik = wynik << 2;
    wynik = wynik | zam;
    wynik = wynik << 4;
    wynik = wynik | region;
    wynik = wynik << 3;
    wynik = wynik | odp;

    return wynik;
};

void info(unsigned short kod) {

    int odp = kod & 7;
    kod = kod >> 3;
    int region = kod & 15;
    kod = kod >> 4;
    int zam = kod & 3;
    kod = kod >> 2;
    int edu = kod & 3;
    kod = kod >> 2;
    int grupa_wiek = kod & 3;
    kod = kod >> 2;
    int stan_cw = kod & 3;
    kod = kod >> 2;
    int plec = kod & 3;

    std::cout << "plec:             " << plec << std::endl
              << "stan cywilny:     " << stan_cw << std::endl
              << "grupa wiekowa:    " << grupa_wiek << std::endl
              << "wyksztalcenie     " << edu << std::endl
              << "miejsce zam.:     " << zam << std::endl
              << "region:           " << region << std::endl
              << "odpowiedz:        " << odp << std::endl;
}

int main() {
    unsigned short kod = koduj(0, 3, 2, 3, 0, 12, 6);
    info(kod);
}