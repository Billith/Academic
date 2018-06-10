package ppj_12;
    public class C {
        static String zm1;
        int pole1;
        int pole2;
        
        public C (String zm1, int pole1, int pole2) {
            this.zm1 = zm1;
            this.pole1 = pole1;
            this.pole2 = pole2;
        }
        
        public void display() {
            System.out.println("Zm1 = "+this.zm1);
            System.out.println("Pole1 = "+this.pole1);
            System.out.println("Pole2 = "+this.pole2);
        }
    }
