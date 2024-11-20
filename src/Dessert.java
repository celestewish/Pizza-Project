public class Dessert extends MenuItem {
	private final DessertType type;
	
	public Dessert(DessertType type, float price) {
		super(price);
		this.type = type;
	}
	
	@Override
	public float calcPrice() {
		return getPrice();
	}
	
	@Override
	public String toString() {
		// Append the type of dessert
		return Utils.enumToNormalCase(type) + " - $" + String.format("%.2f", getPrice());
				// Append the price
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;  // Check if they are the same object
		if (o == null || getClass() != o.getClass()) return false;  // Ensure the same class type
		Dessert dessert = (Dessert) o;  // Cast to Dessert to compare fields
		return Float.compare(dessert.getPrice(), getPrice()) == 0 && type == dessert.type;  // Compare price and type
	}
	
	public DessertType getType() {
		return type;
	}
}
