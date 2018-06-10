package ppj11_cwiczenia;
    public class test {
        public static void main(String[] args) {
            
            //Zad1
            Kwadrat kwadrat1 = new Kwadrat(5);
            kwadrat1.show();
            
            System.out.println();
            
            //Zad2
            Walec walec1 = new Walec(5,10);
            walec1.show();
            
            System.out.println();
            
            //Zad3
            kwadrat1.przygotujWalec().show();
            
            System.out.println();
          
            //Zad4
            Wyraz wyraz = new Wyraz();
            System.out.println("Wyraz1 "+wyraz);
            wyraz.dodajZnak('a');
            System.out.println("Wyraz1 "+wyraz);
            System.out.println(wyraz.length());
            
            System.out.println();
            //Zad5
            
            Wyraz2 wyraz1 = new Wyraz2("Ala ");
            Wyraz2 wyraz2 = new Wyraz2("ma ");
            Wyraz2 wyraz3 = new Wyraz2("kota ");
            Wyraz2 wyraz4 = new Wyraz2("a ");
            Wyraz2 wyraz5 = new Wyraz2("kot ");
            Wyraz2 wyraz6 = new Wyraz2("ma ");
            Wyraz2 wyraz7 = new Wyraz2("Ale");
            
            wyraz1.setNastepnyWyraz(wyraz2);
            wyraz2.setNastepnyWyraz(wyraz3);
            wyraz3.setNastepnyWyraz(wyraz4);
            wyraz4.setNastepnyWyraz(wyraz5);
            wyraz5.setNastepnyWyraz(wyraz6);
            wyraz6.setNastepnyWyraz(wyraz7);

            wyraz1.show();
            System.out.println();
        }
        
}
