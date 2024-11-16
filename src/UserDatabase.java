import java.io.*;

public class UserDatabase {
	static File file;
	
	public UserDatabase() {
		String storagePath = "resources/customerRecords.txt";
		file = new File(storagePath);
	}
	
	public boolean storeUser(Customer customer) {
		// Check if a customer with the proposed email already exists within the database
		if (customerExists(customer.getEmail()))
			return false; // Return false if an entry was found
		
		try {
			// Ensure the parent directory exists
			file.getParentFile().mkdirs();
			
			// Create a BufferedWriter in append mode
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			
			// Write customer information to the file
			writer.write(customer.toCSV());
			writer.newLine();
			
			// Close the BufferedWriter
			writer.close();
			// Return true if the customer info was inserted successfully
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Return false if the try/catch threw an error
		return false;
	}
	
	public boolean customerExists(String emailToCheck) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			
			// Read each line
			while ((line = reader.readLine()) != null) {
				// Split the line into fields
				String[] fields = line.split(",");
				// Check if the email matches (email is in the 3rd column, index 2)
				if (fields[2].replaceAll("\"", "").equalsIgnoreCase(emailToCheck)) {
					return true; // Email found
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false; // Email not found
	}
}
