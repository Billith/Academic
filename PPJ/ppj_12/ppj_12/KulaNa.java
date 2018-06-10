package ppj_12;
    public class KulaNa {
        double promien;
        
        public KulaNa(Walec walec)  {
            if (walec.promien*2>walec.wysokosc)
                this.promien = Math.sqrt((4*Math.pow(walec.promien, 2)+Math.pow(walec.wysokosc, 2)))/2;
                        else    
                this.promien = Math.sqrt((4*Math.pow(walec.wysokosc, 2)+Math.pow(walec.promien, 2)))/2;
        }
        
        public KulaNa(Kwadrat kwadrat) {
            this.promien = (double)(kwadrat.bok)*Math.sqrt(3);    
        }
}
