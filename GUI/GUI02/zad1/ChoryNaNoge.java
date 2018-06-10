package zad1;

public class ChoryNaNoge extends Pacjent {
	final String choroba = "noga";
	final String leczenie = "gips";
	public ChoryNaNoge(String imie) {
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
