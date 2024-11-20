public abstract class MenuItem {
	protected float price;
	
	public MenuItem() {
		this.price = 0;
	}
	
	public MenuItem(float price) {
		this.price = price;
	}
	
	public abstract float calcPrice();
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public abstract String toString();
	
	@Override
	public abstract boolean equals(Object obj);
}