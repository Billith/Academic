package ppj_7;
public
	class ppj_wyklad_7_2{
		
	static void metoda1(){
		System.out.println("Hello metoda1");
		metoda2();
	}
	
	static void metoda2(){
		System.out.println("Hello metoda2");
		//metoda1();
		//return;
	}	
	
	static int metoda3(){
		return 3;
	}
	
	static int metoda4(){
		boolean b = true;
		if(b)
			return 3;
		
		return 5;
	}
	
	static void metoda5(int a){
		System.out.println("a: "+a);
	}
	
	static void metoda6(int a){
		System.out.println("a: "+a);
		a = a + 2;
		System.out.println("a: "+a);
	}	
	
	static void metoda7(int[] a){
		System.out.println("a: "+a[0]);
		a[0] = a[0] + 2;
		System.out.println("a: "+a[0]);
	}

	static int metodaSum( int wrt1, int wrt2){
		System.out.println("TU1");
		return wrt1 + wrt2;
	}
	
	static int metodaSum( double wrt1, double wrt2){
		System.out.println("TU2");
		return (int)(wrt1 + wrt2);
	}	
	
	static int metodaSum(int wrt1, int... wrt2){
		System.out.println("TU3: "+wrt2.length);
		System.out.println("TU3: "+wrt2[0]);
		int sum = wrt1;
		//for(int i : wrt2)
		for( int i=0; i<wrt2.length; i++)
			sum += wrt2[i];
		return sum;
	}
	//static int metodaSum(int... wrt2, int wrt1){
	
	static int space = 0;
	
	static void myPrint(int level, int wrt){
		System.out.print("Fib level: "+level);
		for(int i=0; i<space; i++)
			System.out.print(' ');	
		System.out.println(wrt);
	}
	
	static int fib(int n){
		if( n == 0)
			return 0;
		if( n == 1)
			return 1;
		space += 2;
		
		int lval = fib(n-1);
		int rval = fib(n-2);
		myPrint( n, lval+rval);
		
		return lval+rval;
	}
	
	public static void main(String[] args){
		metoda1();
		int rezultatMetody = metoda3();
		System.out.println(rezultatMetody);
		
		metoda5(6);
		
		int mojaZmienna = 8;
		System.out.println(mojaZmienna);
		metoda6(mojaZmienna);
		System.out.println(mojaZmienna);
		
		int[] mojaTab = {8};
		System.out.println(mojaTab[0]);
		metoda7(mojaTab);
		System.out.println(mojaTab[0]);
		
		metoda6(mojaTab[0]);
		
		System.out.println(
			metodaSum( 6, 7)
		);
		
		System.out.println(
			metodaSum( 6.0, 7.0)
		);
		
		System.out.println(
			metodaSum( 6.0, 7)
		);
		
		System.out.println(
			metodaSum( 2, 3)
		);
		
		System.out.println(
			metodaSum( 2, 3, 5, 6, 7, 8)
		);	

		System.out.println(
			metodaSum( metodaSum( 6.0, 7), metodaSum( 2, 3))
		);		
		
		fib(7);
	}
		
}