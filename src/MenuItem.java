public abstract class MenuItem {
	private static int idCounter = 1000;
	private final int itemID;
	private float cost;
	private int count;
	
	public MenuItem() {
		itemID = idCounter;
		idCounter++;
		count = 1;
	}
	
	public int getItemID() {
		return itemID;
	}
	
	public float getCost() {
		return cost;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void incrementCount(int count) {
		this.count += count;
	}
}
