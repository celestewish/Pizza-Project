public abstract class MenuItem {
	private float price;
	private int count;
	
	public MenuItem() {
		count = 1;
	}
	
	public MenuItem(float price) {
		this.price = price;
		count = 1;
	}
	
	public MenuItem(float price, int count) {
		this.price = price;
		this.count = count;
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
	
	@Override
	public abstract String toString();
}
