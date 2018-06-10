package ppj10_cwiczenia;
public class ZbiorMetod {
    
    //Zad1
    public void ustalWartosc (int zm1 ) {
        System.out.println("Int = "+zm1);
        zm1++;
        System.out.println("Int po = "+zm1);
    }
    
    public void ustalWartosc (float zm1 ) {
        System.out.println("Float = "+zm1);
        zm1++;
        System.out.println("Float po = "+zm1);
    }
    
    public void ustalWartosc ( Liczba liczba ) {
        liczba.wW();
        liczba.pW(100);
        liczba.wW();
    }
    
}
