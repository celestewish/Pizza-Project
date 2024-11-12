public abstract class MenuItem {
	private static int idCounter = 1000;
	private final int itemID;
	private String name;
	private float cost;
	private int count;
	
	public MenuItem(String name, float cost) {
		itemID = idCounter;
		idCounter++;
		this.name = name;
		this.cost = cost;
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
}
