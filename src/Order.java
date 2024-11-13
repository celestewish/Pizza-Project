import java.util.LinkedList;

public class Order {
	private static int orderCounter = 1;
	private final int orderNumber;
	private final LinkedList<MenuItem> items;
	private deliveryMethod deliveryMethod;
	private boolean cash;
	private Payment payment;
	
	public Order(LinkedList<MenuItem> items, deliveryMethod deliveryMethod, boolean cash, Payment payment) {
		orderNumber = orderCounter;
		orderCounter++;
		this.items = items;
		this.deliveryMethod = deliveryMethod;
		this.cash = cash;
		this.payment = payment;
	}
	
	public boolean addItem(MenuItem item) {
		if (items.contains(item)) {
			items.get(items.indexOf(item)).incrementCount(item.getCount());
			return false;
		}
		items.add(item);
		return true;
	}
	
	public boolean removeItem(int itemID) {
		for (MenuItem i : items) {
			if (i.getItemID() == itemID) {
				items.remove(i);
				return true;
			}
		}
		return false;
	}
}
