package ppj_8.ppj_wyklad_8;
public
	class Owoc{
		
	public static int cos;
	
	public String nazwa;
	public int iloscPestek;
	
	public Owoc(String nazwa, int ip){
		System.out.println("Hello nowy owoc !!!");
		this.nazwa = nazwa;
		iloscPestek = ip;
		System.out.println(this);
	}
	
	public Owoc(boolean b, int ip){
		this(b?"Gruszka" : "Sliwka", ip);
	}	
	
	public void przedstaw(){
		System.out.println(
			nazwa + " iloscPestek: " + iloscPestek
		);
	}
	
	public String toString(){
		return nazwa + " iloscPestek: " + iloscPestek;
	}
}