package ppj_4;
public class ppj_wyklad_4 {
    public static void main(String[] args) {
         
        //   for ( int i = 0; i< 6; i++) {
        //     System.out.println("test");
        // }
        
        
        for ( int j=1; j<10; j++ ) {
            for ( int i = 1; i < 10; i++ ) {
                System.out.print(i*j+" ");
            }
            System.out.println();
        }
        
       int czerwony = 1;
        int color;
        do {
            color = (int)(Math.random()*4);
            System.out.println("Wylosowano: "+color);
        } while (color != 1);
        
        int ileLiczbWylosowac = 10;
        for(int i=0; i < ileLiczbWylosowac; i++ ) {
            System.out.println(Math.random()*100);
        }
        
        double[] tab = { 1.0, 2.0, 3.0 };
        
        for(int i=0; i < tab.length; i++) {
            System.out.println(tab[i]);
        }
        
    }
    
}
