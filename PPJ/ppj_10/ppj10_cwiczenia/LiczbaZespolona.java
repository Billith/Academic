package ppj10_cwiczenia;
    public class LiczbaZespolona {
        private double rzecz;
        private double uroj;
        
        public LiczbaZespolona (double rzecz, double uroj ) {
            this.rzecz = rzecz;
            this.uroj = uroj;
        }
        
        public void dodaj(LiczbaZespolona liczbazesp ) {
            this.rzecz += liczbazesp.rzecz;
            this.uroj += liczbazesp.uroj;
        }
        
        public void odejmij(LiczbaZespolona liczbazesp ) {
            this.rzecz -= liczbazesp.rzecz;
            this.uroj -= liczbazesp.uroj;
        }
        
        public void pomnoz(LiczbaZespolona liczbazesp) {
            this.rzecz *= liczbazesp.rzecz;
            this.uroj *= liczbazesp.uroj;
        }
        
        public double zwieksz() {
            rzecz++;
            return rzecz;
        }
        
        public void wyswietl() {
            System.out.println((int)(rzecz)+" + "+(int)(uroj)+" * i");
        }
}
