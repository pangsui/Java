//represents the screen of an ATM
public class Screen {
	// Display a message without a carriage return 
	public void displayMessage(String message) {
		System.out.print(message);
	} //end method of displayMessage
	
	//display a message with a carriage return
	public void displayMessageLine(String message) {
		System.out.println(message);
	}//end of displayMessageLine
	
	//display a dollar amount
	public void displayDollarAmount(double amount) {
		System.out.printf("$%,.2f", amount);
	} //end method displayDollarAmount 

}
