package ppj13_cwiczenia;
    public class Magazyn {
        Element poczatek = null;
        int Rozmiar=0;
        
        public void przyjmijKontener(Kontener kontener) {
            if(poczatek == null) {
                Element e = new Element();
                e.kontener = kontener;
                poczatek = e;
                Rozmiar++;
            }
            else {
                Element tmp = poczatek;
                while(tmp.nastepny != null) {
                    tmp = tmp.nastepny;
                }
                Element ele = new Element();
                ele.kontener = kontener;
                tmp.nastepny = ele;
                Rozmiar++;
            }
        }
        
        public void show() {
            Element temp = poczatek;
            
		while(temp != null){
			System.out.println(temp.kontener);
			System.out.println("Next: "+temp.nastepny);
			temp = temp.nastepny;
                        
                        System.out.println();
		}
            System.out.println("Ilosc elementow: "+Rozmiar);    
        }
        
        public void wydajKontener() {
            Element tmp = poczatek;
            if(Rozmiar > 0) {
                int i=0;
                while(i < Rozmiar-1) {
                        tmp = tmp.nastepny;
                        i++;
                    }
                tmp.nastepny = null;
                }else{
                    System.out.println("Magazyn jest pusty");
                }    
            }
}
