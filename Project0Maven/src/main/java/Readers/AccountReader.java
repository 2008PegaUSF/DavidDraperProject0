package Readers;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import DataObjects.Account;

public class AccountReader {

	// Members
	private static AccountReader ar;
	private File filePath;
	private ArrayList<Account> aList;
	
	// No Arg Constructor
	private AccountReader() {
		this("src/accounts/AccountList.txt");
	}
	
	// Arg Constructor
	private AccountReader(String s) {
		filePath = new File(s);
		aList = new ArrayList<Account>();
		load();
		
	}
	
	public static AccountReader getInstance() {
		if (ar == null) {
			ar = new AccountReader();
		}
		return ar;
	}
	
	// Read accounts from the file
	public void load() {
		try {
			Scanner fileReader = new Scanner(filePath);
			
			while (fileReader.hasNext()) {
				String s = fileReader.nextLine();
				
				aList.add(Account.readFromLine(s));
			}
			
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Post account list to file
	public void post() {
		try {
			FileWriter fw = new FileWriter(filePath);
			
			for(Account a : aList) {
				fw.write(a.readToLine() + '\n');
				a.post();
			}
			
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Add an account to the list
	public void add(Account e) {
		aList.add(e);
	}
	
	// Delete an acount from the list
	public void remove(Account e) {
		aList.remove(e);
	}
	
	public int getNextID() {
		Account a = aList.get((aList.size() - 1));
		int i = a.getID();
		return i + 1;
	}
	
	// Display all
	public void displayAll() {
		for (Account a : aList) {
			System.out.println(a.getID());
		}
	}
	
	// Get an account from an ID
	public Account getByID(int id) {
		for(Account acc : aList) {
			if (acc.getID() == id) {
				return acc;
			}
		}
		
		System.out.println("No Account with ID " + id + " found.");
		return null;
	}
	
	// Get all accounts
	public ArrayList<Account> getAll(){
		return aList;
	}
}
