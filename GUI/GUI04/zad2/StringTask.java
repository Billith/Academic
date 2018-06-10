package zad2;

public class StringTask implements Runnable {
	
	String letter;
	int amount;
	volatile TaskState state;
	String str = "";
	Thread TaskThread;
	boolean ready = false;
	
	public StringTask(String letter, int amount) {
		this.letter = letter;
		this.amount = amount;
		this.state = TaskState.CREATED;
	}

	public TaskState getState() {
		return this.state;
	}

	public void start() {
		this.TaskThread = new Thread(this); 
		this.TaskThread.setName("TaskThread");
		this.TaskThread.start();
		this.state = TaskState.RUNNING;
	}
	
	public void abort() {
		this.state = TaskState.ABORTED;
		this.TaskThread.interrupt();
	}

	public boolean isDone() {
		if (this.TaskThread.isAlive() == false && (this.state == TaskState.READY || this.state == TaskState.ABORTED)) {
			ready = true;
		}
		return ready;
	}

	public String getResult() {
		return str.toString();
	}

	@Override
	public void run() {
		for(int i=0; i<this.amount; i++) {
			try {
				str += this.letter;
				Thread.currentThread();
				Thread.sleep(0); 
			} catch (InterruptedException e) {
				return;
			}
		}
		this.state = TaskState.READY;
	}
}
