import java.util.LinkedList;

public class Pizza extends MenuItem {
	private PizzaSize size;
	private CrustType crust;
	private SauceOption sauce; // true for marinara, false for alfredo
	private final LinkedList<Topping> toppings;
	
	public Pizza(PizzaSize size, CrustType crust, SauceOption sauce) {
		this.size = size;
		this.crust = crust;
		this.sauce = sauce;
		this.toppings = new LinkedList<>();
	}
	
	public Pizza() {
		toppings = new LinkedList<>();
	}
	
	@Override
	public float calcPrice() {
		float total = 0;
		
		// Calculate the cost based on crust type
		switch (crust) {
			case DEEP_DISH -> total += 2.25F;
			case THIN_CRUST -> total += .75F;
			case THICK_CRUST -> total += 1.5F;
		}
		
		// Calculate the cost based on pizza size
		switch (size) {
			case XL -> total += 17.5F;
			case LARGE -> total += 15F;
			case MEDIUM -> total += 12.25F;
			case SMALL -> total += 9.5F;
		}
		
		// Add the cost of toppings
		for (Topping t : toppings) {
			total += t.getPrice();
		}
		
		return total;
	}
	
	@Override
	public float getPrice() {
		return calcPrice();
	}
	
	@Override
	public String toString() {
		StringBuilder pizza = new StringBuilder();
		
		// Append the size of pizza dynamically
		pizza.append(Utils.enumToNormalCase(size)).append(" ");
		
		// Append the crust of pizza dynamically
		pizza.append(Utils.enumToNormalCase(crust)).append(" ");
		
		// Append the sauce dynamically
		pizza.append(Utils.enumToNormalCase(sauce)).append(" ");
		
		// Append the price (calculated with count externally)
		pizza.append(" - $").append(String.format("%.2f", getPrice()));
		
		// Append the toppings
		if (!toppings.isEmpty()) {
			pizza.append("\n   Toppings:\n");
			for (Topping topping : toppings) {
				pizza.append("        ").append(topping.toString()).append("\n");
			}
		}
		
		return pizza.toString();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;  // Check if both objects are the same instance
		if (obj == null || getClass() != obj.getClass()) return false;  // Ensure the objects are of the same class
		Pizza pizza = (Pizza) obj;  // Cast the object to Pizza
		// Compare pizza-specific fields (size, crust, sauce, and toppings)
		return size == pizza.size &&
				crust == pizza.crust &&
				sauce == pizza.sauce &&
				toppings.equals(pizza.toppings);  // Compare toppings list
	}
	
	public PizzaSize getSize() {
		return size;
	}
	
	public Pizza clone() {
		Pizza clone = new Pizza(this.size, this.crust, this.sauce);
		for (Topping topping : this.toppings) {
			clone.addTopping(new Topping(topping.getType(), topping.isExtra(), topping.getPlacement()));
		}
		return clone;
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
	
	public SauceOption getSauce() {
		return sauce;
	}
	
	public void setSauce(SauceOption sauce) {
		this.sauce = sauce;
	}
	
	public LinkedList<Topping> getToppings() {
		return toppings;
	}
	
	public Topping getTopping(ToppingType toppingType) {
		int index = 0;
		for (Topping t : toppings) {
			if (t.getType() == toppingType) {
				return toppings.get(index);
			}
			index++;
		}
		return null;
	}
	
	public void removeTopping(ToppingType toppingType) {
		toppings.removeIf(t -> t.getType() == toppingType);
	}
	
	public void addTopping(Topping topping) {
		for (Topping t : toppings) {
			if (t.getType() == topping.getType()) {
				return;
			}
		}
		toppings.addLast(topping);
	}
}
