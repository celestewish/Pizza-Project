import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Customer {
	private static int idCounter;
	private final int userID;
	private String name;
	private String email;
	private String password;
	private String address;
	private final LinkedList<String> phoneNumbers = new LinkedList<>();
	private final LinkedList<Payment> savedPaymentMethods = new LinkedList<>();
	
	Customer(String name, String email, String password, String address, String phoneNumber) {
		// Read file to determine next available ID
		File file = new File("src/main/resources/customerRecords.txt");
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			int maxID = -1;
			
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				int id = Integer.parseInt(fields[0]);
				maxID = Math.max(maxID, id);
			}
			
			if (maxID == -1)
				idCounter = 1000000;
			else
				idCounter = maxID + 1;
		} catch (IOException e) {
			// Handle exception or set default ID
			idCounter = 1000000;
		}
		
		userID = idCounter;
		idCounter++;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		phoneNumbers.addFirst(phoneNumber);
	}
	
	// constructor to create a new object with specified userID (used on startup to create customer objects for each entry in the database)
	Customer(int userID, String name, String email, String password, String address, LinkedList<String> phoneNumbers) {
		this.userID = userID;
		idCounter++;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumbers.addAll(phoneNumbers);
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
	
	@Override
	public String toString() {
		return userID + "," +
				name + "," +
				email + "," +
				password + "," +
				address + "," +
				String.join(";", phoneNumbers);
	}
}
