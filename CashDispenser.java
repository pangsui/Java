//Represents the cash dispenser of the ATM(the where money comes out)
public class CashDispenser {
	
	//the default initial number bill in the cash dispenser
	private final static int INITIAL_COUNT = 500;
	private int count; //number of $200bills remaining
	
	//no argument constructor initializes cash to count
	public CashDispenser() {
		count = INITIAL_COUNT; //SET COUNT VALUE TO DEFAULT
	}
	
	public void dispenseCash(int amount) {
		
		int dispenseAmount = amount /20;
		count = count - dispenseAmount;
	}
	
	public boolean isSufficientCachAvailable(int amount) {
		
		int dispenseAmount = amount/20;
		if(count >= dispenseAmount) {
			return true;
		}
		else
			return false;
	}

}
