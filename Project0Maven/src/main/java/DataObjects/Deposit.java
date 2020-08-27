package DataObjects;

public class Deposit extends Transaction {
	public Deposit() {
		amount = 0;
	}
	
	public Deposit(int amount) {
		this.amount = amount;
	}
	
	public String readToLine() {
		return ("Deposit " + amount);
	}
	
	public String toString() {
		return readToLine();
	}
	
	public boolean equals(Deposit d) {
		return (amount == d.getAmount());
	}
}
