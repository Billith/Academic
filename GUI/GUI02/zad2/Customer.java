package zad2;

import java.util.ArrayList;

public class Customer {
	String name;
	double balance = 0;
	ShoppingCart wozek = new ShoppingCart();
	
	public Customer(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	
	public void get(Flower flower) {
		wozek.customerList.add(flower);
	}
	
	public void pay() {
		ArrayList<Integer> removeableItems = new ArrayList<>();
		double worthTmp = this.wozek.worth;
		for (Flower f : this.wozek.customerList) {	
			try {
				worthTmp = this.wozek.worth + (f.amount * PriceList.getInstance().cennik.get(f.name));
			} catch (NullPointerException e) {
				removeableItems.add(this.wozek.customerList.indexOf(f));
			}
			if(this.balance >= worthTmp) {
				this.wozek.worth = worthTmp;
			} else {
				removeableItems.add(this.wozek.customerList.indexOf(f));
			}
		}
		for (Integer i : removeableItems) {
			this.wozek.customerList.remove(i.intValue());
		}
	}
	
	public double getCash() {
		return this.balance - this.wozek.worth;
	}
	
	public void pack(Box b) {
		b.customerList = this.wozek.customerList;
		this.wozek.customerList = null;
		b.owner = this.wozek.owner;
		b.worth = this.wozek.worth;
	}

	public ShoppingCart getShoppingCart() {
		wozek.owner = this.name;
		return wozek;
	}
}
