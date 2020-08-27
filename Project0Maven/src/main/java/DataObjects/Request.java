package DataObjects;

import java.util.Scanner;

public abstract class Request {
	protected int id;
	
	public abstract String readToLine();
	
	public int getID() {
		return id;
	}
	
	public static Request readfromline(String s) {
		Scanner lineReader = new Scanner(s);
		
		String[] arr = s.split(" ");
		
		lineReader.close();

		if (arr.length > 1) { // Determine what kind of transaction to create
			return(new AccessRequest(Integer.parseInt(arr[0]),Integer.parseInt(arr[1])));
		} else {
			return(new AccountRequest(Integer.parseInt(arr[0])));
		}
		
	}
	
}