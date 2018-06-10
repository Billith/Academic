package ppj9_wyklad;

public 
	class Main {
	

	
	public static void main(String[] args){
		
		A myObj = new A();
		myObj.sprawdz(myObj);
		
		A myB = new A();
		myB.sprawdz(myB);

		myObj.sprawdz(myB);
		
		// =====================================
		
		Owoc sliwka1 = new Owoc("Sliwka", 1);
    		Owoc sliwka2 = new Owoc(args[0], 1);
		
		System.out.println(
			sliwka1.equals(sliwka2)
		);
		
		// ======================================
		
		MyLista ml = new MyLista();
		
		
		ml.addElement(sliwka1);
		ml.addElement(sliwka2);
		ml.addElement(sliwka1);
		ml.addElement(sliwka2);		
		ml.show();
		
		MyLista ml1 = new MyLista();

	}
}


class A{
	public void sprawdz(A a){
		System.out.println(a);
		System.out.println(this);
		
		if( a == this)
			System.out.println("Jest taki sam");
		else
			System.out.println("Nie jest taki sam");
	}
}