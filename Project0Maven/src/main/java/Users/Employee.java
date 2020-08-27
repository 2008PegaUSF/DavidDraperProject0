package Users;

import java.util.ArrayList;
import java.util.Scanner;

import DataObjects.AccessRequest;
import DataObjects.Account;
import DataObjects.AccountRequest;
import DataObjects.Request;
import Readers.AccountReader;
import Readers.CustomerReader;
import Readers.RequestReader;

public class Employee extends User{

	CustomerReader cr;
	RequestReader rr;
	
	// No Arg Constructor
	public Employee() {
		this(1000, "John", "Kolassa");
	}
	
	public Employee(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	
		this.ar = AccountReader.getInstance();
		this.cr = CustomerReader.getInstance();
		this.rr = RequestReader.getInstance();
		
		this.aList = ar.getAll();
	}
	
	// Add an account to the list
	public void add(Account a) {
		aList.add(a);
	}

	// Return all accounts
	public ArrayList<Account> getAll(){
		return aList;
	}
	
	// Approve Request
	public void approve(Request r) {
		// Grant access to and old request
		if (r instanceof AccessRequest) {
			Customer c = cr.getByID(r.getID());
			Account a = ar.getByID(((AccessRequest) r).getAccountID());
			c.add(a);
		// Create a new account and give the customer access
		} else if (r instanceof AccountRequest) {
			Customer c = cr.getByID(r.getID());
			int accountNum = ar.getNextID();
			Account a = Account.readFromLine(accountNum + " " + 0);
			
			c.add(a);
			add(a);
		}
		rr.nullify(r);
	}
	
	// Deny Request
	public void deny(Request r) {
		rr.nullify(r);
	}
	
	// Post
	public void post() {
		ar.post();
		rr.post();
	}
	@Override
	public String readToLine() {
		StringBuilder s = new StringBuilder();
		s.append(id+" "+firstName+" "+lastName);
		
		
		return s.toString();

	}
	public int getID() {
		return id;
	}

	public static Employee readFromLine(String s) {
		Scanner stringReader = new Scanner(s);
		
		int id = stringReader.nextInt();
		String firstName = stringReader.next();
		String lastName = stringReader.next();
		
		Employee e = new Employee(id,firstName,lastName);
		
		stringReader.close();
		
		return e;	
	}

}
