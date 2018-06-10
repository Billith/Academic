package ppj11_wyklad;

public 
	class MyStackEmptyException
	extends Exception{

	public MyStackEmptyException(){
		super("Stos jest pusty");
	}
}
