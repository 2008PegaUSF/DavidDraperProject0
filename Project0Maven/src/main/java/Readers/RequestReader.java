package Readers;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import DataObjects.Request;
import DataObjects.AccountRequest;
import DataObjects.AccessRequest;

public class RequestReader {
	
	// Members
	private static RequestReader rr;
	private File filePathNew;
	private File filePathAccess;
	private ArrayList<Request> rList;
	
	private RequestReader() {
		this("src/requests/AccountRequests.txt","src/requests/AccessRequests.txt");
	}
	
	private RequestReader(String s, String t) {
		filePathNew = new File(s);
		filePathAccess = new File(t);
		rList = new ArrayList<Request>();
		load();
	}
	
	public static RequestReader getInstance() {
		if (rr == null) {
			rr = new RequestReader();
		}
		return rr;
	}
	
	public void load() {
		try {
			Scanner accountInput = new Scanner(filePathNew);
			Scanner accessInput = new Scanner(filePathAccess);
			
			while (accountInput.hasNext()) {
				String s = accountInput.nextLine();
				
				rList.add(Request.readfromline(s));
			}
			
			while (accessInput.hasNext()) {
				String s = accessInput.nextLine();
				
				rList.add(Request.readfromline(s));
			}
			
			accountInput.close();
			accessInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void post() {
		for (int i = rList.size()-1; i >= 0; i--) {
			if (rList.get(i) == null) {
				rList.remove(i);
			}
		}
		
		try {
			FileWriter accountfw = new FileWriter(filePathNew);
			FileWriter accessfw = new FileWriter(filePathAccess);
			
			for (Request r : rList) {
				String s = r.readToLine();
				
				if (r instanceof AccountRequest) {
					accountfw.write(s + "\n");
				} else  if (r instanceof AccessRequest){
					accessfw.write(s + "\n");
				}
			}
			
			accountfw.close();
			accessfw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void add(Request e) {
		rList.add(e);
	}
	
	public void remove(Request e) {
		rList.remove(e);
	}
	
	public void nullify(Request e) {
		int i = rList.indexOf(e);
		
		rList.set(i, null);
	}
	
	public ArrayList<Request> getAll(){
		return rList;
	}
	
	public static void main(String[] args) {
		RequestReader rr = new RequestReader();
		System.out.println(rr.getAll());
	}
}
