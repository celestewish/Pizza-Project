import java.util.LinkedList;

public class Pizza extends MenuItem {
	pizzaSize size;
	crustType crust;
	LinkedList<Topping> toppings;
	
	public Pizza(pizzaSize size, crustType crust) {
		this.size = size;
		this.crust = crust;
		toppings = new LinkedList<>();
	}
	
}
