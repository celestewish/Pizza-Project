import java.util.LinkedList;

public class Pizza extends MenuItem {
	private PizzaSize size;
	private CrustType crust;
	private boolean sauce; // true for marinara, false for alfredo
	private final LinkedList<Topping> toppings;
	
	public Pizza(PizzaSize size, CrustType crust, boolean sauce) {
		this.size = size;
		this.crust = crust;
		this.sauce = sauce;
		toppings = new LinkedList<>();
	}
	
	@Override
	public float calcTotalCost() {
		float total = 0;
		
		switch (crust) {
			case DEEP_DISH -> total += 2.25F;
			case THIN_CRUST -> total += .75F;
			case THICK_CRUST -> total += 1.5F;
		}
		
		switch (size) {
			case XL -> total += 17.5F;
			case LARGE -> total += 15F;
			case MEDIUM -> total += 12.25F;
			case SMALL -> total += 9.5F;
		}
		
		for (Topping t : toppings) {
			total += t.calcTotalPrice();
		}
		
		return total * getCount();
	}
	
	public PizzaSize getSize() {
		return size;
	}
	
	public void setSize(PizzaSize size) {
		this.size = size;
	}
	
	public CrustType getCrust() {
		return crust;
	}
	
	public void setCrust(CrustType crust) {
		this.crust = crust;
	}
	
	public boolean getSauce() {
		return sauce;
	}
	
	public void setSauce(boolean sauce) {
		this.sauce = sauce;
	}
	
	public LinkedList<Topping> getToppings() {
		return toppings;
	}
	
	public void addTopping(Topping topping) {
		if (toppings.contains(topping))
			return;
		toppings.addLast(topping);
	}
}
