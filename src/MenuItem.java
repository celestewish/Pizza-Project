// Abstract class representing a menu item with a price
public abstract class MenuItem {
	// The price of the menu item
	protected float price;

	// Default constructor setting price to 0
	public MenuItem() {
		this.price = 0;
	}

	// Constructor that initializes the price to a specified value
	public MenuItem(float price) {
		this.price = price;
	}

	// Getter for the price of the menu item
	public float getPrice() {
		return price;
	}

	// Setter for the price of the menu item
	public void setPrice(float price) {
		this.price = price;
	}

	// Abstract method to calculate the price of the menu item (could be overridden)
	public abstract float calcPrice();

	// Abstract method for converting the menu item to a string representation
	@Override
	public abstract String toString();

	// Abstract method for comparing menu items for equality
	@Override
	public abstract boolean equals(Object obj);
}