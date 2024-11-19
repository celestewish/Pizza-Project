public class Wings extends Side{
	public Wings(SideType type, float price, int count) {
		super(type, price, count);
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
		if (getCount() > 0)
			wing.append(" (").append(getCount()).append(" count)");
		
		// Append the price
		wing.append(" - $").append(String.format("%.2f", getPrice()));
		
		return wing.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Wings wings = (Wings) o;
		return  type == wings.type &&
				getCount() == wings.getCount(); // Explicitly compare the boolean count
	}
}
