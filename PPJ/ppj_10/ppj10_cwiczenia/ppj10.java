package ppj10_cwiczenia;
    public 
        class ppj10 {
        public static void main(String[] args){
        //Zad1
        ZbiorMetod obj = new ZbiorMetod();
        byte zm1 = 1;
        obj.ustalWartosc(10);
        obj.ustalWartosc(10.01f);
        obj.ustalWartosc('A');
        obj.ustalWartosc(zm1);
        
        System.out.println();
        //Zad2
        Liczba liczba = new Liczba();
        obj.ustalWartosc(10);
        obj.ustalWartosc(10.01f);
        obj.ustalWartosc(liczba);
        System.out.println();
        
        //Zad3
        Osoba osoba = new Osoba();
        osoba.imie = "Dawid";
        osoba.nazwisko = "Koral";
        osoba.rokUro = 1996;
        
        //Zad4
        Osoba osoba1 = new Osoba("Dawid","Koral",1996);
        osoba1.show();
        
        //Zad5
        LiczbaZespolona liczba1 = new LiczbaZespolona(10,13);
        LiczbaZespolona liczba2 = new LiczbaZespolona(1230,12123);
        LiczbaZespolona liczba3 = new LiczbaZespolona(1,5);
        
        System.out.println();
        
        liczba1.wyswietl();
        liczba2.wyswietl();
        liczba3.wyswietl();
        
        System.out.println();
        
        liczba1.dodaj(liczba2);
        liczba1.wyswietl();
        System.out.println();
        liczba1.odejmij(liczba2);
        liczba1.wyswietl();
        System.out.println();
        liczba3.pomnoz(liczba1);
        liczba3.wyswietl();
        System.out.println();
        liczba3.zwieksz();
        liczba3.wyswietl();
    }
}