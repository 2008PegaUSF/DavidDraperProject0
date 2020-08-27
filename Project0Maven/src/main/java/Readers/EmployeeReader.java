package Readers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Users.Employee;

public class EmployeeReader {

	// Members
	File filePath;
	ArrayList<Employee> eList;
	
	// No Arg Constructor
	public EmployeeReader() {
		this("src/users/EmployeeList.txt");
	}
	
	// Arg Constructor
	public EmployeeReader(String s) {
		filePath = new File(s);
		eList = new ArrayList<Employee>();
		load();
	}
	
	// Read accounts from the file
	public void load() {
		try {
			Scanner fileReader = new Scanner(filePath);
				
			while (fileReader.hasNext()) {
				String s = fileReader.nextLine();
				
				eList.add(Employee.readFromLine(s));
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
			
			for(Employee e : eList) {
				fw.write(e.readToLine() + '\n');
				e.post();
			}
			
			fw.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Add a customer
	public void add(Employee e) {
		eList.add(e);
	}
	
	// Get an account from an ID
	public Employee getByID(int id) {
		for(Employee e : eList) {
			if (e.getID() == id) {
				return e;
			}
		}
		
		System.out.println("No Account with ID " + id + " found.");
		return null;
	}
	
	public static void main(String[] args) {
		
	}
}
