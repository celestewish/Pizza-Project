public abstract class MenuItem {
	private float price;
	private int count;
	
	public MenuItem(float price) {
		this.price = price;
		count = 1;
	}
	
	public float calcTotalCost() {
		return price * count;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void incrementCount(int count) {
		this.count += count;
	}
}
