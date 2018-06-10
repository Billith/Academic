package ppj9_wyklad;

public 
	class Owoc {
	
	String nazwa;
	int iloscPestek;
	
	public Owoc(String nazwa, int iloscPestek){
		this.nazwa = nazwa;
		this.iloscPestek = iloscPestek;
	}
	
	public Owoc(String nazwa){
		this( nazwa, 5);
	}	
	
	public boolean equals(Owoc owoc){
		if(nazwa.length() != owoc.nazwa.length())
			return false;
		for(int i=0; i<nazwa.length(); i++)
			if(nazwa.charAt(i) != owoc.nazwa.charAt(i))
				return false;
		
		if(
			//nazwa.equals(owoc.nazwa) && 
			this.iloscPestek == owoc.iloscPestek
		)
			return true;
		return false;
	}

}
