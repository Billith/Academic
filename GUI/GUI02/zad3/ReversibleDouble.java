package zad3;

public class ReversibleDouble implements Reversible {
	double value;
	
	public ReversibleDouble(double value) {
		this.value = value;
	}
	
	@Override
	public Reversible reverse() {
		value = 1/value;
		return this;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
