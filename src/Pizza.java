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
	
	public boolean addTopping(Topping topping) {
		if (toppings.contains(topping))
			return false;
		toppings.addLast(topping);
		return true;
	}
}
