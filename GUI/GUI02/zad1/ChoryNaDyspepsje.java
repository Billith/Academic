package zad1;

public class ChoryNaDyspepsje extends Pacjent {
	final String choroba = "dyspepsja";
	final String leczenie = "węgiel";
	public ChoryNaDyspepsje(String imie) {
		super(imie);
	}
	
	@Override
	public String choroba() {
		return choroba;
	}
	
	@Override
	public String leczenie() {
		return leczenie;
	}
}
