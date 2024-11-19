public class MenuItemWithCount {
	private final MenuItem item;  // The MenuItem itself
	private int count;            // The count for this item
	
	public MenuItemWithCount(MenuItem item, int count) {
		this.item = item;
		this.count = count;
	}
	
	public MenuItem getItem() {
		return item;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public boolean incrementCount(int count) {
		if (this.count + count <= 10) {
			this.count += count;
			return true;
		}
		this.count = 10;
		return false;
	}
	
	public float calcTotalPrice() {
		return item.calcPrice() * count;
	}
	
	@Override
	public String toString() {
		return count + " x " + item.toString();
	}
}