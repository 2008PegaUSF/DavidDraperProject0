package Readers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import DataObjects.Account;
import Users.Customer;

public class CustomerReader {

	// Members
	public static CustomerReader cr;
	private File filePath;
	private ArrayList<Customer> cList;
	
	// No Arg Constructor
	private CustomerReader() {
		this("src/users/CustomerList.txt");
	}
	
	// Arg Constructor
	private CustomerReader(String s) {
		filePath = new File(s);
		cList = new ArrayList<Customer>();
		load();
	}
	
	public static CustomerReader getInstance() {
		if (cr == null) {
			cr = new CustomerReader();
		}
		return cr;
	}
	
	// Read accounts from the file
	public void load() {
		try {
			Scanner fileReader = new Scanner(filePath);
				
			while (fileReader.hasNext()) {
				String s = fileReader.nextLine();
				
				cList.add(Customer.readFromLine(s));
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
			
			for(Customer c : cList) {
				fw.write(c.readToLine() + '\n');
				c.post();
			}
			
			fw.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Add a customer
	public void add(Customer e) {
		cList.add(e);
	}
	
	// Remove an account
	public void removeAccount(Account a) {
		for (Customer c : cList) {
			c.remove(a);
		}
	}
	
	// Get an account from an ID
	public Customer getByID(int id) {
		for(Customer c : cList) {
			if (c.getID() == id) {
				return c;
			}
		}
		
		System.out.println("No Account with ID " + id + " found.");
		return null;
	}
	
	public static void main(String[] args) {

	}
}
