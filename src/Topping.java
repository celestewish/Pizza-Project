public class Topping {
	private final ToppingType type;
	private boolean extra;
	private ToppingPlacement placement;
	
	public Topping(ToppingType type, boolean extra, ToppingPlacement placement) {
		this.type = type;
		this.extra = extra;
		this.placement = placement;
	}
	
	@Override
	public String toString() {
		StringBuilder topping = new StringBuilder(Utils.enumToNormalCase(type));
		topping.append(" ");
		
		// Append Extra if applicable
		if (extra)
			topping.append("Extra").append(" ");
		
		// Append placement (using enumToNormalCase for readable enum value)
		topping.append(Utils.enumToNormalCase(placement)); // Convert the placement enum to a readable string
		
		return topping.toString();
	}
	
	public ToppingType getType() {
		return type;
	}
	
	public float calcTotalPrice() {
		float total = 0;
		
		switch (type) {
			case BACON, CHICKEN, PEPPERONI -> total += .5F;
			case OLIVES, ONIONS -> total += .1F;
			case PEPPERS -> total += .2F;
			case SPINACH -> total += .15F;
			case SAUSAGE -> total += .75F;
			case MUSHROOMS -> total += .25F;
			case GROUND_BEEF -> total += 1F;
		}
		
		total = extra ? total * 1.15F : total;
		
		if (!placement.equals(ToppingPlacement.WHOLE))
			total /= 1.85F;
		
		return total;
	}
	
	public float getPrice() {
		return calcTotalPrice();
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
