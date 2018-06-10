package ppj13_cwiczenia;
    public class Cukierek {
        String smak;
        double waga;
        
        public Cukierek (String smak, double waga) {
            this.smak = smak;
            this.waga = waga;
        }
        
        public void show () {
            System.out.println("Smak: "+this.smak);
            System.out.println("Waga: "+this.waga);
        }
}
