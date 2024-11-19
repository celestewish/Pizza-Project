public class Dessert extends MenuItem {
	DessertType type;
	
	public Dessert(DessertType type, float price) {
		super(price);
		this.type = type;
	}
	
	@Override
	public float calcPrice() {
		return getPrice();
	}
	
	@Override
	public String toString() {
		StringBuilder dessert = new StringBuilder();
		
		// Append the type of dessert
		switch (type) {
			case CHOCOLATE_LAVA_CAKE -> dessert.append("Chocolate Lava Cake");
			case CHEESECAKE -> dessert.append("Cheesecake");
			case COOKIES -> dessert.append("Cookies");
			case BROWNIE -> dessert.append("Brownie");
			case CINNAMON_KNOTS -> dessert.append("Cinnamon Knots");
		}
		
		// Append the price
		dessert.append(" - $").append(String.format("%.2f", getPrice()));
		
		return dessert.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;  // Check if they are the same object
		if (o == null || getClass() != o.getClass()) return false;  // Ensure the same class type
		Dessert dessert = (Dessert) o;  // Cast to Dessert to compare fields
		return Float.compare(dessert.getPrice(), getPrice()) == 0 && type == dessert.type;  // Compare price and type
	}
	
	public DessertType getType() {
		return type;
	}
}
