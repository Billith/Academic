package ppj10_wyklad;

import ppj9_wyklad.Owoc;

public 
	class MyStackImplementation 
	implements MyStack{

	private Owoc tabOwoc[];
	private int size;
	private int count;
	
	public MyStackImplementation(){
		size = 2;
		count = 0;
		tabOwoc = new Owoc[size];
	}

	public void push(Owoc owoc) {
		if(count  < size){
			tabOwoc[count] = owoc;
			count += 1;
		}else
			System.out.println("Stos jest pelny");
	}

	public Owoc pop() {
		if(count - 1 >= 0){
			count -= 1;
			Owoc owoc = tabOwoc[count];
			tabOwoc[count] = null;
			return owoc;
		} else {
			System.out.println("Stos jest pusty");
		}
		return null;	
	}
	
}
