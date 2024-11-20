public class Wings extends Side{
	private final WingType wingType;
	
	public Wings(SideType type, float price, int count, WingType wingType) {
		super(type, price, count);
		this.wingType = wingType;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Wings wings = (Wings) o;
		
		// Compare the WingType and count for equality
		return getWingType() == wings.getWingType() &&  // Compare WingType
				getCount() == wings.getCount();  // Compare count
	}
	
	public WingType getWingType() {
		return wingType;
	}
}
