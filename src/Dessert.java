/**
 * Class representing a Dessert item, which is a subclass of MenuItem.
 * This class defines a dessert with a specific type and price.
 */
public class Dessert extends MenuItem {
	private final DessertType type;  // The type of the dessert (enum)

	/**
	 * Constructs a Dessert with a specific type and price.
	 *
	 * @param type  The type of the dessert (e.g., cake, pie, etc.).
	 * @param price The price of the dessert.
	 */
	public Dessert(DessertType type, float price) {
		super(price);
		this.type = type;
	}

	/**
	 * Calculates the price of the dessert.
	 * This implementation returns the price that was set in the constructor.
	 *
	 * @return The price of the dessert.
	 */
	@Override
	public float calcPrice() {
		return getPrice();
	}

	/**
	 * Returns a string representation of the dessert, including its type and price.
	 * The price is formatted to two decimal places.
	 *
	 * @return A string representation of the dessert in the format "Type - $Price".
	 */
	@Override
	public String toString() {
		// Append the type of dessert and its price formatted to two decimal places
		return Utils.enumToNormalCase(type) + " - $" + String.format("%.2f", getPrice());
	}

	/**
	 * Compares two Dessert objects for equality based on their price and type.
	 * Two Dessert objects are considered equal if they have the same price and type.
	 *
	 * @param o The object to compare to.
	 * @return true if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;  // Check if they are the same object
		if (o == null || getClass() != o.getClass()) return false;  // Ensure the same class type
		Dessert dessert = (Dessert) o;  // Cast to Dessert to compare fields
		// Compare price and type to determine equality
		return Float.compare(dessert.getPrice(), getPrice()) == 0 && type == dessert.type;
	}

	/**
	 * Getter for the type of the dessert.
	 *
	 * @return The type of the dessert.
	 */
	public DessertType getType() {
		return type;
	}
}