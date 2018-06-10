package ppj_7;
public class ppj_cwiczenia_7 {
    public static void main(String[] args) {
       
    //Zad1
        String[] slowa = {
            "Ala", "kota", "ma", "ma", "a", "kot", "Ale"
        };
        
    System.out.print(slowa[0]+" "+slowa[2]+" "+slowa[1]+" "+slowa[4]+" "+slowa[5]+" "+slowa[3]+" "+slowa[6]);    
    
    System.out.println();
    
    //Zad2
    
    char[] tab1 = {
        'A', 'l', 'a', 'm', 'a', 'k', 'o', 't', 'a' 
    };
    int wynik = 0;
    for (int i=0; i<tab1.length;i++) {
        if(tab1[i]=='a') {
            wynik++;
        }
    }
    
    System.out.println("Liczba znaków a: "+wynik);
    
    //Zad3
    
    int[] tab2 = new int[20];
    
    for (int k=0; k<19;k++) {
        tab2[k]=(int)(Math.random()*100);
    }
    
    for (int l=18;l>10;l--) {
        tab2[l+1]=tab2[l];
    }
    tab2[10]=-1;
    
    for (int j=0;j<tab2.length;j++) {
        System.out.print(tab2[j]+" ");
    }
    System.out.println();
    System.out.println();
    //Zad4
    
    int[] tab3 = new int[10];
    
    for (int k=0; k<9;k++) {
        tab3[k]=(int)(Math.random()*2);
    }
    
    int zm2=0;
    for (int o=1;o<9;o++) {
        if (tab3[o]==0 && tab3[o-1]==1 && tab3[o+1]==1) {
            zm2++;
        }
    }
    System.out.println(zm2);
    for (int j=0;j<tab3.length;j++) {
        System.out.print(tab3[j]+" ");
    }
    
    System.out.println();  
    System.out.println();
    //Zad5
    char[] slowo = {
        's', 'l', 'o', 'w', 'o'
    };
    
    for (int u=0;u<slowo.length;u++) {
        slowo[u] = (char)(slowo[u]+u);
    }
    System.out.print("Oryginalne : ");
    for(int w=0;w<slowo.length;w++) {
        System.out.print((char)(slowo[w]-w));
    }
    System.out.println();
    System.out.print("Szyfr : ");
    for(int t=0;t<slowo.length;t++) {
        System.out.print(slowo[t]);
    }
    
    System.out.println();
    System.out.println();
    
    //Zad6
    int[] tab61 = new int[(int)((Math.random()*10)+1)];
    int[] tab62 = new int[(int)((Math.random()*10)+1)];
    int[] tab63 = new int[(int)((Math.random()*10)+1)];
    
    for (int y=0; y<tab61.length;y++) {
        tab61[y]=(int)(Math.random()*100);
    }
    
    for (int r=0; r<tab62.length;r++) {
        tab62[r]=(int)(Math.random()*100);
    }
    
    for (int q=0; q<tab63.length;q++) {
        tab63[q]=(int)(Math.random()*100);
    }
    
    
    
    int[] tab6[] = {tab61, tab62, tab63 };
    /*
    for (int e=0;e<tab61.length;e++) {
        tab6[0][e] = tab61[e];
    }
    
    for (int p=0;p<tab62.length;p++) {
        tab6[0][p] = tab62[p];
    }
    
    for (int h=0;h<tab63.length;h++) {
        tab6[0][h] = tab63[h];
    }
    */
    for (int i=0; i<tab6.length;i++) {
        for (int j=0;j<tab6[i].length;j++) {
            System.out.print(tab6[i][j]+" ");
        }
        System.out.println();
    }
}
}
    
