package ppj10_wyklad;

import ppj9_wyklad.Owoc;

public 
	class Main {

	public static void main(String[] args){
//		A a = new A();
//		a.wrt = 10;
		
		//A.make();
		
		MyStack stos = new MyStackImplementation();
		
		stos.push(
			new Owoc("Sliwka", 1)
		);
		stos.push(
			new Owoc("Gruszka", 5)
		);	
		stos.push(
			new Owoc("Granat", 1000)
		);
		
//		stos.pop();
//		stos.pop();
//		stos.pop();
		
		MyStack tmp = new MyStackImplementation();
		tmp.push(stos.pop());
		Owoc owoc = stos.pop();
		stos.push(tmp.pop());
		
		owoc.show();
		
		stos.pop().show();
	}
}

final class A{
	
	private static A a = null;
	
	private A(){
	}
	
	public static A make(){
		if( a == null)
			a = new A();
		return a;
	}
	
}