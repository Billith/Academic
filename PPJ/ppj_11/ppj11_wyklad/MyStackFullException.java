package ppj11_wyklad;

public 
	class MyStackFullException 
	extends Exception{

	public MyStackFullException(){
		super("Stos jest pelny");
	}
}
