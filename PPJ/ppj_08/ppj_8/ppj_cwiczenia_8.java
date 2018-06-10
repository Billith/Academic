package ppj_8;

import java.util.concurrent.TimeUnit;

public class ppj_cwiczenia_8 {
    public static void wyswietl(int[] data ) {
    for (int i=0;i<data.length;i++) {
        System.out.print(data[i]+" ");
    }
    System.out.println();
    }
    
    public static void wyswietl(char[][] data) {
        for(int i=0;i<data.length;i++) {
            for(int j=0;j<data[i].length;j++) {
                System.out.print(data[i][j]+" ");
            }
        System.out.println();
        }
    }
    public static void wyswietl(char[] data) {
        for(int i=0;i<data.length;i++) {
            System.out.print(data[i]+" ");
    }
    }    
    
    public static void MyMethod(int zm) {
        zm++;
        System.out.println(zm);
    }
    public static void MyMethod(double zm) {
        zm--;
        System.out.println(zm);
    }
    
    public static boolean check(char[] tab) {
        boolean wynik=true;
            for (int i=0;i<=tab.length/2;i++) {
                if (tab[i] != tab[tab.length-i-1]) {
                    wynik=false;
                }
            } 
        return wynik;
    }
    
    public static void sortuj(int[] data){
		for( int j = 0; j < data.length-1; j++){
			int minValIndex = j;
			for(int i=j+1; i<data.length; i++){
				if(data[i] < data[minValIndex])
					minValIndex = i;
			}		
			{
			int tmp = data[j];
			data[j] = data[minValIndex];
			data[minValIndex] = tmp;
			}
		}		
    }
    
    public static int fibonacci(int n) {
        if (n==1) {
            return 1;
        }
        else if (n==2) {
            return 2;
        }
        else {
        int f = fibonacci(n-1)+fibonacci(n-2);
        return f;
        }
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        //Zad1
        int[][] tab1 = new int[8][8];
        
        //System.out.println(tab1[1].length);
        for (int i=0;i<tab1.length;i++) {
            for (int j=0;j<tab1[i].length;j++) {
                tab1[i][j]=(int)(Math.random()*10);
            }
        }
        
        for (int i=0;i<tab1.length;i++) {
            for (int j=0;j<tab1[i].length;j++) {
                System.out.print(tab1[i][j]+" ");
            }
            System.out.println();
        }
        
        int licznik[] = new int[10];
        
        for (int i =0; i < tab1.length ; i++){
            licznik[tab1[i][i]]++;
                licznik[tab1[i][7-i]]++;
        }
        System.out.println();
        for (int i=0;i<licznik.length;i++) 
            System.out.print(licznik[i]+" ");
        System.out.println();
        System.out.println();
        for(int i=0;i<licznik.length;i++) {
            if(licznik[i]>=3) {
                System.out.println(i+" powtarza się "+licznik[i]+" razy");
            } 
            }
        
        System.out.println();System.out.println();System.out.println();
        
        
        //Zad2
        
        double tab2[] = new double[3];
        double tab3[] = new double[3];
        
        for (int j=0;j<tab2.length;j++) {
                tab2[j]=Math.random()*5;
        }
        
        for (int j=0;j<tab3.length;j++) {
                tab3[j]=Math.random()*5;
        }
        
        double tab4[] = new double[3];
        int zm1 = 2;
        for(int i=0;i<tab4.length;i++) {
            tab4[i]=tab2[i]+tab3[2-i];
            //zm1--;
        }
        
        for (int i=0;i<tab2.length;i++) {
            System.out.print(tab2[i]+" ");
        }
        System.out.println();
        for (int i=0;i<tab3.length;i++) {
            System.out.print(tab3[i]+" ");
        }
        System.out.println();
        System.out.println();
        for (int i=0;i<tab4.length;i++) {
            System.out.print(tab4[i]+" ");
        }
        System.out.println();System.out.println();
        //Zad3
        char[][] tab5 = {
            {'S' , 'a' , 'm' , 's' , 'u' , 'n' , 'g' } ,
            {'N' , 'o' , 'k' , 'i' , 'a' } ,
            {'A' , 'p' , 'p' , 'l' , 'e' } ,
            {'B' , 'l' , 'a' , 'c' , 'k' , 'B' , 'e' , 'r' , 'r' , 'y' } ,
            {'A' , 'l' , 'c' , 'a' , 't' , 'e' , 'l' } ,
            {'S' , 'o' , 'n' , 'y' } ,
            {'J' , 'o' , 'l' , 'l' , 'a' }
        };
        char tmp;
        int zm2=0;
        for (int i=0;i<tab5.length;i++) {            
            for (int j=0;j<tab5[i].length;j++) {
                tmp=tab5[i][j];
                for(int k=j+1;k<tab5[i].length;k++) {
                    if (tmp==tab5[i][k]) {
                        zm2++;
                    }
                }
                if (zm2==1) {
                    System.out.println("Wiersz: "+(i)+" - powtorzenie znaku "+tmp);
                    zm2=0; 
                }  
            }
        } 
        
        //Zad4
        int[] tab8 = new int[(int)(Math.random()*20)+1];

        for (int p=0;p<tab8.length;p++) {
            tab8[p]=(int)(Math.random()*100);
        }
        
        wyswietl(tab8);
        sortuj(tab8);
        
        int[] tab7 = new int[(2*tab8.length)];
        int zm = tab8.length-1;
        for (int i=0;i<tab7.length;i++) {
            if (i<tab8.length)
            tab7[i]=tab8[i];
            else
            tab7[i]=tab8[zm--];
        }
        
        wyswietl(tab7);
        System.out.println();System.out.println();
        
        
        //Zad5
        int[] tab10[] = new int [10][10];
        for (int i=0;i<tab10.length;i++) {
            for (int j=0;j<tab10[i].length;j++) {
                tab10[i][j]=(int)(Math.random()*90+10);
            }
        }
        for (int i=0;i<tab10.length;i++) {
            for (int j=0;j<tab10[i].length;j++) {
                System.out.print(tab10[i][j]+" ");
            }
            System.out.println();
        }
        
        for (int w=0;w<tab10.length;w++) {
            for (int k=0;k<tab10[w].length-1;k++) {
                int mvi = k;
                for (int i=k+1;i<tab10[w].length;i++) {
                    if (tab10[w][i]<tab10[w][mvi]) {
                        mvi = i;
                    }
                }
                    int tmp1 = tab10[w][k];
                    tab10[w][k] = tab10[w][mvi];
                    tab10[w][mvi] = tmp1;
                
            }
        }
        
        System.out.println();
        
        for (int i=0;i<tab10.length;i++) {
            for (int j=0;j<tab10[i].length;j++) {
                System.out.print(tab10[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        
        //Zad6
        char[][] tab = {
            {'o','-','-','-','-','-','-','-','-','-'},
            {'x','x','x','x','x','x','x','x','x','-'},
            {'-','-','-','-','-','-','-','-','x','-'},
            {'-','x','x','x','x','x','x','-','x','-'},
            {'-','x','e','-','-','-','-','-','x','-'},
            {'-','x','x','x','x','x','x','x','x','-'},
            {'-','-','-','-','-','-','-','-','-','-'}
           };

        int pozycjaX = 0 , pozycjaY = 0 ;
        
        wyswietl(tab);
        
        for(int i=0;i<tab[0].length-1;i++) {
            char tmpi = tab[0][i+1];
            tab[0][i+1]=tab[0][i];
            tab[0][i]=tmpi;
            wyswietl(tab);
            //TimeUnit.SECONDS.sleep(1);
            System.out.println();
            pozycjaX++;
            System.out.println("X: "+pozycjaX);
            System.out.println("Y: "+pozycjaY);
        }
        for(int i=0;i<tab.length-1;i++) {
            char tmpi = tab[i+1][tab[i+1].length-1];
            tab[i+1][tab[i+1].length-1]=tab[i][tab[i].length-1];
            tab[i][tab[i].length-1]=tmpi;
            wyswietl(tab);
            //TimeUnit.SECONDS.sleep(1);
            System.out.println();
            pozycjaY++;
            System.out.println("X: "+pozycjaX);
            System.out.println("Y: "+pozycjaY);
        }
        for(int i=tab[6].length-1;i>0;i--) {
            char tmpi = tab[6][i-1];
            tab[6][i-1]=tab[6][i];
            tab[6][i]=tmpi;
            wyswietl(tab);
            //TimeUnit.SECONDS.sleep(1);
            System.out.println();
            pozycjaX--;
            System.out.println("X: "+pozycjaX);
            System.out.println("Y: "+pozycjaY);
        }
        for(int i=tab.length-1;i>2;i--) {
            char tmpi = tab[i-1][0];
            tab[i-1][0]=tab[i][0];
            tab[i][0]=tmpi;
            wyswietl(tab);
            //TimeUnit.SECONDS.sleep(1);
            System.out.println();
            pozycjaY--;
            System.out.println("X: "+pozycjaX);
            System.out.println("Y: "+pozycjaY);
        }        
        for(int i=0;i<tab[2].length-3;i++) {
            char tmpi = tab[2][i+1];
            tab[2][i+1]=tab[2][i];
            tab[2][i]=tmpi;
            wyswietl(tab);
            //TimeUnit.SECONDS.sleep(1);
            System.out.println();
            pozycjaX++;
            System.out.println("X: "+pozycjaX);
            System.out.println("Y: "+pozycjaY);
        }
        for(int i=2;i<tab.length-3;i++) {
            char tmpi = tab[i+1][tab[i+1].length-3];
            tab[i+1][tab[i+1].length-3]=tab[i][tab[i].length-3];
            tab[i][tab[i].length-3]=tmpi;
            wyswietl(tab);
            //TimeUnit.SECONDS.sleep(1);
            System.out.println();
            pozycjaY++;
            System.out.println("X: "+pozycjaX);
            System.out.println("Y: "+pozycjaY);
        }
        for(int i=tab[4].length-3;i>0;i--) {
            char tmpi = tab[4][i-1];
            if (tmpi=='e') {
                tab[4][i-1]=tab[4][i];
                tab[4][i]='-';
                wyswietl(tab);
                System.out.println();
                pozycjaX--;
                System.out.println("X: "+pozycjaX);
                System.out.println("Y: "+pozycjaY);
                break;
            }
            tab[4][i-1]=tab[4][i];
            tab[4][i]=tmpi;
            wyswietl(tab);
            //TimeUnit.SECONDS.sleep(1);
            System.out.println();
            pozycjaX--;
            System.out.println("X: "+pozycjaX);
            System.out.println("Y: "+pozycjaY);
        }
        
        System.out.println();
        
        //Zad7
        char zm3 = 'x';
        MyMethod(zm3);
        MyMethod((double)(zm3));       
        
        //Zad8
        char[] tab11 = {
            'b','o','o','b',
        };
        System.out.println(check(tab11));
        
        //Zad9
        System.out.println(fibonacci(40));

        //Zad10 Nie wiem jak to zrobić
        char[] tab12 = {
            'x','d','o','k','j','5'
        };
        
        for (int i=0;i<tab12.length;i++) {
            for (int j=i+1;j<tab12.length-1;j++) {
                char tmpc = tab12[j];
                tab12[j]=tab12[i];
                tab12[i] = tmpc;
                wyswietl(tab12);
                System.out.println();
            }
        System.out.println();    
        }
        
}
}
