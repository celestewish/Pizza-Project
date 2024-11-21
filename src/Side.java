public class Side extends MenuItem {
	protected final SideType type;
	private int count = -1; // Default value indicating count is not required
	
	public Side(SideType type, float price, int count) {
		super(price);
		this.type = type;
		this.count = count;
	}
	
	public Side(SideType type, float price) {
		super(price);
		this.type = type;
	}
	
	@Override
	public float calcPrice() {
		// Calculate the price based on the count if count is relevant
		if (count > 0) {
			return getPrice() * count/5; // Multiply price by count divided by 5 (1 for 5 ct, 2 for 10ct)
		}
		return getPrice(); // Return base price if no count
	}
	
	@Override
	public String toString() {
		StringBuilder side = new StringBuilder();
		
		// Append the type of side
		switch (type) {
			case GARLIC_BREAD -> side.append("Garlic Bread");
			case GARLIC_KNOTS -> side.append("Garlic Knots");
			case CAESAR_SALAD -> side.append("Caesar Salad");
			case WINGS -> {
				WingType wingType = ((Wings) this).getWingType();  // Assuming this class is a Wing type and has a getWingType method
				switch (wingType) {
					case BREADED_CHICKEN_WINGS -> side.append("Breaded Chicken Wings");
					case LEMON_PEPPER_WINGS -> side.append("Lemon Pepper Wings");
					case HOT_WINGS -> side.append("Hot Wings");
					case HONEY_FIRE_WINGS -> side.append("Honey Fire Wings");
					case APRICOT_GLAZE_WINGS -> side.append("Apricot Glaze Wings");
					case PARMESAN_WINGS -> side.append("Parmesan Wings");
				}
			}
		}
		
		// Append the count if applicable
		if (count > 0) {
			side.append(" (").append(count).append(" count)");
		}
		
		// Append the price
		side.append(" - $").append(String.format("%.2f", calcPrice()));
		return side.toString();
	}

	// Checks if the current side is equal to another side by comparing their price, type, and count
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;  // Check if both objects are the same instance
		if (o == null || getClass() != o.getClass()) return false;  // Ensure the objects are of the same class
		Side side = (Side) o;  // Cast the object to Side
		// Compare price, type, and count of the side
		return Float.compare(side.getPrice(), getPrice()) == 0
				&& type == side.type
				&& count == side.count;  // Compare count as well
	}

	// Getter for the type of the side
	public SideType getType() {
		return type;
	}

	// Getter for the count of the side
	public int getCount() {
		return count;
	}

	// Setter for the count of the side
	public void setCount(int count) {
		this.count = count;
	}
}