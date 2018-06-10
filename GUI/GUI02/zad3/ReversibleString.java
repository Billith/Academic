package zad3;

public class ReversibleString implements Reversible {
	String phrase;
	String reversedPhrase = "";
	
	public ReversibleString(String phrase) {
		this.phrase = phrase;
	}
	
	@Override
	public Reversible reverse() {
		for(int i=phrase.length()-1;i>=0;i--) {
			reversedPhrase += phrase.charAt(i);
		}
		phrase = reversedPhrase;
		reversedPhrase = "";
		return this;
	}
	
	@Override
	public String toString() {
		return phrase;
	}
}
