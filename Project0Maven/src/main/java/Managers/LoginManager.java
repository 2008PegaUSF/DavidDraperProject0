package Managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Readers.CustomerReader;
import Users.Customer;
import Users.Login;

public class LoginManager {
	
	// Members
	private String type;
	private File filePath;
	private ArrayList<Login> lList;
	
	public LoginManager(String type) {
		this.type = type;
		filePath = new File("src/logins/"+type+"Logins.txt");
		lList = new ArrayList<Login>();
		load();
	}
	
	// Login as an existing user
	public int login(String username, String password) {
		
		for (Login l : lList) {
			if (username.equals(l.getUsername()) && password.equals(l.getPassword())) {
				System.out.println("Successfully logged into account #" + l.getId() + "!");
				return l.getId();
			}
		}
				
		System.out.println("No login " + type + " found with given credentials.");
		return -1;
	}
	
	// Register as a new user
	public int register(String username, String password, String firstName, String lastName) {
		int highestID = 999;
		
		for (Login l : lList) {
			if (username.equals(l.getUsername())) {
				System.out.println("Username: " + username + " is already taken.");
				return -1;
			}
			highestID = l.getId();
		}
		int newID = highestID + 1;
		
		Login l = Login.readFromString(username + " " + password + " " + newID);
		lList.add(l);
		
		System.out.println("Account #" + newID + " created successfully!");
		
		CustomerReader cr = CustomerReader.getInstance();
		Customer c = Customer.readFromLine(newID + " " + firstName + " " + lastName);
		
		cr.add(c);
		cr.post();
		
		post();
		return newID;
	}
	
	// Read in all login credentials from the file
	public void load() {
		try {
			Scanner fileReader = new Scanner(filePath);
			
			while (fileReader.hasNext()) {
				Login l = Login.readFromString(fileReader.nextLine());
				lList.add(l);
			}
			
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// Read out all login credentials to the file
	public void post() {
		try {
			FileWriter fw = new FileWriter(filePath);
			
			for (Login l : lList) {
				fw.write(l.readToLine() + "\n");
			}
			
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		LoginManager lm = new LoginManager("Customer");
		
		lm.login("davemd", "aBadPassword");
		lm.login("jackbw", "aWorsePassword");
		lm.register("rachelaf", "thisPasswordIsBetterThanAllTheOthersDueToItsExcessiveLength1123","Rachel","Feldhausen");
	}
	
}
