/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppj_6;

/**
 *
 * @author Lucas
 */
public class ppj_cwiczenia_6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Zad1
        int[] tab = new int[10];
        for (int i=0; i<tab.length;i++) {
            tab[i]=(int)(Math.random()*2);
            System.out.print(tab[i]+" ");
        }
                System.out.println();
        
        //Zad2
        int count1 = 0;
        int count0 = 0;
        for (int i=0; i<tab.length;i++) {
            if (tab[i]==0)
                count0++;
            else
                count1++;
        }
        System.out.println("Liczba zer : "+count0);
                System.out.println("Liczba jedynek : "+count1);
                
        //Zad3
        int zm1 = (int)(Math.random()*100);
        int[] tab2 = new int[zm1];
        System.out.println(tab2.length);
        
        System.out.println();
        System.out.println("=============================");
        System.out.println();

        //Zad4
        double[] tab3 = new double[10];
        
        for(int i=0; i<tab3.length;i++) {
            tab3[i]=Math.random()*100.0;
            System.out.print(tab3[i]+" ");
        }
       System.out.println();
        for(int i=0; i<tab3.length;i++) {
            tab3[i]=Math.random()*100.0;
            
         if(i%2==0)System.out.print("Index "+i+"="+tab3[i]+" ");
        
        }
        System.out.println();
        System.out.println("=============================");
        System.out.println();
  
        for(int i=0; i<tab3.length;i++) {
            int zm = (int)(tab3[i]);
            if (zm%2!=0) {
            System.out.print(zm+" ");
            }
        }
        
        System.out.println();
        System.out.println("=============================");
        System.out.println();
        //Zad5
        
        int tab4 [ ] = {1 , 0 , 0 , 0 , 2 , 0 , 0 , 0 , 3 };
        int zm3 = 0;
        
            for(int j=0;j<3;j++) {
                for(int k=0;k<3;k++) {
                    System.out.print(tab4[zm3]+" ");
                    zm3++;
                }
                System.out.println();
            }
        System.out.println();
        
        zm3 = tab4.length - 3;
        for(int j=0;j<3;j++) {
            for(int k=0;k<3;k++) {
                    System.out.print(tab4[zm3]+" ");
                    zm3++;
                }
                System.out.println();
                zm3 -= 6;
        }
        
        System.out.println();
        zm3 = tab4.length - 1 ;
            for(int j=0;j<3;j++) {
                for(int k=0;k<3;k++) {
                    System.out.print(tab4[zm3]+" ");
                    zm3--;
                }
                System.out.println();
            }
            
        //Zad7
        System.out.println("============================");
        int tab5 [ ] = { 789 , 678 , 567};
        for ( int i = 0 ; i < tab5.length ; i++){
            for ( int j = i ; j < tab5.length ; j++) {
                System.out.println(tab5[i] - tab5[j]);
            }
        }
        
        //Zad8
        System.out.println("============================");
        
        int[][] tab8 = {
            { 1, 0, 0 },
            { 0, 1, 0 },
            { 0, 0, 1 }
        };
        int zm4 = 0;
        
        for( int i = 0; i < tab8.length; i++) {
            for ( int j = 0; j < 3; j++ ) {
                if ( i!=j ) { 
                    if ( tab8[i][j] != 0 ) 
                        zm4++;         
                 }
                else 
                    if ( tab8[i][j] == 0 ) {
                        zm4++;
                    }
            }
            
        } 
        if( zm4 > 0 ) 
            System.out.println("Nie jest to tablica diagonalna");
                else
                    System.out.println("Jest to tablica diagonalna");
        
        System.out.println("============================");
        //Zad9
         int[][] tab9 = new int[5][5];
         
        for( int i=0; i<5; i++ ) {
            for ( int j=0; j<5; j++) {
                tab9[i][j]=(int)(Math.random()*10);
                System.out.print(tab9[i][j]+" "); 
            }
        System.out.println();
        }
         
        System.out.println();
        
        for( int i=0; i<5; i++ ) {
            for ( int j=0; j<5; j++) {
                tab9[i][j]=(int)(Math.random()*10);
                System.out.print(tab9[i][j]+" "); 
            }
        System.out.println();
        }
         
         
        
        
        
    }
    
}
