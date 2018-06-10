package zad1;

public class ChoryNaGlowe extends Pacjent {
	final String choroba = "głowa";
	final String leczenie = "aspiryna";
	public ChoryNaGlowe(String imie) {
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
