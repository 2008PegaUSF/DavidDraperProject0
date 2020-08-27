package DataObjects;

public class Withdrawal extends Transaction {
	public Withdrawal() {
		amount = 0;
	}
	
	public Withdrawal(int amount) {
		this.amount = amount;
	}
	
	public String readToLine() {
		return ("Withdrawal " + amount);
	}
	
	public String toString() {
		return readToLine();
	}
	
	public boolean equals(Withdrawal w) {
		return (amount == w.getAmount());
	}
}
