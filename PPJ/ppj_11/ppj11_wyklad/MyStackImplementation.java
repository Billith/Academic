package ppj11_wyklad;

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

	public void push(Owoc owoc) throws MyStackFullException{
		
		// Nie tak robic !!!
		
		try{
		//if(count  < size){
			tabOwoc[count] = owoc;
			count += 1;
		//}else
		}catch(Exception ex){
			throw new MyStackFullException();
		}
	}

	public Owoc pop() throws MyStackEmptyException{
		if(count - 1 >= 0){
			count -= 1;
			Owoc owoc = tabOwoc[count];
			tabOwoc[count] = null;
			return owoc;
		} else {
			throw new MyStackEmptyException();
			//System.out.println("Stos jest pusty");
		}
		//return null;	
	}
	
}
