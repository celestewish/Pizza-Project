public class ProgramInfo {
	private Customer currentUser;
	private boolean isLoggedIn;
	
	private final UserDatabase userDatabase;
	
	public ProgramInfo() {
		userDatabase = new UserDatabase();
		currentUser = null;
		isLoggedIn = false;
	}
	
	public Customer getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(Customer customer) {currentUser = customer;}
	
	
	public boolean IsLoggedIn() {
		return isLoggedIn;
	}
	
	public void setLoggedIn(boolean isLoggedIn) {this.isLoggedIn = isLoggedIn;}
	
	
	public UserDatabase getUserDatabase() {
		return userDatabase;
	}
}
