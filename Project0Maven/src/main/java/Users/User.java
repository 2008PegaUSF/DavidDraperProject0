package Users;

import java.util.ArrayList;
import DataObjects.Account;
import Readers.AccountReader;

public abstract class User {
	
	// Members
	protected String firstName;
	protected String lastName;
	protected int id;
	protected ArrayList<Account> aList;
	protected AccountReader ar;
	
	public abstract String readToLine();
	
}
