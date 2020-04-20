
public class BalanceInquiry extends Transaction {
	
	public BalanceInquiry(int accountNumber, Screen atmScreen, BankDatabase atmDbase) {
		
		super(accountNumber, atmScreen, atmDbase);
	}
	
	//now perform the transaction
	@Override
	public void execute() {
		
		//get reference to bank database and screen
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		//get available balance for the account involved
		
		double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
		//get total balance
		
		double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
		
		//display balance information on the screen
		screen.displayMessageLine("\nBalance Information :");
		screen.displayMessage("-Available Balance :");
		screen.displayDollarAmount(availableBalance);
		screen.displayMessage("\n-Total Balance");
		screen.displayDollarAmount(totalBalance);
	}
	

}
