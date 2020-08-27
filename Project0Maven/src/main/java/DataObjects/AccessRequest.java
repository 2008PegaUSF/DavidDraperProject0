package DataObjects;

public class AccessRequest extends Request {
	
	// Members
	private int accountId;
	
	public AccessRequest() {
		id = 0;
		accountId = 0;
	}
	
	public AccessRequest(int id, int accountId) {
		this.id = id;
		this.accountId = accountId;
	}
	
	public int getAccountID() {
		return accountId;
	}
	
	public String readToLine() {
		return (id + " " + accountId);
	}
	
	public String toString() {
		return readToLine();
	}
	
	public boolean equals(AccessRequest a) {
		return (id == a.getID() && accountId == a.getAccountID());
	}
}
