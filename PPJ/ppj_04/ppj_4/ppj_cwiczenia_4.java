package ppj_4;
public class ppj_cwiczenia_4 {
    public static void main(String[] args) {
        //Zad 2
        double sqTwo = Math.sqrt(2);
        //Math.pow(x,y) x do potęgi y
        double zm1 = (sqTwo*sqTwo)-2; //4.44...*10 do -16 potęgi;
        
        System.out.println((zm1!=0)?"Przewidywania nie sprawdziły się":"Przewidywania sprawdziły się");
        System.out.println(zm1);
        
        //Zad3 moje
        
        System.out.println("=================================");
        
        int color = 1651300;
        
        String colorBinary = Integer.toBinaryString(color);
        
        String colorBinary1 = colorBinary.substring(colorBinary.length() - 8);
        int G = Integer.parseInt(colorBinary1, 2);
        
        String colorBinary2 = colorBinary.substring(0, colorBinary.length() - 8 );
        String colorBinary3 = colorBinary2.substring(colorBinary2.length() - 8);
        
        int B = Integer.parseInt(colorBinary3, 2);
        
        String colorBinary4 = colorBinary2.substring(0, colorBinary2.length() - 8 );
        int R = Integer.parseInt(colorBinary4, 2);
        
        System.out.println(R+"."+B+"."+G);
        System.out.println("R  G  B");
        
        //Zad3
        
        int color1 = 1651300;
        
        System.out.println("R: "+((color1 >> 16) & 255));
        System.out.println("G: "+((color1 >> 8) & 255));
        System.out.println("B: "+(color1 & 255));
        
        //Zad5
        
        float x = -14.3f;
        
        if ( x > -15 && x < -13 || x < -4 && x > -5) 
            System.out.println("x znajduje się w częsci wspólnej");
        else 
            System.out.println("x nie znajduje się w części wspólnej");
        
        //Zad6
        //String[] tab = { "Truskawkowy", "Malinowy", "Brzoskwiniowy"};
        
        int sloik = (int)(Math.random()*3);
        System.out.println(sloik);
        switch(sloik) {
            case 0 : System.out.println("Truskawkowy");break;
            case 1 : System.out.println("Malinowy");break;
            case 2 : System.out.println("Brzoskwiniowy");break;
            default: System.out.println("Error");break;
        }
        
        //Zad4
        
        //float y = 14.2f;
        
        /*
        System.out.println(
                          (y < -8 ) ? 
                                ((y > -13 ) ? 
                                        (( y > - 10 ) ? "Należy do zbioru C" : "Należy do zbioru AC") : 
                                            ((y > -15) ? "Należy do ABC" : "Należy do BC") : 
                                                ((y > -3 && y < 0 && y > 5 && y < 10) ? "Nalezy do zbioru A" : 
                                                    (( y > -4) ? "Nalezy do AB" : 
                                                        (( y > -5 ) ? "Nalezy do ABC" : "Nalezy do BC") : ))));
        */
        
        //Zad7
        byte a = 40 ; 
        byte b = 50;
        int suma = a + b ;
        System.out.println ( suma ) ;
    
        
        //Zad8
        int xx = 4;
        long yy = xx * 4 - ++xx;
        if ( yy<10) System . out . println ( "za mało "+xx+yy ) ;
            else System . out . println ( "w sam raz "+xx+yy ) ;
        
        //Zad9
        boolean xxx = true, zzz = true;
        int yyy =20;
        xxx = ( yyy!=20) ^ (zzz = false);
        System.out.println( xxx+", "+yyy+", "+zzz);
        
        
        
    }
    
}
