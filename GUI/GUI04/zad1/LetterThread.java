package zad1;

public class LetterThread implements Runnable {
	char znak;
	
	public LetterThread (char znak) {
		this.znak = znak;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep(1000);
				System.out.print(znak);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
