import java.util.LinkedList;

public class Pizza extends MenuItem {
	private PizzaSize size;
	private CrustType crust;
	private SauceOption sauce; // true for marinara, false for alfredo
	private final LinkedList<Topping> toppings;


	// Constructor to create a Pizza with specified size, crust, and sauce
	public Pizza(PizzaSize size, CrustType crust, SauceOption sauce) {
		this.size = size;
		this.crust = crust;
		this.sauce = sauce;
		this.toppings = new LinkedList<>(); // Initialize the toppings list
	}

	// Default constructor to create a Pizza with no size, crust, or sauce (empty toppings list)
	public Pizza() {
		toppings = new LinkedList<>(); // Initialize the toppings list
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

	// Returns the price of the pizza by calling calcPrice()
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
	
	public String shortToString() {
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
			pizza.append(", Toppings:");
			for (Topping topping : toppings) {
				pizza.append(" ").append(topping.shortToString());
			}
		}
		
		return pizza.toString();
	}


	// Checks if the current pizza is equal to another pizza by comparing their size, crust, sauce, and toppings
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

	// Getter for the size of the pizza
	public PizzaSize getSize() {
		return size;
	}

	// Creates and returns a clone of the current pizza, including its toppings
	public Pizza clone() {
		Pizza clone = new Pizza(this.size, this.crust, this.sauce);
		// Add each topping to the cloned pizza
		for (Topping topping : this.toppings) {
			clone.addTopping(new Topping(topping.getType(), topping.isExtra(), topping.getPlacement()));
		}
		return clone;
	}

	// Setter for the size of the pizza
	public void setSize(PizzaSize size) {
		this.size = size;
	}

	// Getter for the crust type of the pizza
	public CrustType getCrust() {
		return crust;
	}

	// Setter for the crust type of the pizza
	public void setCrust(CrustType crust) {
		this.crust = crust;
	}

	// Getter for the sauce option of the pizza
	public SauceOption getSauce() {
		return sauce;
	}

	// Setter for the sauce option of the pizza
	public void setSauce(SauceOption sauce) {
		this.sauce = sauce;
	}

	// Getter for the list of toppings on the pizza
	public LinkedList<Topping> getToppings() {
		return toppings;
	}

	// Retrieves a specific topping by its type, if present
	public Topping getTopping(ToppingType toppingType) {
		int index = 0;
		// Iterate through the toppings list to find the specified topping
		for (Topping t : toppings) {
			if (t.getType() == toppingType) {
				return toppings.get(index);  // Return the topping if found
			}
			index++;
		}
		return null;  // Return null if the topping is not found
	}

	// Removes a topping from the pizza by its type
	public void removeTopping(ToppingType toppingType) {
		// Remove the topping matching the specified type
		toppings.removeIf(t -> t.getType() == toppingType);
	}

	// Adds a new topping to the pizza if it's not already present
	public void addTopping(Topping topping) {
		// Check if the topping is already in the list
		for (Topping t : toppings) {
			if (t.getType() == topping.getType()) {
				return;  // Do nothing if the topping is already added
			}
		}
		toppings.addLast(topping);  // Add the topping if not already present
	}
}
