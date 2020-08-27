package DataObjects;

import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Readers.TransactionReader;

public class Account {
	
	// Members
	private int id;
	private int balance;
	private TransactionReader tr;
	
	// No Arg Constructor
	public Account() {
		this(0, 0, "TransactionTest");
	}
	
	// Arg Constructor
	public Account(int id, int balance, String filePath) {
		this.id = id;
		this.balance = balance;
		tr = new TransactionReader("src/accounts/" + filePath + ".txt");
	}
	
	// Print balance to the screen
	public void displayBalance() {
		System.out.println("Balance: " + balance);
	}
	
	// Set the balance from transaction history
	public void setBalance() {
		balance = tr.getBalance();
	}
	
	// Return the account id
	public int getID() {
		return id;
	}
	
	// Add money to the account
	public void deposit(int i) {
		tr.add(new Deposit(i));
		balance += i;
		
		Logger log = LogManager.getLogger(TransactionReader.class);
		PropertyConfigurator.configure("log4j.properties");
		log.debug("Account #" + id + ": Deposit $" + i);
	}
	
	// Subtract money from the account
	public void withdraw(int i) {
		tr.add(new Withdrawal(i));
		balance -= i;
		
		Logger log = LogManager.getLogger(TransactionReader.class);
		PropertyConfigurator.configure("log4j.properties");
		log.debug("Account #" + id + ": Withdraw $" + i);
	}
	
	// Post the transactions to account history
	public void post() {
		tr.post();
	}	
	
	//Convert the object into a string
	public String readToLine() {
		return (id + " " + balance);
	}
	
	// get an account object from a string
	public static Account readFromLine(String s) {
		Scanner stringReader = new Scanner(s);
		
		int id = stringReader.nextInt();
		int balance = stringReader.nextInt();
		
		stringReader.close();
		
		return new Account(id,balance,"account" + id);
	}
	
	public boolean equals(Account a) {
		return (id == a.getID());
	}
}
