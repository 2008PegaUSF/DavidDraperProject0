package Driver;

import static java.lang.System.out; // This class will have a lot of printing, so we're going to simplify the whole thing

import java.util.ArrayList;
import java.util.Scanner;

import DataObjects.AccessRequest;
import DataObjects.Account;
import DataObjects.AccountRequest;
import DataObjects.Request;
import Managers.LoginManager;
import Readers.AccountReader;
import Readers.AdminReader;
import Readers.CustomerReader;
import Readers.EmployeeReader;
import Readers.RequestReader;
import Users.Admin;
import Users.Customer;
import Users.Employee;
import Users.User;

public class BankApp {

	User user;
	Scanner input;
	
	public BankApp() {
		input = new Scanner(System.in);
	}
	
	/*
	 * MENU DISPLAYS
	 */
	
	// Print Main Menu
	public void displayMainMenu() {
		out.println();
		out.println("Main Menu");
		out.println("=========");
		out.println("1. Customer Login");
		out.println("2. Customer Register");
		out.println("3. Employee Login");
		out.println("4. Admin Login");
		out.println("5. Exit");
	}
	
	// Print Customer Menu
	public void displayCustomerMenu() {
		out.println();
		out.println("Customer Menu");
		out.println("=============");
		out.println("1. View Account List");
		out.println("2. View Account");
		out.println("3. Request New Account");
		out.println("4. Request Joint Access");
		out.println("5. Log out");
	}
	
	
	// Print Employee Menu
	public void displayEmployeeMenu() {
		out.println();
		out.println("Employee Menu");
		out.println("=============");
		out.println("1. View Account List");
		out.println("2. View Account");
		out.println("3. Manage Requests");
		out.println("4. Log out");
	}
	
	// Print Admin Menu
	public void displayAdminMenu() {
		out.println();
		out.println("Admin Menu");
		out.println("==========");
		out.println("1. View Account List");
		out.println("2. View Account");
		out.println("3. Log out");
	}
	
	// Print Account Menu (Customer)
	public void displayAccountMenu(Customer c) {
		out.println();
		out.println("Account Menu");
		out.println("============");
		out.println("1. Deposit");
		out.println("2. Withdraw");
		out.println("3. Transfer");
		out.println("4. Back");
	}
	
	// Print Account Menu (Admin)
	private void displayAccountMenu() {
		out.println();
		out.println("Account Menu");
		out.println("============");
		out.println("1. Deposit");
		out.println("2. Withdraw");
		out.println("3. Transfer");
		out.println("4. Close");
		out.println("5. Back");
	}
	/*
	 * MENU FUNCTIONS
	 */
	
	// Main Menu - Access point for the program
	public void mainMenu() {
		displayMainMenu();
		
		// Make sure input is a valid option
		int num = 0;
		while (true) {
			num = input.nextInt();
			if (num >= 1 && num <= 5) {
				break;
			}
			out.println("Invalid input.");
		}
		
		switch (num) {
		case 1:
			customerLogin();
			if (user instanceof Customer) {
				customerMenu();
			}
			break;
		case 2:
			customerRegister();
			if (user instanceof Customer) {
				customerMenu();
			}
			break;
		case 3:
			employeeLogin();
			if (user instanceof Employee) {
				employeeMenu();
			}
			break;
		case 4:
			adminLogin();
			if (user instanceof Admin) {
				adminMenu();
			}
			break;
		case 5:
			input.close();
			System.exit(0);
			break;
		default:
			out.println("ERROR: INVALID OPTION");
			break;
		}
		
		return;
	}
	
	// Customer Login
	public void customerLogin() {
		CustomerReader cr = CustomerReader.getInstance();
		LoginManager lm = new LoginManager("Customer");
		int id = -1;
		
		out.println();
		out.println("Customer Login");
		out.println("==============");
		
		while (true) {
			out.print("Username: ");
			String username = input.next();
			out.print("Password: ");
			String password = input.next();
		
			id = lm.login(username, password);
			
			if (id != -1) {
				break;
			}
			
			out.println("Try again? (Y/N)");
			String s = input.next();
			if (!s.equals("Y")) {
				return;
			}
		}
		
		user = cr.getByID(id);
	}
	
	// Customer Register
	public void customerRegister() {
		CustomerReader cr = CustomerReader.getInstance();
		LoginManager lm = new LoginManager("Customer");
		int id = -1;
		
		out.println();
		out.println("Customer Register");
		out.println("=================");
		
		while (true) {
			out.print("Username: ");
			String username = input.next();
			out.print("Password: ");
			String password = input.next();
			out.print("First Name: ");
			String firstName = input.next();
			out.print("Last Name: ");
			String lastName = input.next();
			
			id = lm.register(username, password, firstName, lastName);
			
			if (id != -1) {
				break;
			}
			
			out.println("Try again? (Y/N)");
			String s = input.next();
			if (!s.equals("Y")) {
				return;
			}
		}
		
		user = cr.getByID(id);
		
	}
	
	// Employee Login
	public void employeeLogin() {
		EmployeeReader er = new EmployeeReader();
		LoginManager lm = new LoginManager("Employee");
		int id = -1;
		
		out.println();
		out.println("Employee Login");
		out.println("==============");
		
		while (true) {
			out.print("Username: ");
			String username = input.next();
			out.print("Password: ");
			String password = input.next();
		
			id = lm.login(username, password);
			
			if (id != -1) {
				break;
			}
			
			out.println("Try again? (Y/N)");
			String s = input.next();
			if (!s.equals("Y")) {
				return;
			}
		}
		
		user = er.getByID(id);
	}
	
	// Admin Login
	public void adminLogin() {
		AdminReader ar = new AdminReader();
		LoginManager lm = new LoginManager("Admin");
		int id = -1;
		
		out.println();
		out.println("Admin Login");
		out.println("===========");
		
		while (true) {
			out.print("Username: ");
			String username = input.next();
			out.print("Password: ");
			String password = input.next();
		
			id = lm.login(username, password);
			
			if (id != -1) {
				break;
			}
			
			out.println("Try again? (Y/N)");
			String s = input.next();
			if (!s.equals("Y")) {
				return;
			}
		}
		
		user = ar.getByID(id);
	}
	
	// Customer Menu
	public void customerMenu() {
		while(true) {
			displayCustomerMenu();
			
			// Make sure input is a valid option
			int num = 0;
			while (true) {
				num = input.nextInt();
				if (num >= 1 && num <= 5) {
					break;
				}
				out.println("Invalid input.");
			}
			
			switch (num) {
			case 1:
				displayAccountList((Customer) user);
				break;
			case 2:
				Account a = selectAccount((Customer) user);
				viewAccount((Customer) user, a);
				break;
			case 3:
				((Customer) user).request();
				break;
			case 4:
				displayAccountList();
				out.print("Select account to request join access: ");
				Account b = selectAccount();
				((Customer) user).requestAccess(b.getID());
				break;
			case 5:
				RequestReader.getInstance().post();
				return;
			default:
				out.println("ERROR: INVALID OPTION");
				break;
			}
		}
	}
	
	// Employee Menu
	public void employeeMenu() {
		while (true) {
			displayEmployeeMenu();
			
			// Make sure input is a valid option
			int num = 0;
			while (true) {
				num = input.nextInt();
				if (num >= 1 && num <= 4) {
					break;
				}
				out.println("Invalid input.");
			}
			
			switch (num) {
			case 1:
				displayAccountList();
				break;
			case 2:
				Account a = selectAccount();
				viewAccount((Employee) user, a);
				break;
			case 3:
				manageRequests();
				break;
			case 4:
				CustomerReader.getInstance().post();
				RequestReader.getInstance().post();
				return;
			default:
				out.println("ERROR: INVALID OPTION");
				break;
			}
		}
	}
	
	// Admin Menu
	public void adminMenu() {
		while (true) {
			displayAdminMenu();
			
			// Make sure input is a valid option
			int num = 0;
			while (true) {
				num = input.nextInt();
				if (num >= 1 && num <= 3) {
					break;
				}
				out.println("Invalid input.");
			}
			
			switch (num) {
			case 1:
				displayAccountList();
				break;
			case 2:
				Account a = selectAccount();
				viewAccount(a);
				break;
			case 3:
				RequestReader.getInstance().post();
				CustomerReader.getInstance().post();
				return;
			default:
				out.println("ERROR: INVALID OPTION");
				break;
			}
		}
	}
	
	/*
	 * CUSTOMER FUNCTIONS
	 */
	
	public void displayAccountList(Customer c) {
		c.displayAccountList();
	}
	
	public Account selectAccount(Customer c) {
		out.println();
		out.print("Enter account ID: ");
		while (true) {
			int num = input.nextInt();
			Account a = c.getAccount(num);
			
			if (a != null) {
				return a;
			}
		}
	}
	
	public void viewAccount(Customer c, Account a) {
		out.println();
		out.println("Account #" + a.getID());
		
		while (true) {
			a.displayBalance();
			displayAccountMenu(c);
			
			// Make sure input is a valid option
			int num = 0;
			while (true) {
				num = input.nextInt();
				if (num >= 1 && num <= 4) {
					break;
				}
				out.println("Invalid input.");
			}
			
			int i;
			switch (num) {
			case 1:
				out.print("Enter amount to deposit: ");
				i = input.nextInt();
				a.deposit(i);
				break;
			case 2:
				out.print("Enter amount to withdraw: ");
				i = input.nextInt();
				a.withdraw(i);
				break;
			case 3:
				out.print("Enter account to transfer to: ");
				Account b = selectAccount(c);
				out.print("Enter amount to transfer: ");
				i = input.nextInt();
				a.withdraw(i);
				b.deposit(i);
				break;
			case 4:
				AccountReader.getInstance().post();//Post all account changes
				return;
			default:
				out.println("ERROR: INVALID OPTION");
				break;	
			}
		}
	}
	
	/*
	 * EMPLOYEE/ADMIN FUNCTIONS
	 */
	
	public void displayAccountList() {
		AccountReader ar = AccountReader.getInstance();
		ar.displayAll();
	}
	
	public Account selectAccount() {
		AccountReader ar = AccountReader.getInstance();
		out.println();
		out.print("Enter account ID: ");
		while (true) {
			int num = input.nextInt();
			Account a = ar.getByID(num);
			
			if (a != null) {
				return a;
			}
		}
	}
	
	/*
	 * EMPLOYEE FUNCTION
	 */
	
	public void viewAccount(Employee e, Account a) {
		out.println();
		out.println("Account #" + a.getID());
		
		a.displayBalance();
	}
	
	public void manageRequests() {
		RequestReader rr = RequestReader.getInstance();
		ArrayList<Request> rList = rr.getAll();
		
		for (Request r : rList) {
			displayRequest(r);
			
			int num;
			while (true) {
				num = input.nextInt();
				if (num >= 1 && num <= 3) {
					break;
				}
				out.println("Invalid input.");
			}
			
			switch (num) {
			case 1:
				((Employee) user).approve(r);
				break;
			case 2:
				((Employee) user).deny(r);
				break;
			case 3:
				return;
			default:
				out.println("ERROR: INVALID OPTION");
				break;
			}
		}
		
		out.println();
		out.println("End of requests.");
		
	}
	
	public void displayRequest(Request r) {
		if (r instanceof AccountRequest) {
			out.println();
			out.println("Customer #" + r.getID() + " requested a new account.");
			out.println("1. Approve");
			out.println("2. Deny");
			out.println("3. Exit");
			return;
		}
		out.println();
		out.println("Customer #" + r.getID() + " requested access to account #" + ((AccessRequest) r).getAccountID() +".");
		out.println("1. Approve");
		out.println("2. Deny");
		out.println("3. Exit");
	}
	
	/*
	 * ADMIN FUNCTIONS
	 */
	public void viewAccount(Account a) {
		out.println();
		out.println("Account #" + a.getID());
		
		while (true) {
			a.displayBalance();
			displayAccountMenu();
			
			// Make sure input is a valid option
			int num = 0;
			while (true) {
				num = input.nextInt();
				if (num >= 1 && num <= 4) {
					break;
				}
				out.println("Invalid input.");
			}
			
			int i;
			switch (num) {
			case 1:
				out.print("Enter amount to deposit: ");
				i = input.nextInt();
				a.deposit(i);
				break;
			case 2:
				out.print("Enter amount to withdraw: ");
				i = input.nextInt();
				a.withdraw(i);
				break;
			case 3:
				out.print("Enter account to transfer to: ");
				Account b = selectAccount();
				out.print("Enter amount to transfer: ");
				i = input.nextInt();
				a.withdraw(i);
				b.deposit(i);
				break;
			case 4:
				AccountReader ar = AccountReader.getInstance();
				CustomerReader cr = CustomerReader.getInstance();
				ar.remove(a);
				cr.removeAccount(a);
			case 5:
				AccountReader.getInstance().post();//Post all account changes
				return;
			default:
				out.println("ERROR: INVALID OPTION");
				break;	
			}
		}
	}

	// The primary function method of the banking app
	public void run() {
		while(true) {
			mainMenu();
		}
	}
	
	/*
	 *  The launch point for the app
	 *  Declares a BankApp object and calls run
	 */
	public static void main(String[] args) {
		BankApp ba = new BankApp();
		ba.run();
	}
}
