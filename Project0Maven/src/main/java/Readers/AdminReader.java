package Readers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Users.Admin;

public class AdminReader {

	// Members
	File filePath;
	ArrayList<Admin> aList;
	
	// No Arg Constructor
	public AdminReader() {
		this("src/users/AdminList.txt");
	}
	
	// Arg Constructor
	public AdminReader(String s) {
		filePath = new File(s);
		aList = new ArrayList<Admin>();
		load();
	}
	
	// Read accounts from the file
	public void load() {
		try {
			Scanner fileReader = new Scanner(filePath);
				
			while (fileReader.hasNext()) {
				String s = fileReader.nextLine();
				
				aList.add(Admin.readFromLine(s));
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
			
			for(Admin a : aList) {
				fw.write(a.readToLine() + '\n');
				a.post();
			}
			
			fw.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Add a customer
	public void add(Admin a) {
		aList.add(a);
	}
	
	// Get an account from an ID
	public Admin getByID(int id) {
		for(Admin a : aList) {
			if (a.getID() == id) {
				return a;
			}
		}
		
		System.out.println("No Account with ID " + id + " found.");
		return null;
	}
	
	public static void main(String[] args) {
		
	}
}
