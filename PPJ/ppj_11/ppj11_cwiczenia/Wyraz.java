package ppj11_cwiczenia;
    public class Wyraz {
        String pole1;
        int dlugosc;
        
        public Wyraz() {
            this.pole1 = "";
            this.dlugosc = 0;
        }
        
        public void dodajZnak(char znak) {
            this.pole1 += znak;
            this.dlugosc++;    
        }
        
        public String toString() {
            return this.pole1;
        }
        
        public int length() {
            return this.dlugosc;
        }
}
