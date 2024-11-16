import java.util.LinkedList;

public class Pizza extends MenuItem {
	private pizzaSize size;
	private crustType crust;
	private boolean sauce; // true for marinara, false for alfredo
	private final LinkedList<Topping> toppings;
	
	public Pizza(pizzaSize size, crustType crust, boolean sauce) {
		this.size = size;
		this.crust = crust;
		this.sauce = sauce;
		toppings = new LinkedList<>();
	}
	
	public pizzaSize getSize() {
		return size;
	}
	
	public void setSize(pizzaSize size) {
		this.size = size;
	}
	
	public crustType getCrust() {
		return crust;
	}
	
	public void setCrust(crustType crust) {
		this.crust = crust;
	}
	
	public boolean isSauce() {
		return sauce;
	}
	
	public void setSauce(boolean sauce) {
		this.sauce = sauce;
	}
	
	public LinkedList<Topping> getToppings() {
		return toppings;
	}
}
