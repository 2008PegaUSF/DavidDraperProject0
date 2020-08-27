package Readers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import DataObjects.Transaction;

public class TransactionReader {
	
	// Members
	private File filePath;
	private ArrayList<Transaction> tList;

	// No Arg Constructor
	public TransactionReader() {
		this("src/accounts/TransactionTest.txt");
	}
	
	// Arg Constructor
	public TransactionReader(String s) {
		filePath = new File(s);
		tList = new ArrayList<Transaction>();
		load();
	}
	
	// Read transaction history from the file
	public void load() {
		try {
			Scanner fileInput = new Scanner(filePath);
			while(fileInput.hasNext()) {
				String s = fileInput.nextLine();
				
				tList.add(Transaction.readFromLine(s));
			}
			
			fileInput.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Post transaction history to the file
	public void post() {
		try {
			FileWriter fw = new FileWriter(filePath);
			
			for(Transaction t : tList) {
				fw.write(t.readToLine() + '\n');
			}
			
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Add a transaction to the list
	public void add(Transaction e) {
		tList.add(e);
	}
	
	// Return the balance of current transactions
	public int getBalance() {
		int balance = 0;
		
		for (Transaction t : tList) {
			if (t instanceof DataObjects.Deposit) {
				balance += t.getAmount();
			}
			else {
				balance -= t.getAmount();
			}
		}
		
		return balance;
	}
	
	// return all transactions
	public ArrayList<Transaction> getAll(){
		return tList;
	}
}
