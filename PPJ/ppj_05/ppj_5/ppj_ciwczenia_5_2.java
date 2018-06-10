package ppj_5;
public class ppj_ciwczenia_5_2 {
    public static void main(String[] args) {
        boolean czyPada = true;
        boolean czySwieciSlonce = false; 
        
        //Zad1
        if (czyPada == true && czySwieciSlonce == false) 
            System.out.println("Plucha");
            else if (czyPada == true && czySwieciSlonce == true) 
                System.out.println("tęcza");
                else if (czyPada == false && czySwieciSlonce == true ) 
                    System.out.println("słonecznie");
                        else
                            System.out.println("pochmurno");
        
        
        //Zad2 
        int zm1 = (czyPada == true) ? (zm1 = 5) : (zm1 = 8);
        
        
        //Zad3
        
        char z = 'A';
        
        if ( z >= '0' && z <= '9') 
            System.out.println(z);
        else
            if ( z >= 'A' && z <= 'F' ) {
                System.out.println(z-'A'+10);
            }
                
        //Zad4
        int x = 0;
        while ( x <= 100 ) {
           if ( x%2 == 0 ) {
               System.out.println(x);
           } 
           x++;
        }
        
        //Zad5
        int s = 0, i = 1;
        while ( i <= 10 ) {
            s = s + i;
            i++;
        }
        
        //Zad6
        int n = 1 ;
        while ( n <= 10 ) {
            System.out.println("Wyraz "+n+" :"+(1/(Math.pow(2,n))));
            n++;
        }
        
        
        
        
        //Zad7
        int licznik = 0;
        int liczba = 2;
        int c = 2;
        
        while ( licznik < 10) {
            while ( liczba%c == 0) {
                if ( liczba == c ) {
                    System.out.println(liczba+" jest pierwsza");
                    licznik++;
                }  
                liczba++;
            } 
             c++;
        }  
        
    }
    
}
