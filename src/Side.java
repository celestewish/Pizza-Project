public class Side extends MenuItem {
	protected final SideType type;
	
	public Side(SideType type, float price) {
		super(price);
		this.type = type;
	}
	
	@Override
	public float calcPrice() {
		// Calculate the price based on the count
		float total = getPrice();
		
		// You can add logic to modify the price based on the type of side (if needed)
		switch (type) {
			case GARLIC_BREAD -> total += 0;  // No additional cost for garlic bread (example)
			case GARLIC_KNOTS -> total += 1;  // Additional cost for garlic knots (example)
			case CHICKEN_WINGS -> total += 2; // Additional cost for chicken wings (example)
			case LEMON_PEPPER_WINGS -> total += 2; // Additional cost for lemon pepper wings (example)
			case CAESAR_SALAD -> total += 1; // Additional cost for caesar salad (example)
		}

		return total;
	}
	
	@Override
	public String toString() {
		StringBuilder side = new StringBuilder();
		
		// Append the type of side
		switch (type) {
			case GARLIC_BREAD -> side.append("Garlic Bread");
			case GARLIC_KNOTS -> side.append("Garlic Knots");
			case CHICKEN_WINGS -> side.append("Chicken Wings");
			case LEMON_PEPPER_WINGS -> side.append("Lemon Pepper Wings");
			case CAESAR_SALAD -> side.append("Caesar Salad");
		}
		
		// Append the price
		side.append(" - $").append(String.format("%.2f", calcPrice()));  // Use a single count for display purposes
		return side.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Side side = (Side) o;
		return Float.compare(side.getPrice(), getPrice()) == 0 && type == side.type;
	}
	
	public SideType getType() {
		return type;
	}
}