package ppj9_cwiczenia;

public class ppj9 {
    //Zad1
    public static void kwadrat (int s, char c) {
        for ( int i=0;i<s;i++) {
            for ( int j=0;j<s;j++) {
                if (j%2 == 0) {
                    System.out.print(c+" ");
                }
                else if (c == 'x') {
                    System.out.print("o ");
                }
                else {
                    System.out.print("x ");
                }
            }
        System.out.println();
        if (s%2 != 0 && c == 'x') 
            c = 'o';
            else
                c = 'x';
        }    
        
    }
    
    //Zad2
    public static int check ( char[][] tab) {
        int count=0;
        for (int i=0;i<tab.length;i++) {
            for (int j=0;j<tab.length;j++) {
                if ( j>0 & tab[i][j] == 'l' ) {
                    if ( j<tab.length-1 && tab[i][j-1] == 'a' && tab[i][j+1] == 'a')
                        count++;
                    if ( i>0 && i<tab.length-1 && tab[i-1][j] == 'a' && tab[i+1][j] == 'a' ) {
                        count++;}
                    if (j>0 && i>0 && j<tab.length-1 && i<tab.length-1 && tab[i-1][j-1] == 'a' && tab[i+1][j+1] == 'a' ) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    //Zad3
    public static boolean isDiagonal (int [][] tab) {
        boolean check1 = false;
        if (tab.length == tab[0].length) {
            check1 = true;
        }
        boolean check2 = true;
        for (int i=0;i<tab.length;i++) {
            for (int j=0;j<tab[i].length;j++) {
                if ( i != j && tab[i][j] != 0) {
                    check2 = false;
                }
            }
        }
        boolean check = false;
        if (check1 == true && check2 == true) {
            check = true;
        }
        return check;
    }
    
    //Zad4 
    public static int EukliCheck (int a, int b) {
        int nwd=0;

        while ( a != b ) {
        if ( a>b) {
            a -= b;
            nwd = a;
        }
        else {
            b -= a;
            nwd = a;    
        }
        }
        return nwd; 
    }
    
    
    public static void main(String[] args) {
    
    //Zad1
    kwadrat(5,'x');
    
    System.out.println();System.out.println();
    
    //Zad2
    char[] tab[] = new char[100][100];
    for (int i=0;i<tab.length;i++) {
        for (int j=0;j<tab.length;j++) {
            int randomNum = 97 + (int)(Math.random()*25);
            tab[i][j]=(char)(randomNum);
            //System.out.println(randomNum);
        }
    }
    for (int i=0;i<tab.length;i++) {
        for (int j=0;j<tab.length;j++) {
            int randomNum = 97 + (int)(Math.random()*25);
            System.out.print(tab[i][j]+" ");
        }
        System.out.println();
    }
    System.out.println();
    System.out.println(check(tab));
    
    System.out.println();System.out.println();
    //System.out.println(check());
    
    System.out.println();
    //Zad4 
    for (int i=0;i<10;i++) {
            int a =(int)(Math.random()*101);
            int b =(int)(Math.random()*101);
            System.out.print(a+" "+b);
            System.out.println();
            System.out.println(EukliCheck(a,b));
            System.out.println();
        }
        System.out.println();
    }
}

