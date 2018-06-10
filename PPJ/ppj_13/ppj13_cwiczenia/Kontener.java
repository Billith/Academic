package ppj13_cwiczenia;
    public class Kontener {
        Cukierek ladunek;
        double masaLadunku;
        int dniPrzydatnosci;
        
        public Kontener (double masaLadunku) {
            this.masaLadunku = masaLadunku;
        }
        
        public void zaladuj (Cukierek c,int dniPrzydatnosci) {
            this.ladunek = c;
            this.dniPrzydatnosci = dniPrzydatnosci;
        }
        
        public boolean sprawdzPrzydatnosc () {
            boolean check;
            check = this.dniPrzydatnosci >= 0;
            return check;
        }
}
