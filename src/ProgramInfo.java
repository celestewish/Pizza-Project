public class ProgramInfo {
	private Customer currentUser;
	private boolean isLoggedIn;
	private int loginAttempts;
	
	private final UserDatabase userDatabase;
	
	public ProgramInfo() {
		userDatabase = new UserDatabase();
		currentUser = null;
		isLoggedIn = false;
		loginAttempts = 0;
	}
	
	public Customer CurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(Customer customer) {currentUser = customer;}
	
	
	public boolean IsLoggedIn() {
		return isLoggedIn;
	}
	
	public void setLoggedIn(boolean isLoggedIn) {this.isLoggedIn = isLoggedIn;}
	
	
	public UserDatabase UserDatabase() {
		return userDatabase;
	}
}
