public class Wings extends Side{
	private boolean count; // false for 5, true for 10
	
	public Wings(SideType type, float price, boolean count) {
		super(type, price);
		this.count = count;
	}
	
	@Override
	public String toString() {
		StringBuilder wing = new StringBuilder();
		
		// Append the type of side
		switch (type) {
			case LEMON_PEPPER_WINGS -> wing.append("Lemon Pepper Wings").append(" ");
			case CHICKEN_WINGS -> wing.append("Chicken Wings").append(" ");
		}
		
		// Append the count
		if (count)
			wing.append("5 Count");
		else
			wing.append("10 count");
		
		// Append the price
		wing.append(" - $").append(String.format("%.2f", getPrice()));
		
		return wing.toString();
	}
	
	public boolean isCount() {
		return count;
	}
	
	public void setCount(boolean count) {
		this.count = count;
	}
}
