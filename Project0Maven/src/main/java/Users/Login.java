package Users;

import java.util.Scanner;

public class Login {
	private String username;
	private String password;
	private int id;
	
	public Login() {
		username = "";
		password = "";
		id = 0;
	}
	
	public Login(String username, String password, int id) {
		this.username = username;
		this.password = password;
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String readToLine() {
		return (username + " " + password + " " + id);
	}
	
	public static Login readFromString(String s) {
		Scanner stringReader = new Scanner(s);
		
		String username = stringReader.next();
		String password = stringReader.next();
		int id = stringReader.nextInt();
		
		stringReader.close();
		
		return new Login(username,password,id);
	}
}
