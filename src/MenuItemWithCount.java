// Class representing a menu item with a count (e.g., quantity)
public class MenuItemWithCount {
	private final MenuItem item;  // The MenuItem itself
	private int count;            // The count for this item

	// Constructor to initialize the item and its count
	public MenuItemWithCount(MenuItem item, int count) {
		this.item = item;
		this.count = count;
	}

	// Getter for the MenuItem object
	public MenuItem getItem() {
		return item;
	}

	// Getter for the count of the menu item
	public int getCount() {
		return count;
	}

	// Setter for the count of the menu item
	public void setCount(int count) {
		this.count = count;
	}
	
	public void increaseCount() {
		if (count < 10) {
			count++;
		}
	}
	
	public void decreaseCount() {
		if (count > 1) {
			count--;
		}
	}
	
	// Increments the count by a specified amount (up to a maximum of 10)
	public boolean incrementCount(int count) {
		if (this.count + count <= 10) {
			this.count += count;
			return true;
		}
		this.count = 10;  // Cap the count at 10
		return false;
	}

	// Calculates the total price for this menu item (price * count)
	public float calcTotalPrice() {
		return item.calcPrice() * count;
	}

	// Returns a string representation of the item and its count
	@Override
	public String toString() {
		return count + " x " + item.toString();
	}
	
	public String shortToString() {
		if (item instanceof Pizza)
			return count + " x " + ((Pizza) item).shortToString();
		else
			return toString();
	}
}