/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad4;

import java.util.Set;

public class Writer implements Runnable {
	Author author;
	
	public Writer(Author author) {
		this.author = author;
	}
	
	@Override
	public void run() {
		try {
			Thread.currentThread().setName("Writer");
			while(true) {
				Thread.sleep(1000);
				System.out.println(author.que.take());
				if(author.que.isEmpty()) {
					Thread.currentThread().interrupt();
				}
			}
			
		} catch (InterruptedException e ) {
			return;
		}
	}
}
