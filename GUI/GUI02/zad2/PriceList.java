package zad2;

import java.util.HashMap;

public class PriceList {
	private static PriceList instance = null;
	HashMap<String, Double> cennik = new HashMap<>(); 
	protected PriceList() {
		
	}
	
	public static  PriceList getInstance() {
		if(instance == null) {
			instance = new PriceList();
		}
		return instance;
	}
	
	public void put(String flower, Double price) {
		if (price>=0) {
			cennik.put(flower, price);
		}
	}

}
