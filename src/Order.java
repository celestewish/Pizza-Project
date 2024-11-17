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
	
	public Order(LinkedList<MenuItem> items) {
		orderNumber = orderCounter;
		orderCounter++;
		this.items = items;
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
	
	public deliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}
	
	public void setDeliveryMethod(deliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	
	public boolean isCash() {
		return cash;
	}
	
	public void setCash(boolean cash) {
		this.cash = cash;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}
}
