package ppj_5;
public class ppj_cwiczenia_5_1 {
    public static void main(String[] args) {
        int n = 1 ;
        double suma = 0;
        while ( n <= 10 ) {
            System.out.println("Wyraz "+n+" :"+(suma=(suma+(1/(Math.pow(2,n))))));
            n++;
        }
        
        int licznik = 0;
        int liczba = 2;
        int c = 2;
        while ( licznik < 10) {
            while ( liczba%c == 0) {
                if ( liczba == c )  {
                    System.out.println(liczba+" jest pierwsza");
                    licznik++;
                }
            c++;
            }
        liczba++;
        }
    }
}
