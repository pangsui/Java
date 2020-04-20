
public class BankDatabase {
	
	Account []accounts; //creates an array of accounts
	
	public BankDatabase() {
		
		accounts = new Account[2]; //create only 2accounts for testing purpose
		accounts[0] =  new Account( 12345, 54321, 1000.0, 1200.0 );
		accounts[ 1 ] = new Account( 98765, 56789, 200.0, 200.0 );
	}
	
	//get account object containing specific account number
	
	private Account getAccount(int accountNumber) {
		
		//loop through accounts searching for matching account number
		
		for(Account currentAccount : accounts) {
			
			if(currentAccount.getAccountNumber() == accountNumber) {
				
				return currentAccount;
			}
		}//end of for
		return null; //if no matching account found return nothing
	} // end of method account
	
	//determine if user specified account number and pin matches that of database
	
	public boolean authenticateUser(int userAccountNumber, int userPin) {
		
		//attempt to retrieve account with account number
		Account userAccount = getAccount(userAccountNumber);
		
		//if the account exist return the accountPIN method to check the PIN
		if(userAccount!=null) {
			
			return userAccount.validatePIN(userPin);
		}
		else
			return false; //account number not found, so return false
	} //end method authenticate
	
	// return available balance of account with specified account number
	public double getAvailableBalance(int userAccountNumber) {
		
		return getAccount(userAccountNumber).getAvailableAmount();
		
	}
	
	//return total balance of account with specified account number
	public double getTotalBalance(int userAccountNumber) {
		
		return getAccount(userAccountNumber).getTotalAmount();
	}
	
	//credit an amount to an account with specified account number
	public void credit(int accountNumber, double amount) {
		
		getAccount(accountNumber).credit(amount);
	}
	
	//debit an amount from an account with specific account number
	public void debit(int accountNumber, double amount) {
		
		getAccount(accountNumber).debit(amount);
	} //end of debit method

}
