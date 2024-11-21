/**
 * Abstract class representing a menu item with a price.
 * This class serves as the base for different types of menu items (e.g., drinks, desserts)
 * and provides methods to handle the price and common behaviors.
 */
public abstract class MenuItem {
	// The price of the menu item
	protected float price;

	/**
	 * Default constructor that sets the price of the menu item to 0.
	 */
	public MenuItem() {
		this.price = 0;
	}

	/**
	 * Constructor that initializes the price of the menu item to the specified value.
	 *
	 * @param price The price of the menu item.
	 */
	public MenuItem(float price) {
		this.price = price;
	}

	/**
	 * Getter for the price of the menu item.
	 *
	 * @return The price of the menu item.
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Setter for the price of the menu item.
	 *
	 * @param price The new price to set for the menu item.
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Abstract method to calculate the price of the menu item.
	 * This method should be overridden by subclasses to provide specific price calculation logic.
	 *
	 * @return The calculated price of the menu item.
	 */
	public abstract float calcPrice();

	/**
	 * Abstract method for converting the menu item to a string representation.
	 * This method should be implemented by subclasses to provide a specific string format.
	 *
	 * @return A string representation of the menu item.
	 */
	@Override
	public abstract String toString();

	/**
	 * Abstract method for comparing this menu item with another object for equality.
	 * This method should be overridden by subclasses to define equality criteria.
	 *
	 * @param obj The object to compare this menu item to.
	 * @return true if the objects are equal, false otherwise.
	 */
	@Override
	public abstract boolean equals(Object obj);
}