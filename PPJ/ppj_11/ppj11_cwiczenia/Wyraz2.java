package ppj11_cwiczenia;
    public class Wyraz2 {
        String pole1;
        Wyraz2 pole2;
        
        public Wyraz2(String pole1) {
            this.pole1 = pole1;
        }
        
        public void setNastepnyWyraz( Wyraz2 wyraz) {
            this.pole2 = wyraz;
        }
        
        public void show() {
            System.out.print(this.pole1);
            if (this.pole2 != null ) {
                this.pole2.show();
            }
        }
}
