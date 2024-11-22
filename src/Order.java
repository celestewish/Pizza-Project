import java.util.LinkedList;

public class Order {
	private static int orderCounter = 1;
	private final int orderNumber;
	private final LinkedList<MenuItemWithCount> items; // Using MenuItemWithCount
	private DeliveryMethod deliveryMethod;
	private boolean cash;
	private Payment payment;
	
	public Order(LinkedList<MenuItemWithCount> items, DeliveryMethod deliveryMethod, boolean cash, Payment payment) {
		orderNumber = orderCounter++;
		this.items = items;
		this.deliveryMethod = deliveryMethod;
		this.cash = cash;
		this.payment = payment;
	}
	
	public Order(LinkedList<MenuItemWithCount> items) {
		orderNumber = orderCounter++;
		this.items = items;
	}
	
	// Calculates the total cost of the order including the count
	public float calcTotalOrderCost() {
		float total = 0;
		
		// Iterate over items and calculate total, including count from MenuItemWithCount
		for (MenuItemWithCount itemWithCount : items) {
			total += itemWithCount.calcTotalPrice();
		}
		
		if (deliveryMethod != null && deliveryMethod.equals(DeliveryMethod.DELIVERY)) {
			total += 5F; // Delivery charge
		}
		
		return total;
	}
	
	public float[] totalCostBreakDown() {
		float pizzaTotal = 0;
		float drinkTotal = 0;
		float saladTotal = 0;
		float breadTotal = 0;
		float knotTotal = 0;
		float wingTotal = 0;
		float dessertTotal = 0;
		
		// Iterate through items and accumulate costs based on item type
		for (MenuItemWithCount m : items) {
			MenuItem item = m.getItem();
			
			if (item instanceof Pizza) {
				pizzaTotal += m.calcTotalPrice(); // Use calcTotalPrice from MenuItemWithCount
			} else if (item instanceof Drink) {
				drinkTotal += m.calcTotalPrice(); // Use calcTotalPrice from MenuItemWithCount
			} else if (item instanceof Side side) {
				switch (side.getType()) {
					case CAESAR_SALAD -> saladTotal += m.calcTotalPrice(); // Use calcTotalPrice from MenuItemWithCount
					case GARLIC_BREAD -> breadTotal += m.calcTotalPrice(); // Use calcTotalPrice from MenuItemWithCount
					case GARLIC_KNOTS -> knotTotal += m.calcTotalPrice(); // Use calcTotalPrice from MenuItemWithCount
					case WINGS -> {
						wingTotal += m.calcTotalPrice(); // Use calcTotalPrice from MenuItemWithCount
					}
				}
			} else if (item instanceof Dessert) {
				dessertTotal += m.calcTotalPrice(); // Use calcTotalPrice from MenuItemWithCount
			}
		}
		
		return new float[]{pizzaTotal, drinkTotal, saladTotal, breadTotal, knotTotal, wingTotal, dessertTotal};
	}
	
	public LinkedList<MenuItemWithCount> getItems() {
		return items;
	}
	
	// Add item with count to the order. If item already exists, increment count.
	public int addItem(MenuItemWithCount item) {
		for (MenuItemWithCount itemWithCount : items) {
			if (itemWithCount.getItem().equals(item.getItem())) {
				if (itemWithCount.incrementCount(item.getCount()))
					return 0;
				return -1; // Item already exists, count updated
			}
		}
		// If item doesn't exist, add a new item with count
		items.add(item);
		return 1;
	}
	
	public int getNumberPizzasInOrder() {
		int total = 0;
		for (MenuItemWithCount item : items) {
			if (item.getItem() instanceof Pizza) {
				total += item.getCount();
			}
		}
		return total;
	}
	
	// Remove item from the order
	public boolean removeItem(MenuItem item) {
		for (MenuItemWithCount itemWithCount : items) {
			if (itemWithCount.getItem().equals(item)) {
				items.remove(itemWithCount); // Remove the item
				return true;
			}
		}
		return false;
	}

	// Getter for the delivery method
	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}

	// Setter for the delivery method
	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	// Checks if the payment method is cash
	public boolean isCash() {
		return cash;
	}

	// Setter for the cash payment status
	public void setCash(boolean cash) {
		this.cash = cash;
	}

	// Getter for the payment object associated with the order
	public Payment getPayment() {
		return payment;
	}

	// Setter for the payment object associated with the order
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	// Getter for the order number
	public int getOrderNumber() {
		return orderNumber;
	}
	
	@Override
	public String toString() {
		StringBuilder orderDetails = new StringBuilder();
		
		// Append the order number
		orderDetails.append("Order Number: ").append(orderNumber).append("\n");
		
		// Append the items in the order
		orderDetails.append("Items:\n");
		for (MenuItemWithCount m : items) {
			orderDetails.append(m.toString()).append("\n");  // Use MenuItemWithCount toString method
		}
		
		// Append delivery method
		if (deliveryMethod != null) {
			orderDetails.append("Delivery Method: ").append(deliveryMethod).append("\n");
		}
		
		// Append payment information (cash or payment method)
		if (payment != null) {
			if (cash) {
				orderDetails.append("Payment: Cash\n");
			} else {
				orderDetails.append("Payment Method: ").append(payment).append("\n");
			}
		}
		
		// Append total cost of the order
		orderDetails.append("Total Order Cost: $").append(String.format("%.2f", calcTotalOrderCost())).append("\n");
		
		return orderDetails.toString();
	}
}
