//class withdrawal represents an ATM withdrawal transaction
public class Withdrawal extends Transaction {
	
	//attributes
	private int amount; // amount to withdraw
	
	//reference to associated object
	private Keypad keypad; //ATM'S Keypad
	private CashDispenser cashDispenser; //slot where money will come through
	private BankDatabase bankDatabase;   //database that interacts with account
	private final static int CANCELED = 6; //menu option to cancel
	
	public Withdrawal( int userAccountNumber, Screen atmScreen,
			 BankDatabase atmBankDatabase, Keypad atmKeypad,
			 CashDispenser atmCashDispenser ) {
		super( userAccountNumber, atmScreen, atmBankDatabase );                                     //no argument withdrawal  constructor
		cashDispenser = atmCashDispenser;
		keypad = atmKeypad;
	}//end no argument withdrawer constructor
	
	//perform transactions
	@Override   //we override bcus the parent class has the same method
	
	public void execute() {
		boolean cashDispensed = false;
		double availableBalance;
		
		// get references to bank database and screen
		 BankDatabase bankDatabase = getBankDatabase();
		 Screen screen = getScreen();
		 
		// loop until cash is dispensed or the user cancels
		 do
		 {
		 // obtain a chosen withdrawal amount from the user
		 amount = displayMenuOfAmounts();
		
		 // check whether user chose a withdrawal amount or canceled
		 if ( amount != CANCELED )
		 {
		 // get available balance of account involved
		 availableBalance =
		 bankDatabase.getAvailableBalance( getAccountNumber() );
		
		 // check whether the user has enough money in the account
		  if( amount <= availableBalance )
		 {
				 // check whether the cash dispenser has enough money
		 if ( cashDispenser.isSufficientCachAvailable( amount ) )
		 {
		 // update the account involved to reflect the withdrawal
		 bankDatabase.debit( getAccountNumber(), amount );
		
		 cashDispenser.dispenseCash( amount ); // dispense cash
		 cashDispensed = true; // cash was dispensed
		
		 // instruct user to take cash
		 screen.displayMessageLine( "\nYour cash has been" +
		 " dispensed. Please take your cash now." );
		 } // end if
		 else // cash dispenser does not have enough cash
		 screen.displayMessageLine(
		 "\nInsufficient cash available in the ATM." +
		 "\n\nPlease choose a smaller amount." );
		 } // end if
		 else // not enough money available in user's account
		 {
		 screen.displayMessageLine(
		 "\nInsufficient funds in your account." +
		 "\n\nPlease choose a smaller amount." );
		 } // end else
		 } // end if
		 else // user chose cancel menu option
		 {
		 screen.displayMessageLine( "\nCanceling transaction..." );
		 return; // return to main menu because user canceled
		 } // end else
		 
		 } while ( !cashDispensed );
		
		 } // end method execute
		
		 // display a menu of withdrawal amounts and the option to cancel;
		 // return the chosen amount or 0 if the user chooses to cancel
		 private int displayMenuOfAmounts()
		 {
		 int userChoice = 0; // local variable to store return value
		
		 Screen screen = getScreen(); // get screen reference
		
		 // array of amounts to correspond to menu numbers
		 int[] amounts = { 0, 20, 40, 60, 100, 200 };
		
		 // loop while no valid choice has been made
		 while ( userChoice == 0 )
		 {
		 // display the withdrawal menu
		 screen.displayMessageLine( "\nWithdrawal Menu:" );
		 screen.displayMessageLine( "1 - $20" );
		 screen.displayMessageLine( "2 - $40" );
		 screen.displayMessageLine( "3 - $60" );
		
		  screen.displayMessageLine( "4 - $100" );
		  screen.displayMessageLine( "5 - $200" );
		  screen.displayMessageLine( "6 - Cancel transaction" );
		  screen.displayMessage( "\nChoose a withdrawal amount: " );
		 
		  int input = keypad.getInput(); // get user input through keypad
		 
		  // determine how to proceed based on the input value
		  switch ( input )
		  {
		  case 1: // if the user chose a withdrawal amount
		  case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
		  case 3: // corresponding amount from amounts array
		  case 4:
		  case 5:
		  userChoice = amounts[ input ]; // save user's choice
		  break;
		  case CANCELED: // the user chose to cancel
		  userChoice = CANCELED; // save user's choice
		  break;
		  default: // the user did not enter a value from 1-6
		  screen.displayMessageLine( "\nInvalid selection. Try again." );
		  } // end switch
		  } // end while
		 
		  return userChoice; // return withdrawal amount or CANCELED
		   // end method displayMenuOfAmounts
		  // end class Withdrawal
		
	}//end of execute

}//end class withdrawal 
