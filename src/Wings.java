/**
 * The {@code Wings} class represents a side order of wings in the menu system. 
 * It extends the {@code Side} class and adds a specific type of wings through 
 * the {@code WingType} enumeration.
 */
public class Wings extends Side {
	
	/** The type of wings */
	private final WingType wingType;
	
	/**
	 * Constructs a {@code Wings} object with the specified attributes.
	 *
	 * @param type     the type of side dish (from {@code SideType})
	 * @param price    the price of the wings
	 * @param count    the number of wings in the order
	 * @param wingType the specific type of wings (from {@code WingType})
	 */
	public Wings(SideType type, float price, int count, WingType wingType) {
		super(type, price, count); // Call the constructor of the parent class (Side)
		this.wingType = wingType; // Initialize the specific wing type
	}
	
	/**
	 * Checks if this {@code Wings} object is equal to another object.
	 * Two {@code Wings} objects are considered equal if:
	 * <ul>
	 *     <li>They are the same instance</li>
	 *     <li>Their {@code WingType} and {@code count} are equal</li>
	 * </ul>
	 *
	 * @param o the object to compare this instance against
	 * @return {@code true} if the objects are equal; {@code false} otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true; // Check if the objects are the same instance
		if (o == null || getClass() != o.getClass()) return false; // Ensure the object is of the same class
		
		Wings wings = (Wings) o; // Cast to Wings for comparison
		
		// Compare the WingType and count for equality
		return getWingType() == wings.getWingType() && // Compare WingType
				getCount() == wings.getCount(); // Compare count
	}
	
	/**
	 * Retrieves the specific type of wings for this object.
	 *
	 * @return the {@code WingType} of the wings
	 */
	public WingType getWingType() {
		return wingType;
	}
}
