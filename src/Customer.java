import java.util.LinkedList;

public class Customer {
	private static int idCounter = 1000000;
	private final int userID;
	private String name;
	private String email;
	private String password;
	private String address;
	private final LinkedList<String> phoneNumbers = new LinkedList<>();
	private final LinkedList<Payment> savedPaymentMethods = new LinkedList<>();
	
	Customer(String name, String email, String password, String address, String phoneNumber) {
		userID = idCounter;
		idCounter++;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		phoneNumbers.addFirst(phoneNumber);
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		// if (newName.split(" ").length != 3)
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public LinkedList<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	
	public String getPhoneNumberAtIndex(int index) {
		return phoneNumbers.get(index);
	}
	
	public boolean addPhoneNumber(String phoneNumber) {
		if (phoneNumbers.contains(phoneNumber))
			return false;
		phoneNumbers.addLast(phoneNumber);
		return true;
	}
	
	public boolean removePhoneNumber(String phoneNumber) {
		if (phoneNumbers.contains(phoneNumber)) {
			phoneNumbers.remove(phoneNumber);
			return true;
		}
		else
			return false;
	}
}
