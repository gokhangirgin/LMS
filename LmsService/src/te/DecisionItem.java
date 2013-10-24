package te;
 
public class DecisionItem { 
	public DecisionItem(int id)
	{
		this.id = id; 
	}
	public int id;
	private boolean bedding;
	private boolean toBeDiscarded;
	private boolean dryInDryer;  
	public boolean isBedding() {
		return bedding;
	}
	public void setBedding(boolean bedding) {
		this.bedding = bedding;
	}
	public boolean isToBeDiscarded() {
		return toBeDiscarded;
	}
	public void setToBeDiscarded(boolean toBeDiscarded) {
		this.toBeDiscarded = toBeDiscarded;
	}

	public boolean isDryInDryer() {
		return dryInDryer;
	}

	public void setDryInDryer(boolean dryInDryer) {
		this.dryInDryer = dryInDryer;
	}
	@SuppressWarnings("unused")
	private static int waitingTime = 5;
}
