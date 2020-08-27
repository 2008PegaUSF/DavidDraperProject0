package DataObjects;

public class AccountRequest extends Request {
	
	public AccountRequest() {
		id = 0;
	}
	
	public AccountRequest(int id) {
		this.id = id;
	}
	
	public String readToLine() {
		return (id + "");
	}
	
	public String toString() {
		return readToLine();
	}
	
	public boolean equals(AccountRequest a) {
		return (id == a.getID());
	}
}
