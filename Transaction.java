import java.util.Scanner;

public abstract class Transaction {
	private int accountNumber;  //account to withdraw cash from
	private Screen screen;  //transaction interacts with the screen
	private BankDatabase bankDatabase;
	
	//constructor
	public Transaction(int accountNumber, Screen atmScreen, BankDatabase atmDbase) {
		
		this.accountNumber = accountNumber;
		screen = atmScreen;
		bankDatabase = atmDbase;
	}//end of constructor
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public Screen getScreen() {
		
		return screen;
	}
	
	public BankDatabase getBankDatabase() {
		
		return bankDatabase;
	}
	
	//operations to perform
	public abstract void execute();

}
