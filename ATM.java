import java.util.Scanner;

public class ATM {
	private boolean userAuthenticated;
	private int currentAccountNumber;
	private Screen screen;  //ATM's screen
	private Keypad keypad;
	private CashDispenser cashDispenser;
	private DepositSlot depositSlot;
	private BankDatabase bankDatabase;  //account information database
	
	//constants corresponding to main menu option
	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;
	
	//No argument ATM constructor to initiate instance variable
	public ATM() {
		userAuthenticated = false;
		currentAccountNumber = 0;
		screen = new Screen(); //create a screen
		keypad = new Keypad();
		cashDispenser = new CashDispenser();
		depositSlot = new DepositSlot();
		bankDatabase = new BankDatabase();
	}//end no argument constructor
	
	//start ATM
	public void start() {
		
		//welcome and authenticate user and perform transaction
		
		while(true)
		{
			//if user is not yet authenticated
			while(!userAuthenticated) {
				screen.displayMessageLine("\nWelcome!");
				authenticateUser(); // authenticate user into the system
			} //end while user is not authenticated
			
			performTransaction();  //user now authenticated
			userAuthenticated = false; //reset ATM for next user
			currentAccountNumber = 0;
			screen.displayMessageLine("\nThank you! GoodBye");
		}  //end of while true
		
	} //end of run
	
	//Attempt to authenticate user against database
	
	public void authenticateUser() {
		
		screen.displayMessage("\nPlease enter your account number : ");
		int accountNumber = keypad.getInput();   //input account number
		screen.displayMessage("enter your PIN : ");
		int pin = keypad.getInput();   //input PIN
		
		//set authentication to boolean value return by database
		
		userAuthenticated = bankDatabase.authenticateUser(accountNumber , pin);
		
		//checking if athentication was a success
		
		if(userAuthenticated) {
			
			currentAccountNumber = accountNumber;
			
		} //end if
		else 
			screen.displayMessageLine("Incorrect account number or pin, tyr again");
		
	}  //end of method authenticate
	
	//display menu and perform transactions
	public void performTransaction() {
		// local variable to store transaction currently being processed
		Transaction currentTransaction = null;
		
		boolean userExited = false; //user has not chosen to exit
		
		// loop while user has not chosen option to exit the system
		while(!userExited) {
			// show main menu for user to select an option to perform transaction
			int mainMenuSelection = displayMainMenu();
			
			//decide how to proceed base on user selection
			switch( mainMenuSelection )
			{
			   // user choose to perform one of the three transaction type
			case BALANCE_INQUIRY:
			case WITHDRAWAL:
			case DEPOSIT:
				//initialize as new object of chosen type
				currentTransaction = createTransation( mainMenuSelection);
				currentTransaction.execute();  //execute transaction
				break;
			case EXIT:   //User chose to terminate session
				screen.displayMessageLine("\nExiting the system...");
				userExited = true;  //this ATM session should end
				break;
			default: //user did not enter an integer from 1 to 4
				screen.displayMessageLine("\nYou did not enter a valid selection"
						+ "Try again.");
				break;		
			} //end of switch
		} //end while
	} //end of perform transaction
	
	//display mainmenu and return input selection
	
	private int displayMainMenu() {
		screen.displayMessageLine("\nMain menu");
		screen.displayMessageLine("1 - view my balance");
		screen.displayMessageLine("2 - Withdraw cash");
		screen.displayMessageLine("3 - Deposit funds");
		screen.displayMessageLine("Enter a choice");
		
		return keypad.getInput(); //return user's selection
		
	} // end of display main menu method
	
	//return object of specific transaction subclass
	private Transaction createTransation(int type) {
		Transaction temp = null; //temporary transaction variable
		
		//determine which type of transaction to create
		switch(type)
		{
		case BALANCE_INQUIRY:  //CREATE NEW BALANCE INQUIRY TRANSACTION
			temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
			break;
		case WITHDRAWAL :   //CREATE NEW WITHDRAWAL TRANSACTION
			temp = new Withdrawal( currentAccountNumber, screen,
					bankDatabase, keypad, cashDispenser );
					 break;
		case DEPOSIT:
			temp = new Deposit( currentAccountNumber, screen,
					bankDatabase, keypad, depositSlot);
					break;			
		}//end of switch
		return temp;  //return the newly created object
		
	}// end of createTransaction method

}// end of ATM class
