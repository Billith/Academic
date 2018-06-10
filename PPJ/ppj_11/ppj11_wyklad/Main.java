package ppj11_wyklad;

import ppj9_wyklad.Owoc;

public 
	class Main {
	
	public static void exTest() throws Exception{
		throw new Exception();
	}

	public static void main(String[] args) throws MyStackFullException {
		
		int[] tab = new int[10];
		
		int wrt = 10;
		try{
			System.out.println("Zaczynam try");
			if(Math.random() > 0.5)
				System.out.println(tab[wrt]);
			else
				exTest();
				//System.out.println(1/0);
			System.out.println("Koncze try");
		}catch(ArrayIndexOutOfBoundsException ex1){
			System.out.println("Wychodze poza tablice !!!!");
			System.out.println("przypisz na pozycje"+(tab.length-1));
			System.out.println(tab[tab.length-1]);
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		// =======================================================
		
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
		
		try {
			stos.pop();
		} catch (MyStackEmptyException e) {
			e.printStackTrace();
		}
		
		try {
			stos.pop();
		} catch (MyStackEmptyException e) {
			e.printStackTrace();
		}
		
		try {
			stos.pop();
		} catch (MyStackEmptyException e) {
			e.printStackTrace();
		}
		
	}

}
