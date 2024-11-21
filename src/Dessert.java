// Class representing a Dessert item, subclass of MenuItem
public class Dessert extends MenuItem {
	private final DessertType type;  // The type of the dessert (enum)

	// Constructor to initialize Dessert with type and price
	public Dessert(DessertType type, float price) {
		super(price);
		this.type = type;
	}

	// Calculates the price of the dessert (returns the price set in the constructor)
	@Override
	public float calcPrice() {
		return getPrice();
	}

	// Returns a string representation of the dessert (type and price)
	@Override
	public String toString() {
		// Append the type of dessert and its price formatted to two decimal places
		return Utils.enumToNormalCase(type) + " - $" + String.format("%.2f", getPrice());
	}

	// Checks equality of two Dessert objects (based on price and type)
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;  // Check if they are the same object
		if (o == null || getClass() != o.getClass()) return false;  // Ensure the same class type
		Dessert dessert = (Dessert) o;  // Cast to Dessert to compare fields
		// Compare price and type to determine equality
		return Float.compare(dessert.getPrice(), getPrice()) == 0 && type == dessert.type;
	}

	// Getter for the dessert type
	public DessertType getType() {
		return type;
	}
}