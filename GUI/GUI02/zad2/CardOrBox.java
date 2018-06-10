package zad2;

import java.util.ArrayList;

abstract class CardOrBox {
	ArrayList<Flower> customerList = new ArrayList<>();
	double worth = 0;
	String owner;
	
	public CardOrBox() {
	}
	
	@Override
	public String toString() {
		String instance;
		if(this instanceof Box) 
			instance = "Pudełko";
			else 
				instance = "Wózek";
		String str = instance+" własciciel "+this.owner;
		if (this.customerList != null) str += "\n";
		try {
			for(Flower f : this.customerList) {
				double priceTmp;
				if(PriceList.getInstance().cennik.get(f.name) == null) {
					priceTmp = -1;
					} else {
						priceTmp = PriceList.getInstance().cennik.get(f.name);
					}
				//str += f.name+", kolor: "+f.color+", ilość "+f.amount+", cena "+priceTmp;
				str += f+", cena "+priceTmp;
				if(this.customerList.indexOf(f)+1 != this.customerList.size()) {
					str += "\n";
				}
			}
		} catch (NullPointerException e) {
			str += " -- pusto";
		}
		return str;
	}
}