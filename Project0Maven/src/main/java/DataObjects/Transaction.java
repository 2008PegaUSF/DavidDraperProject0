package DataObjects;

import java.util.Scanner;

public abstract class Transaction {
	protected int amount;
	
	public abstract String readToLine();
	
	public static Transaction readFromLine(String s) {
		Scanner lineReader = new Scanner(s);
		
		String type = lineReader.next(); // Read in the type and amount of the transaction
		int amount = lineReader.nextInt();
		
		lineReader.close();
		
		if (type.equals("Deposit")) { // Determine what kind of transaction to create
			return(new Deposit(amount));
		} else {
			return(new Withdrawal(amount));
		}
		
	}
	
	public int getAmount() {
		return amount;
	}
}
