package zad3;

public class Towar {
	String id;
	String name;
	int weigth;
	static int globalWeigth;
	
	public Towar(String id, String name, int weigth) {
		this.id = id;
		this.name = name;
		this.weigth = weigth;
		globalWeigth += weigth;
	}
}
