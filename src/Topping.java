public class Topping {
	private final String name;
	private float price;
	private boolean extra;
	private toppingPlacement placement;
	
	public Topping(String name, float price, boolean extra, toppingPlacement placement) {
		this.name = name;
		this.price = price;
		this.extra = extra;
		this.placement = placement;
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public boolean isExtra() {
		return extra;
	}
	
	public void setExtra(boolean extra) {
		this.extra = extra;
	}
	
	public toppingPlacement getPlacement() {
		return placement;
	}
	
	public void setPlacement(toppingPlacement placement) {
		this.placement = placement;
	}
}
