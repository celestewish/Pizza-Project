import java.io.*;
import java.util.LinkedList;

public class UserDatabase {
	private static File file;
	private final LinkedList<Customer> customers;
	
	public UserDatabase() {
		String storagePath = "src/main/resources/customerRecords.txt";
		file = new File(storagePath);
		customers = new LinkedList<>();
		loadUsers();
	}
	
	public void loadUsers() {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			
			// Read each line
			while ((line = reader.readLine()) != null) {
				// Split the line into fields
				String[] fields = line.split(",");
				LinkedList<String> phoneNumbers = new LinkedList<>();
				String[] phoneArray = fields[4].split(";");
				for (String phone : phoneArray) {
					phoneNumbers.add(phone.trim()); // Add each phone number after trimming whitespace
				}
				customers.add(new Customer(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], fields[4], phoneNumbers));
			}
			
			for (Customer customer : customers) {
				System.out.println(customer.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void storeUser(Customer customer) {
		// Check if a customer with the proposed email already exists within the database
		if (customerExists(customer.getEmail()))
			return; // Return if an entry was found, no duplicates allowed
		
		try {
			// Ensure the parent directory exists
			file.getParentFile().mkdirs();
			
			// Create a BufferedWriter in append mode
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			
			// Write customer information to the file
			writer.write(customer.toString());
			writer.newLine();
			
			// Close the BufferedWriter
			writer.close();
			
			// add the customer to the current LinkedList of users
			customers.add(customer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Customer getUser(String email) {
		for (Customer c : customers) {
			if (c.getEmail().equalsIgnoreCase(email))
				return c;
		}
		return null;
	}
	
	public boolean customerExists(String email) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			
			// Read each line
			while ((line = reader.readLine()) != null) {
				// Split the line into fields
				String[] fields = line.split(",");
				// Check if the email matches (email is in the 3rd column, index 2)
				if (fields[2].replaceAll("\"", "").equalsIgnoreCase(email)) {
					return true; // Email found
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false; // Email not found
	}
}
