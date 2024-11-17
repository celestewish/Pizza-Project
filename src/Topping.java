public class Topping {
	private final String name;
	private float price;
	private boolean extra;
	private String placement;
	
	public Topping(String name, float price, boolean extra, String placement) {
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
	
	public String getPlacement() {
		return placement;
	}
	
	public void setPlacement(String placement) {
		this.placement = placement;
	}
}
