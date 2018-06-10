package ppj13_cwiczenia;
    public class Main {
        public static void main(String[] args) {
            Cukierek cukierek1 = new Cukierek("waniliowy", 10);
            Cukierek cukierek2 = new Cukierek("truskawkowy", 8);
            Cukierek cukierek3 = new Cukierek("czekoladowy", 9);
            Cukierek cukierek5 = new Cukierek("czekoladowy", 9);
            Cukierek cukierek6 = new Cukierek("czekoladowy", 9);
            Cukierek cukierek7 = new Cukierek("czekoladowy", 9);
            //cukierek1.show();
            
            Cukierek cukierek4 = Fabryka.make(1);
            
            Kontener kontener1 = new Kontener(100);
            kontener1.zaladuj(cukierek1, 29);
            kontener1.zaladuj(cukierek2, 30);
            
            Kontener kontener2 = new Kontener(50);
            Kontener kontener3 = new Kontener(50);
            Kontener kontener4 = new Kontener(50);
            Kontener kontener5 = new Kontener(50);
            kontener2.zaladuj(cukierek3, 28);
            kontener2.zaladuj(cukierek4, 27);
            kontener3.zaladuj(cukierek5, 27);
            kontener4.zaladuj(cukierek6, 27);
            kontener5.zaladuj(cukierek7, 27);
            
            Magazyn magazyn = new Magazyn();
            magazyn.przyjmijKontener(kontener1);
            magazyn.przyjmijKontener(kontener2);
            magazyn.przyjmijKontener(kontener3);
            magazyn.przyjmijKontener(kontener4);
            magazyn.przyjmijKontener(kontener5);
            //System.out.println(kontener1);
            System.out.println();
            magazyn.show();
            magazyn.wydajKontener();
        }
}
