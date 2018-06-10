package ppj_12;

    public class Kwadrat {
        int bok;
        
        public Kwadrat( int bok) {
            this.bok = bok;
            
        }
        
        public void show () {
            int PolePow = bok*bok;
            int Objetosc = bok*bok*bok;
            System.out.println("Dlugość boku "+" "+this.bok);
            System.out.println("Pole powierzchni "+" "+PolePow);
            System.out.println("Objetość "+" "+Objetosc);
        }
        
        public Walec przygotujWalec() {
            int promien = bok/2;
            Walec walec = new Walec(promien,bok);
            return walec;
        }
}
