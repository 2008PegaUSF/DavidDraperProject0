package testers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import DataObjects.*;

public class AppTester {
	
	@Test //Does AccessRequest convert to a String properly?
	public void AccessRequestToLine() {
		String s = "1000 1111";
		AccessRequest ar = new AccessRequest(1000,1111);
		
		Assertions.assertTrue(ar.readToLine().equals(s));
	}
	
	@Test //Does AccessRequest convert from a String properly?
	public void AccessRequestFromLine() {
		String s = "1000 1111";
		AccessRequest ar = new AccessRequest(1000,1111);
		
		Assertions.assertTrue(ar.equals((AccessRequest) AccessRequest.readfromline(s)));
	}
	
	@Test //Does Account convert to a String properly?
	public void AccountToLine() {
		String s = "1111 1000";
		Account a = new Account(1111,1000,"account1111");
		
		Assertions.assertTrue(a.readToLine().equals(s));
	}
	
	@Test //Does Account convert from a String properly?
	public void AccountFromLine() {
		String s = "1111 1000";
		Account a = new Account(1111,1000,"account1111");
		
		Assertions.assertTrue(a.equals(Account.readFromLine(s)));
	}
	
	@Test //Does AccountRequest convert to a String properly?
	public void AccountRequestToLine() {
		String s = "1000";
		AccountRequest ar = new AccountRequest(1000);
		
		Assertions.assertTrue(ar.readToLine().equals(s));
	}
	
	@Test //Does AccountRequest convert from a String properly?
	public void AccountRequestFromLine() {
		String s = "1000";
		AccountRequest ar = new AccountRequest(1000);
		
		Assertions.assertTrue(ar.equals((AccountRequest) AccountRequest.readfromline(s)));
	}
	
	@Test //Does Deposit convert to a String properly?
	public void DepositToLine() {
		String s = "Deposit 1000";
		Deposit d = new Deposit(1000);
		
		Assertions.assertTrue(d.readToLine().equals(s));
	}
	
	@Test //Does Deposit convert from a String properly?
	public void DepositFromLine() {
		String s = "Deposit 1000";
		Deposit d = new Deposit(1000);
		
		Assertions.assertTrue(d.equals((Deposit) Deposit.readFromLine(s)));
	}
	
	@Test //Does Withdrawal convert to a String properly?
	public void WithdrawalToLine() {
		String s = "Withdrawal 1000";
		Withdrawal w = new Withdrawal(1000);
		
		Assertions.assertTrue(w.readToLine().equals(s));
	}
	
	@Test //Does Deposit convert from a String properly?
	public void WithdrawalFromLine() {
		String s = "Withdrawal 1000";
		Withdrawal w = new Withdrawal(1000);
		
		Assertions.assertTrue(w.equals((Withdrawal) Withdrawal.readFromLine(s)));
	}
}
