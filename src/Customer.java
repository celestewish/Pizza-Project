import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Customer {
	// Static counter for generating unique user IDs
	private static int idCounter;
	// Instance variables for customer details
	private final int userID;
	private String name;
	private String email;
	private String password;
	private String address;
	private final LinkedList<String> phoneNumbers = new LinkedList<>();
	private final LinkedList<Payment> savedPaymentMethods = new LinkedList<>();

	// Constructor to create a new customer with basic details
	Customer(String name, String email, String password, String address, String phoneNumber) {
		// Reads the customerRecords file to find the highest ID
		File file = new File("src/main/resources/customerRecords.txt");
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			int maxID = -1;

			// Iterates through the file to determine the next available ID
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				int id = Integer.parseInt(fields[0]);
				maxID = Math.max(maxID, id);
			}

			// Sets ID counter based on the highest ID found
			if (maxID == -1)
				idCounter = 1000000;
			else
				idCounter = maxID + 1;
		} catch (IOException e) {
			// Sets a default ID in case of an error
			idCounter = 1000000;
		}

		// Assigns customer ID and increments ID counter for next customer
		userID = idCounter;
		idCounter++;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		phoneNumbers.addFirst(phoneNumber);
	}

	// Constructor to create a customer object from database entry (with pre-existing userID)
	Customer(int userID, String name, String email, String password, String address, LinkedList<String> phoneNumbers) {
		this.userID = userID;
		idCounter++;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumbers.addAll(phoneNumbers);
	}

	// Getter for userID
	public int getUserID() {
		return userID;
	}

	// Getter for name
	public String getName() {
		return name;
	}

	// Setter for name
	public void setName(String name) {
		this.name = name;
	}

	// Getter for email
	public String getEmail() {
		return email;
	}

	// Setter for email
	public void setEmail(String email) {
		this.email = email;
	}

	// Compares provided password with stored password
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	// Setter for password
	public void setPassword(String password) {
		this.password = password;
	}

	// Getter for address
	public String getAddress() {
		return address;
	}

	// Setter for address
	public void setAddress(String address) {
		this.address = address;
	}

	// Getter for phone numbers list
	public LinkedList<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	// Gets a specific phone number by index
	public String getPhoneNumberAtIndex(int index) {
		return phoneNumbers.get(index);
	}

	// Adds a new phone number if it doesn't already exist
	public boolean addPhoneNumber(String phoneNumber) {
		if (phoneNumbers.contains(phoneNumber))
			return false;
		phoneNumbers.addLast(phoneNumber);
		return true;
	}

	// Removes a phone number from the list if it exists
	public boolean removePhoneNumber(String phoneNumber) {
		if (phoneNumbers.contains(phoneNumber)) {
			phoneNumbers.remove(phoneNumber);
			return true;
		}
		else
			return false;
	}

	// Returns a string representation of the customer (for saving to file)
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