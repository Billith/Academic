package ppj9_wyklad;

public 
	class MyLista {

	Element poczatek = null;
	
	public void addElement(Owoc owoc){
		if(poczatek == null){
			Element e = new Element();
			e.owoc = owoc;
			poczatek = e;
		}else{
			Element tmp = poczatek;
			while(tmp.nastepny != null){
				tmp = tmp.nastepny;
			}
			
			Element e = new Element();
			e.owoc = owoc;
			tmp.nastepny = e;
		}
	}
	
	public void show(){
		Element temp = poczatek;
		while(temp != null){
			System.out.println(temp);
			System.out.println(temp.owoc.nazwa);
			
			temp = temp.nastepny;
		}
	}
}
