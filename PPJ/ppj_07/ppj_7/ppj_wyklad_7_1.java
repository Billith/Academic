package ppj_7;
public
	class ppj_wyklad_7_1{
		
	//public static int wrt;
	
	static void metoda1(){
		System.out.println("Hello metoda1");
	}

	static int metoda2(){
		System.out.println("Hello metoda2");
		boolean b = false;
		if(b)
			return 2;
		return 5;
	}
	
	static void metoda3(int wrt1){
		System.out.println("wrt1: "+wrt1);
		wrt1 = wrt1 + 2;
		System.out.println("wrt1: "+wrt1);
	}
	
	static void metoda4(int[] wrt1){
		System.out.println("wrt1: "+wrt1[0]);
		wrt1[0] = wrt1[0] + 2;
		System.out.println("wrt1: "+wrt1[0]);
	}	
	
	static int metodaSum(int wrt1, int wrt2){
		return wrt1 + wrt2;
	}
	
	static int metodaSum(double wrt1, double wrt2, double... wrt3 ){
		System.out.println("TU");
		return (int)(wrt1 + wrt2);
	}	
	
	static void metoda5(int wrt1, int... wrt){
		System.out.println("metoda 5"+wrt.length);
		System.out.println("metoda 5"+wrt[0]);
	}
	static void metoda5(int wrt1, int wrt){
		System.out.println("TU2");
		//System.out.println("metoda 5"+wrt.length);
		//System.out.println("metoda 5"+wrt[0]);
	}
	
	public static void main(String[] args){
		metoda1();
		int rezultatMetody2 = metoda2();
		System.out.println("Res: "+rezultatMetody2);
		
		int mojaZmienna = 8;
		System.out.println(mojaZmienna);
		metoda3(mojaZmienna);
		System.out.println(mojaZmienna);
		
		int[] myTab = {8};
		System.out.println(myTab[0]);
		metoda4(myTab);
		System.out.println(myTab[0]);
		
		System.out.println(
			metodaSum( 6, 7)
		);
		
		System.out.println(
			metodaSum( metodaSum( 6, 7), metodaSum( 6, 7))
		);	
		
		System.out.println(
			metodaSum( 7.0, 7.0)
		);	
		
		System.out.println(
			metodaSum( 7.0, 7)
		);	

		metoda5(5, 6, 7, 8, 9, 10);
		metoda5(1, 2);
	}
	
}






