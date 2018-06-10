package zad1;

public class Account {
	double balance;
	static double InterestRate;
	
	public Account () {
		this.balance = 0;
	}

	public void deposit(int amount) {
		if(amount > 0) {
			this.balance += amount;
		}
	}
	
	public void withdraw(int amount) {
		if (amount > 0) {	
			if(this.balance >= amount) {
				this.balance -= amount;
			}
		}
	}
	
	public void transfer(Account target, int amount) {
		if (this.balance >= amount) {
			if (amount > 0) {
				this.balance -= amount;
				target.balance += amount;
			}
		}	
	}
	
	public static void setInterestRate(double procent) {
		InterestRate = procent;
	}
	
	public void addInterest() {
		this.balance += (this.balance*(InterestRate/100));
	}
}
