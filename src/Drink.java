/**
 * Class representing a Drink item, which is a subclass of MenuItem.
 * This class defines a drink with a specific size, type, and price.
 */
public class Drink extends MenuItem {
	private DrinkSize size;
	private final DrinkType type;

	/**
	 * Constructs a Drink with a specified size, type, and price.
	 *
	 * @param size  The size of the drink (e.g., SMALL, MEDIUM, LARGE).
	 * @param type  The type of the drink (e.g., Soda, Juice).
	 * @param price The base price of the drink.
	 */
	public Drink(DrinkSize size, DrinkType type, float price) {
		super(price);
		this.size = size;
		this.type = type;
	}

	/**
	 * Calculates the total price of the drink, including any size-related price modifications.
	 * The price is adjusted based on the size of the drink:
	 * - SMALL: No additional cost.
	 * - MEDIUM: Adds $1 to the base price.
	 * - LARGE: Adds $1.50 to the base price.
	 *
	 * @return The total price of the drink.
	 */
	@Override
	public float calcPrice() {
		float total = getPrice();
		
		// Add additional price based on the size
		switch (size) {
			case SMALL -> total += 0;
			case MEDIUM -> total += 1;
			case LARGE -> total += 1.5F;
			case AMERICAN_SIZE -> total += 5;
		}

		return total;
	}

	/**
	 * Returns a string representation of the drink, including its type, size, and calculated price.
	 * The size and type are formatted using the {@link Utils#enumToNormalCase} method.
	 *
	 * @return A string representation of the drink in the format "Type (Size) - $Price".
	 */
	@Override
	public String toString() {
		// Append the drink type using Utils.enumToNormalCase
		return Utils.enumToNormalCase(type) +
				// Append the size dynamically
				" (" + Utils.enumToNormalCase(size) + ")" +  // Using enumToNormalCase for size
				
				// Append price
				" - $" + String.format("%.2f", calcPrice());
	}

	/**
	 * Compares this Drink object with another object for equality.
	 * Two Drink objects are considered equal if they have the same type and size.
	 *
	 * @param obj The object to compare to.
	 * @return true if the objects are equal (same type and size), false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;  // Check for reference equality
		if (getClass() != obj.getClass()) return false;  // Ensure both objects are of the same class
		Drink drink = (Drink) obj;  // Cast the object to Drink for field comparison
		// Compare the Drink-specific fields: type and size
		return type == drink.type && size == drink.size;
	}

	/**
	 * Getter for the size of the drink.
	 *
	 * @return The size of the drink.
	 */
	public DrinkSize getSize() {
		return size;
	}

	/**
	 * Setter for the size of the drink.
	 *
	 * @param size The new size to set for the drink.
	 */
	public void setSize(DrinkSize size) {
		this.size = size;
	}
}