package zad1;

import java.util.Iterator;

public class Hailstone implements Iterable<Integer> {
	int value;
	int firstValue;
	
	public Hailstone(int start) {
		this.value = start * 2;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
				
			boolean check = true;
			
			public boolean hasNext() {
				if(value == 1) 
					check = false;
				return check;
			}
			
			public Integer next() {
				if(value%2 == 0) 
					value = value/2;
				else
					value = 3 * value + 1;
				return value;
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
