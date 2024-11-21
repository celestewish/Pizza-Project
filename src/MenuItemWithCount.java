/**
 * Class representing a menu item with a count (e.g., quantity).
 * This class encapsulates a `MenuItem` object along with its associated count,
 * providing methods to get, set, increment the count, and calculate the total price.
 */
public class MenuItemWithCount {
	private final MenuItem item;  // The MenuItem itself
	private int count;            // The count for this item

	/**
	 * Constructor to initialize the item and its count.
	 *
	 * @param item  The MenuItem object representing the menu item.
	 * @param count The count or quantity of the menu item.
	 */
	public MenuItemWithCount(MenuItem item, int count) {
		this.item = item;
		this.count = count;
	}

	/**
	 * Getter for the MenuItem object.
	 *
	 * @return The MenuItem object associated with this instance.
	 */
	public MenuItem getItem() {
		return item;
	}

	/**
	 * Getter for the count of the menu item.
	 *
	 * @return The count (quantity) of the menu item.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Setter for the count of the menu item.
	 *
	 * @param count The new count (quantity) of the menu item.
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Increments the count of the menu item by a specified amount,
	 * but caps the count at 10.
	 *
	 * @param count The number to increment the count by.
	 * @return true if the count was incremented successfully,
	 *         false if the count was capped at 10.
	 */
	public boolean incrementCount(int count) {
		if (this.count + count <= 10) {
			this.count += count;
			return true;
		}
		this.count = 10;  // Cap the count at 10
		return false;
	}

	/**
	 * Calculates the total price for this menu item based on its price and count.
	 * The total price is the price of the item multiplied by the count.
	 *
	 * @return The total price for the item (price * count).
	 */
	public float calcTotalPrice() {
		return item.calcPrice() * count;
	}

	/**
	 * Returns a string representation of the item and its count.
	 * The format is "count x item_description".
	 *
	 * @return A string representing the menu item and its quantity.
	 */
	@Override
	public String toString() {
		return count + " x " + item.toString();
	}
}