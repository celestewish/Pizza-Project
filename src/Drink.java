public class Drink extends MenuItem {
	private DrinkSize size;
	
	public Drink(DrinkSize size, float price) {
		super(price);
		this.size = size;
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
	
	public DrinkSize getSize() {
		return size;
	}
	
	public void setSize(DrinkSize size) {
		this.size = size;
	}
}
