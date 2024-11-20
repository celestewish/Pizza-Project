public class Topping {
	private final String name;
	private float basePrice;
	private boolean extra;
	private ToppingPlacement placement;
	
	public Topping(String name, float basePrice, boolean extra, ToppingPlacement placement) {
		this.name = name;
		this.basePrice = basePrice;
		this.extra = extra;
		this.placement = placement;
	}
	
	@Override
	public String toString() {
		StringBuilder topping = new StringBuilder(name);
		topping.append(" ");
		
		// Append Extra if applicable
		if (extra)
			topping.append("Extra").append(" ");
		
		// Append placement (using enumToNormalCase for readable enum value)
		topping.append(Utils.enumToNormalCase(placement)); // Convert the placement enum to a readable string
		
		return topping.toString();
	}
	
	public String getName() {
		return name;
	}
	
	public float calcTotalPrice() {
		float total = extra ? basePrice*=1.1F : basePrice;
		
		if (!placement.equals(ToppingPlacement.WHOLE))
			total /= 1.85F;
		
		return total;
	}
	
	public float getBasePrice() {
		return basePrice;
	}
	
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	
	public boolean isExtra() {
		return extra;
	}
	
	public void setExtra(boolean extra) {
		this.extra = extra;
	}
	
	public ToppingPlacement getPlacement() {
		return placement;
	}
	
	public void setPlacement(ToppingPlacement placement) {
		this.placement = placement;
	}
}
