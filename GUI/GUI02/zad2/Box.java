package zad2;

public class Box extends CardOrBox {
	public Box(Customer c) {
		super();
	}
//	public String toString() {
//		String str = super.toString();
//		if (this.customerList != null) str += "\n";
//		try {
//			for(Flower f : this.customerList) {
//				double priceTmp;
//				if(PriceList.getInstance().cennik.get(f.name) == null) {
//					priceTmp = -1;
//					} else {
//						priceTmp = PriceList.getInstance().cennik.get(f.name);
//					}
//				//System.out.println(f.name+", kolor: "+f.color+", ilość: "+f.amount+" , cena "+priceTmp);
//				str += f.name+", kolor: "+f.color+", ilość "+f.amount+", cena "+priceTmp;
//				if(this.customerList.indexOf(f)+1 != this.customerList.size()) {
//					str += "\n";
//				}
//			}
//		} catch (NullPointerException e) {
//			str += " -- pusto";
//		}
//		return str;
//	}
}
