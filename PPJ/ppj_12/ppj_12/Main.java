package ppj_12;
    public class Main {
        public static void main(String[] args) {
        
        //Zad1    
        System.out.println("Zmiena1 : "+A.x);
        System.out.println("Zmiena2 : "+A.zm2);
        
        //A.x = 31; 
        
        A.zm2 = true;
        
        System.out.println("Zmiena1 : "+A.x);
        System.out.println("Zmiena2 : "+A.zm2);
        
        //Zad2
        System.out.println();
        new B();
        
        System.out.println();
        //Zad3
        C c = new C("Text",3,5);
        c.display();
        C cc = new C("Tekst",6,10);
        cc.display();
        C ccc = new C("Qwerty",12,20);
        ccc.display();
        c.display();
        
        System.out.println();
        //Zad4
        String zdanie = "Ala ma kota";
        System.out.println(zdanie.charAt(2));
        System.out.println(zdanie.charAt(4));
        
        System.out.println("Index litery k : "+zdanie.indexOf('k'));

        System.out.println();
        String words[] = zdanie.split(" ");
        for (int i=0;i<words.length;i++){
            System.out.println(words[i]);
        }
        
        //Zad5
        Walec walec1 = new Walec (6,10);
        Kwadrat kwadrat1 = new Kwadrat(10);
        
        KulaW kula1 = new KulaW(walec1);
        KulaW kula2 = new KulaW(kwadrat1);
        
        System.out.println(kula1.promien+" "+kula2.promien);
        
        //Zad6
        KulaNa kula3 = new KulaNa(walec1);
        KulaNa kula4 = new KulaNa(kwadrat1);
        
        System.out.println(kula3.promien+" "+kula4.promien);
        
    }
    
}
