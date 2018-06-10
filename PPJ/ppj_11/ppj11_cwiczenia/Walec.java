package ppj11_cwiczenia;
    public class Walec {
        int promien;
        int wysokosc;
        
        public Walec( int promien, int wysokosc) {
            this.promien = promien;
            this.wysokosc = wysokosc;
        }
        
        public void show() {
            int PolePodst = 2*promien;
            int Objetosc = promien*promien*wysokosc;
            System.out.println("Pole powierzchni "+PolePodst);
            System.out.println("Objetosc "+Objetosc+"PI");
        }
        
}
