package Users;

import java.util.ArrayList;
import java.util.Scanner;

import DataObjects.Account;
import Readers.AccountReader;
import Readers.CustomerReader;

public class Admin extends User{

	CustomerReader cr;
	
	// No Arg Constructor
	public Admin() {
		this(1000, "John", "Kolassa");
	}
	
	public Admin(int id, String firstName, String lastName) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
		this.ar = AccountReader.getInstance();
		this.cr = CustomerReader.getInstance();
		
		this.aList = ar.getAll();
	}
	
	// Add an account to the list
	public void add(Account a) {
		aList.add(a);
	}
	// cancel an account in the list
	public void cancel(Account a) {
		int i = aList.indexOf(a);
		aList.remove(i);
	}
	
	// Return all accounts
	public ArrayList<Account> getAll(){
		return aList;
	}
	
	// Post
	public void post() {
		ar.post();
	}
	
	@Override
	public String readToLine() {
		StringBuilder s = new StringBuilder();
		s.append(id+" "+firstName+" "+lastName);
		
		for (Account acc : aList) {
			s.append(" " + acc.getID());
		}
		
		return s.toString();

	}
	
	public static Admin readFromLine(String s) {
		Scanner stringReader = new Scanner(s);
		
		int id = stringReader.nextInt();
		String firstName = stringReader.next();
		String lastName = stringReader.next();
		
		Admin a = new Admin(id,firstName,lastName);
		
		stringReader.close();
		
		return a;	
	}
	
	public int getID() {
		return id;
	}

}
