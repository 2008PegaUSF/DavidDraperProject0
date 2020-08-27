package Users;

import java.util.ArrayList;
import java.util.Scanner;

import DataObjects.AccessRequest;
import DataObjects.Account;
import DataObjects.AccountRequest;
import Readers.AccountReader;
import Readers.RequestReader;

public class Customer extends User {
	
	// Members
	RequestReader rr;
	
	// No Arg Constructor
	public Customer() {
		this(1000, "Dave", "Draper");
	}
	
	// Arg Constructor
	public Customer(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
		this.aList = new ArrayList<Account>();
		this.ar = AccountReader.getInstance();
		this.rr = RequestReader.getInstance();
	}
	
	// Add an account to the list
	public void add(Account a) {
		aList.add(a);
	}
	
	// Remove an account from the list
	public void remove(Account a) {
		aList.remove(a);
	}
	
	// Display Account List
	public void displayAccountList() {
		System.out.println("Account List:");
		for (Account a : aList) {
			System.out.println(a.getID());
		}
	}
	
	// View Balance
	public void viewBalance(Account a) {
		a.displayBalance();
	}
	
	// Get an account from an ID
	public Account getAccount(int id) {
		for(Account acc : aList) {
			if (acc.getID() == id) {
				return acc;
			}
		}
			
		System.out.println("No Account with ID " + id + " found.");
		return null;
	}
	
	// Return all accounts
	public ArrayList<Account> getAll(){
		return aList;
	}
	
	// Post
	public void post() {
		ar.post();
	}
	
	// Request a new account
	public void request() {
		AccountRequest ar = new AccountRequest(id);
		rr.add(ar);
		
	}
	
	// Request a new account
	public void requestAccess(int account) {
		AccessRequest ar = new AccessRequest(id,account);
		rr.add(ar);
	}
	
	// Return the ID
	public int getID() {
		return id;
	}
	
	// Read the data in the object to a string
	public String readToLine() {
		StringBuilder s = new StringBuilder();
		s.append(id+" "+firstName+" "+lastName);
		
		for (Account acc : aList) {
			s.append(" " + acc.getID());
		}
		
		return s.toString();
	}
	
	// Create a customer out of a string
	public static Customer readFromLine(String s) {
		Scanner stringReader = new Scanner(s);
		
		int id = stringReader.nextInt();
		String firstName = stringReader.next();
		String lastName = stringReader.next();
		
		Customer c = new Customer(id,firstName,lastName);
		
		while (stringReader.hasNext()) {
			c.add(c.ar.getByID(stringReader.nextInt()));
		}
		
		stringReader.close();
		
		return c;	
	}
	
	public static void main(String[] args) {

	}
}
