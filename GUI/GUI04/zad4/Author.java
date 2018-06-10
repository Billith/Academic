/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad4;

import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Author implements Runnable {
	String[] strTab;
	BlockingQueue<String> que = new ArrayBlockingQueue<String>(1024);
	Thread watek;
	
	public Author(String[] strTab) {
		this.strTab = strTab;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				Thread.currentThread().setName("Autor");
				watek = Thread.currentThread();
				for(int i=0; i<strTab.length; i++) {
					Thread.sleep(0);
					que.put(strTab[i]);
				}
				Thread.currentThread().interrupt();
			}
		} catch (InterruptedException e) {
			return;
		}
	}
}  
