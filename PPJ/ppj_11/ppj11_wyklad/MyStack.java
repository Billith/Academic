package ppj11_wyklad;

import ppj9_wyklad.Owoc;

public 
	interface MyStack {

	public void push(Owoc owoc) throws MyStackFullException;
	public Owoc pop() throws MyStackEmptyException;
	
}
