import java.util.LinkedList;

public class Order {
	private static int orderCounter = 1;
	private final int orderNumber;
	private final LinkedList<MenuItem> items;
	private DeliveryMethod deliveryMethod;
	private boolean cash;
	private Payment payment;
	
	public Order(LinkedList<MenuItem> items, DeliveryMethod deliveryMethod, boolean cash, Payment payment) {
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
	
	public float calcTotalOrderCost() {
		float total = 0;
		
		for (MenuItem item : items)
			total += item.calcTotalCost();
		
		if (deliveryMethod != null)
			if (deliveryMethod.equals(DeliveryMethod.DELIVERY))
				total += 5F;
		
		return total;
	}
	
	public void addItem(MenuItem item) {
		if (items.contains(item)) {
			items.get(items.indexOf(item)).incrementCount(item.getCount());
			return;
		}
		items.add(item);
	}
	
	public boolean removeItem(MenuItem item) {
		if (!items.contains(item))
			return false;
		items.remove(item);
		return true;
	}
	
	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}
	
	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
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
