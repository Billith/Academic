package zad2;

public abstract class Flower {
	int amount;
	String color;
	String name;
	
	public Flower(int amount, String color, String name) {
		this.amount = amount;
		this.color = color;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name+", kolor: "+this.color+", ilość "+this.amount;
	}
}
