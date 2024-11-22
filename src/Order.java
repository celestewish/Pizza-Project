import java.util.LinkedList;

/**
 * Represents an order containing a list of menu items with their respective quantities,
 * along with delivery and payment methods.
 * This class provides functionality to calculate the total cost, add/remove items, and more.
 */
public class Order {
	private static int orderCounter = 1;
	private final int orderNumber;
	private final LinkedList<MenuItemWithCount> items; // Using MenuItemWithCount
	private DeliveryMethod deliveryMethod;
	private boolean cash;
	private Payment payment;

	/**
	 * Constructor to create an order with a list of items, delivery method, payment type, and payment information.
	 *
	 * @param items          The list of `MenuItemWithCount` representing the items in the order.
	 * @param deliveryMethod The delivery method for the order (e.g., delivery or pickup).
	 * @param cash           True if the payment is made by cash; false otherwise.
	 * @param payment        The payment object if the payment is not made by cash.
	 */
	public Order(LinkedList<MenuItemWithCount> items, DeliveryMethod deliveryMethod, boolean cash, Payment payment) {
		orderNumber = orderCounter++;
		this.items = items;
		this.deliveryMethod = deliveryMethod;
		this.cash = cash;
		this.payment = payment;
	}

	/**
	 * Constructor to create an order with a list of items, but without specifying payment or delivery method.
	 *
	 * @param items The list of `MenuItemWithCount` representing the items in the order.
	 */
	public Order(LinkedList<MenuItemWithCount> items) {
		orderNumber = orderCounter++;
		this.items = items;
	}

	/**
	 * Calculates the total cost of the order, including item prices and delivery charges.
	 * If the delivery method is delivery, an additional charge is applied.
	 *
	 * @return The total cost of the order, including any delivery charges.
	 */
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

	/**
	 * Breaks down the total cost into categories by item type (e.g., pizza, drink, salad, etc.).
	 *
	 * @return A float array containing the total cost for each item category.
	 */
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

	/**
	 * Getter for the list of items in the order.
	 *
	 * @return The list of `MenuItemWithCount` objects representing the items in the order.
	 */
	public LinkedList<MenuItemWithCount> getItems() {
		return items;
	}

	/**
	 * Adds a menu item to the order, updating the count if the item already exists.
	 * If the item already exists in the order, its count will be incremented.
	 *
	 * @param item The `MenuItemWithCount` object representing the item to be added.
	 * @return 1 if the item was added, 0 if the count was incremented, or -1 if the count could not be incremented.
	 */
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

	/**
	 * Gets the total number of pizzas in the order.
	 *
	 * @return The total count of pizzas in the order.
	 */
	public int getNumberPizzasInOrder() {
		int total = 0;
		for (MenuItemWithCount item : items) {
			if (item.getItem() instanceof Pizza) {
				total += item.getCount();
			}
		}
		return total;
	}

	/**
	 * Removes a menu item from the order.
	 *
	 * @param item The `MenuItem` to be removed from the order.
	 * @return True if the item was successfully removed, false otherwise.
	 */
	public boolean removeItem(MenuItem item) {
		for (MenuItemWithCount itemWithCount : items) {
			if (itemWithCount.getItem().equals(item)) {
				items.remove(itemWithCount); // Remove the item
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter for the delivery method of the order.
	 *
	 * @return The `DeliveryMethod` for this order.
	 */
	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}

	/**
	 * Setter for the delivery method of the order.
	 *
	 * @param deliveryMethod The new `DeliveryMethod` for this order.
	 */
	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	/**
	 * Checks if the payment is made by cash.
	 *
	 * @return True if the payment is made by cash, false otherwise.
	 */
	public boolean isCash() {
		return cash;
	}

	/**
	 * Setter for the cash payment status.
	 *
	 * @param cash True if the payment is made by cash, false otherwise.
	 */
	public void setCash(boolean cash) {
		this.cash = cash;
	}

	/**
	 * Getter for the payment method used for this order.
	 *
	 * @return The `Payment` object representing the payment method.
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * Setter for the payment method used for this order.
	 *
	 * @param payment The `Payment` object to set as the payment method for this order.
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	/**
	 * Getter for the order number.
	 *
	 * @return The unique order number for this order.
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Returns a string representation of the order, including order details,
	 * items, delivery method, payment method, and total cost.
	 *
	 * @return A string representation of the order.
	 */
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
