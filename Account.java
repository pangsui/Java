//Represents a bank account
public class Account {
	private int accountNumber;
	private int pin;
	private double availableAmount; //fund available for withdrawal
	private double totalAmount; //fund available plus pending deposit
	
	public Account(int accountNumber, int pin,double availabeAmount, double totalAmount) {
		
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.availableAmount = availableAmount;
		this.totalAmount = totalAmount;
	} //end of account constructor;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public double getAvailableAmount() {
		
		return availableAmount;
	}
	
	public double getTotalAmount(){
		
		return totalAmount;
	}
	
	public boolean validatePIN(int userPIN) {
		if(userPIN == pin) {
			return true;
		}
		else 
			return false;
	}
	
	//credit an account
	public void credit(double amount) {
		availableAmount = availableAmount + amount;
	}
	//debits an account
	public void debit(double amount) {
		availableAmount = availableAmount - amount;
		totalAmount = totalAmount - amount;
	}
	
}
