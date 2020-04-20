import java.util.Scanner;//program uses scanner to obtain user input
//Represents the keypad of ATM (digit 0-9)
public class Keypad {
	private Scanner input; //read data from the command line
	
	public Keypad() {  //no argument constructor initializes the scanner
		input = new Scanner(System.in);
	}
	
	//return all integer values enter by users 
	public int getInput() {
		return input.nextInt(); //we assume that user enters an integer
	}

}
