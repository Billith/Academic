package ppj13_cwiczenia;
    public class Fabryka {
        private static Cukierek c;
        
        public static Cukierek make (int Rodzaj) {    
            if (c == null) {
                c = new Cukierek("waniliowy",10);
                return c;
            }
            else 
            return c; 
        }
}
