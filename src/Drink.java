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
		// Append the drink type using Utils.enumToNormalCase
		return Utils.enumToNormalCase(type) +
				// Append the size dynamically
				" (" + Utils.enumToNormalCase(size) + ")" +  // Using enumToNormalCase for size
				
				// Append price
				" - $" + String.format("%.2f", calcPrice());
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