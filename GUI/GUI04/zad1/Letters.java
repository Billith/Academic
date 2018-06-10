package zad1;

import java.util.ArrayList;
import java.util.List;

public class Letters {
	List<Thread> threads = new ArrayList<>();
	String str;
	
	public Letters(String str) {
		this.str = str;
		for(int i=0;i<this.str.length();i++) {
			String threadName = "Thread "+this.str.charAt(i);
			Thread one = new Thread(new LetterThread(this.str.charAt(i)));
//			Thread one = new Thread() {
//				public void run() {
//					while (true) {
//						try {
//							Thread.currentThread();
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							return;
//						}
//					}
//				}
//			};
			one.setName(threadName);
			threads.add(one);
		}
	}
	
	public List<Thread> getThreads() {
		return threads;
	}
}
