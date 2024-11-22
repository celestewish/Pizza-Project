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
		topping.append(" (");
		switch (placement) {
			case WHOLE -> topping.append("Whole Pizza");
			case RIGHT -> topping.append("Right Side of Pizza");
			case LEFT -> topping.append("Left Side of Pizza");
		}
		topping.append(")"); // Convert the placement enum to a readable string

		return topping.toString();
	}
	
	public String shortToString() {
		return Utils.enumToNormalCase(type);
	}
	

	// Getter for the type of the topping
	public ToppingType getType() {
		return type;
	}

	// Calculates the total price for the topping based on its type, extra status, and placement
	public float calcTotalPrice() {
		float total = 0;

		// Add base price based on the topping type
		switch (type) {
			case BACON, CHICKEN, PEPPERONI -> total += .5F;
			case OLIVES, ONIONS -> total += .1F;
			case PEPPERS -> total += .2F;
			case SPINACH -> total += .15F;
			case SAUSAGE -> total += .75F;
			case MUSHROOMS -> total += .25F;
			case GROUND_BEEF -> total += 1F;
		}

		// Apply extra price multiplier if the topping is extra
		total = extra ? total * 1.15F : total;

		// Adjust the price based on the placement of the topping
		if (!placement.equals(ToppingPlacement.WHOLE))
			total /= 1.85F;

		return total;  // Return the calculated total price
	}

	// Getter for the price of the topping, which calls calcTotalPrice()
	public float getPrice() {
		return calcTotalPrice();
	}

	// Checks if the topping is extra (i.e., has an additional cost)
	public boolean isExtra() {
		return extra;
	}

	// Sets whether the topping is extra, affecting the total price
	public void setExtra(boolean extra) {
		this.extra = extra;
	}

	// Getter for the placement of the topping (e.g., whole, scattered)
	public ToppingPlacement getPlacement() {
		return placement;
	}

	// Sets the placement of the topping (e.g., whole, scattered)
	public void setPlacement(ToppingPlacement placement) {
		this.placement = placement;
	}
}
