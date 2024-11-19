public class Drink extends MenuItem {
	private DrinkSize size;
	private DrinkType type;
	
	public Drink(DrinkSize size, DrinkType type, float price) {
		super(price);
		this.size = size;
		this.type = type;
	}
	
	@Override
	public float calcTotalCost() {
		float total = getPrice();
		
		switch (size) {
			case SMALL -> total += 0;
			case MEDIUM -> total += 1;
			case LARGE -> total += 1.5F;
		}
		
		return total * getCount();
	}
	
	@Override
	public String toString() {
		StringBuilder drink = new StringBuilder();
		
		// Append the type of drink
		switch (type) {
			case COKE -> drink.append("Coke");
			case DIET_COKE -> drink.append("Diet Coke");
			case PEPSI -> drink.append("Pepsi");
			case DR_PEPPER -> drink.append("Dr Pepper");
			case SPRITE -> drink.append("Sprite");
			case ROOT_BEER -> drink.append("Root Beer");
			case SWEET_TEA -> drink.append("Sweet Tea");
		}
		
		// Append the size
		drink.append(" (");
		switch (size) {
			case SMALL -> drink.append("Small");
			case MEDIUM -> drink.append("Medium");
			case LARGE -> drink.append("Large");
		}
		drink.append(")");
		
		// Append the price and count
		drink.append(" - $").append(String.format("%.2f", calcTotalCost()));
		
		return drink.toString();
	}
	
	public DrinkSize getSize() {
		return size;
	}
	
	public void setSize(DrinkSize size) {
		this.size = size;
	}
}
