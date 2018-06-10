package ppj_3;
 public class ppj_wyklad_3 {
     public static void main(String[] args) {
         //final int wrt = 5;
         
         //Konunkcja bitowa(&)
         System.out.println("3 & 5 = "+ (3 & 5));
         
         //Alternatywa bitowa
         System.out.println("3 | 5 = "+ (3 | 5));
         
         //xor Daszek^
         System.out.println("3 ^ 5 = "+ (3 ^ 5));
         
         //przesuniecia bitowe
         System.out.println("3 << 1 = "+ (3 << 1));
         System.out.println("3 >> 1 = "+ (3 >> 1));
         
         int wrt = 3;
         wrt = wrt << 1;
         System.out.println(wrt);
         wrt = wrt >> 1;
         System.out.println(wrt);
         
         wrt = 1;
         wrt = wrt << 30;
         System.out.println(wrt);
         
         wrt = 1;
         wrt = wrt << 31;
         System.out.println(wrt);
         
         wrt = wrt >> 31;
         System.out.println(wrt);
         
         //Potrójny przesuwa bit znaku
         wrt = wrt >>> 31;
         System.out.println(wrt);
         
         System.out.println ( true && true);
         System.out.println ( true || true);
         
         int x = (int)(Math.random()*30) - 15;
         System.out.println(x);
         
         if((x < 7) && (x > 5)) {
             System.out.println("Zbiór A");
         }
         
         final int czerwony = 1;
         final int zielony = 2;
         final int niebieski = 3;
         int color = (int)(Math.random()*3);
         
        if (color == czerwony) 
            System.out.println("czerwony");
         if (color == zielony) 
             System.out.println("czerwony");
         if (color == niebieski) 
             System.out.println("czerwony");
         
         color = czerwony + czerwony;
         
         switch(color) {
             case czerwony : System.out.println("czerwony");break;
             case zielony : System.out.println("zielony");break;
             case niebieski : System.out.println("niebieski");break;
             //case czerwony | zielony : System.out.println("fioletowy");break;
             default: System.out.println("XD");break;
         }
         
         
         
     }
     
 }