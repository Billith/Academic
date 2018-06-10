package ppj10_cwiczenia;
    public class Osoba {
        public String imie;
        public String nazwisko;
        public int rokUro;
    
    public Osoba () {
        
    }    
        
    public Osoba (String imie,String nazwisko,int rokUro) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rokUro = rokUro;
    }
    
    public void show() {
        System.out.println(imie);
        System.out.println(nazwisko);
        System.out.println(rokUro); 
    }   
}
