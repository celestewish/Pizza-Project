public class Drink extends MenuItem {
	private DrinkSize size;
	private final DrinkType type;
	
	public Drink(DrinkSize size, DrinkType type, float price) {
		super(price);
		this.size = size;
		this.type = type;
	}
	
	@Override
	public float calcPrice() {
		float total = getPrice();
		
		// Add additional price based on the size
		switch (size) {
			case SMALL -> total += 0;
			case MEDIUM -> total += 1;
			case LARGE -> total += 1.5F;
		}

		return total;
	}
	
	@Override
	public String toString() {
		StringBuilder drink = new StringBuilder();
		
		switch (type) {
			case COKE -> drink.append("Coke");
			case DIET_COKE -> drink.append("Diet Coke");
			case PEPSI -> drink.append("Pepsi");
			case DR_PEPPER -> drink.append("Dr Pepper");
			case SPRITE -> drink.append("Sprite");
			case ROOT_BEER -> drink.append("Root Beer");
			case SWEET_TEA -> drink.append("Sweet Tea");
		}
		
		drink.append(" (");
		
		// Add size to the string
		switch (size) {
			case SMALL -> drink.append("Small");
			case MEDIUM -> drink.append("Medium");
			case LARGE -> drink.append("Large");
		}
		
		drink.append(") - $").append(String.format("%.2f", calcPrice()));  // Use a single count for display purposes
		
		return drink.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;  // Check for reference equality
		if (getClass() != obj.getClass()) return false;  // Ensure same class
		Drink drink = (Drink) obj;
		return type == drink.type && size == drink.size;  // Compare Drink-specific fields
	}
	
	public DrinkSize getSize() {
		return size;
	}
	
	public void setSize(DrinkSize size) {
		this.size = size;
	}
}