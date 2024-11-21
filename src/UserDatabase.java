import java.io.*;
import java.util.LinkedList;

/**
 * Manages the storage and retrieval of customer information in a file-based database.
 * This class supports loading existing customers from a file, storing new customers,
 * and checking if a customer already exists based on their email.
 */
public class UserDatabase {
	private static File file;
	private final LinkedList<Customer> customers;
	
	/**
	 * Constructs a UserDatabase instance, initializing the file location and loading existing customers.
	 */
	public UserDatabase() {
		String storagePath = "src/main/resources/customerRecords.txt";
		file = new File(storagePath);
		customers = new LinkedList<>();
		loadUsers();  // Loads users from the file
	}
	
	/**
	 * Loads the customers from the file into the `customers` LinkedList.
	 * Each line in the file represents a customer record, and the method parses these records
	 * to create Customer objects, which are then added to the list.
	 */
	public void loadUsers() {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			
			// Read each line and parse customer information
			while ((line = reader.readLine()) != null) {
				// Split the line into fields (CSV format)
				String[] fields = line.split(",");
				LinkedList<String> phoneNumbers = new LinkedList<>();
				String[] phoneArray = fields[4].split(";");
				for (String phone : phoneArray) {
					phoneNumbers.add(phone.trim());  // Add each phone number after trimming whitespace
				}
				// Create a new Customer object and add it to the list
				customers.add(new Customer(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], fields[4], phoneNumbers));
			}
			
			// Print all customers loaded (for debugging or informational purposes)
			for (Customer customer : customers) {
				System.out.println(customer.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();  // Handle any IO exceptions
		}
	}
	
	/**
	 * Stores a new customer in the database by appending their information to the customer records file.
	 * If a customer with the same email already exists, the new customer is not stored.
	 *
	 * @param customer the Customer object to be stored
	 */
	public void storeUser(Customer customer) {
		// Check if a customer with the proposed email already exists within the database
		if (customerExists(customer.getEmail()))
			return;  // Return if an entry was found, no duplicates allowed
		
		try {
			// Ensure the parent directory exists for the file
			file.getParentFile().mkdirs();
			
			// Create a BufferedWriter in append mode to add the new customer record
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			
			// Write the customer's information to the file
			writer.write(customer.toString());
			writer.newLine();  // Add a newline after each customer record
			
			// Close the BufferedWriter
			writer.close();
			
			// Add the customer to the current LinkedList of users
			customers.add(customer);
		} catch (IOException e) {
			e.printStackTrace();  // Handle any IO exceptions
		}
	}
	
	/**
	 * Retrieves a customer by their email address.
	 *
	 * @param email the email address of the customer to be retrieved
	 * @return the Customer object if found, otherwise null
	 */
	public Customer getUser(String email) {
		for (Customer c : customers) {
			if (c.getEmail().equalsIgnoreCase(email))
				return c;  // Return the customer if their email matches
		}
		return null;  // Return null if no matching customer is found
	}
	
	/**
	 * Checks if a customer with a given email already exists in the database.
	 *
	 * @param email the email address of the customer to check
	 * @return true if the customer exists, false otherwise
	 */
	public boolean customerExists(String email) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				if (fields[2].replaceAll("\"", "").equalsIgnoreCase(email)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
