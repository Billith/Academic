package ppj_12;
    public class KulaW {
        int promien;
        public KulaW (Walec walec) {
            this.promien = walec.promien;
        }
        
        public KulaW (Kwadrat kwadrat) {
            this.promien = kwadrat.bok/2;
        }
}
