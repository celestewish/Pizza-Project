public class Drink extends MenuItem {
	private int size;
	
	public Drink(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public String sizeToString() {
		switch (size) {
			case 0:
				return "Small";
			case 1:
				return "Medium";
			case 2:
				return "Large";
		}
		return "";
	}
}
