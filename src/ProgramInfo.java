public class ProgramInfo {
	private Customer currentUser;
	private boolean isLoggedIn;
	private int loginAttempts;
	private Order curOrder;
	
	private final UserDatabase userDatabase;
	
	public ProgramInfo() {
		userDatabase = new UserDatabase();
		currentUser = null;
		isLoggedIn = false;
		loginAttempts = 0;
		curOrder = null;
	}
	
	public Customer CurrentUser() {
		return currentUser;
	}
	
	public Order CurOrder() {
		return curOrder;
	}
	
	public void setCurrentUser(Customer customer) {currentUser = customer;}
	
	
	public boolean IsLoggedIn() {
		return isLoggedIn;
	}
	
	public void setLoggedIn(boolean isLoggedIn) {this.isLoggedIn = isLoggedIn;}
	
	
	public UserDatabase UserDatabase() {
		return userDatabase;
	}
	
	
	public int getLoginAttempts() {
		return loginAttempts;
	}
	
	public void incrementLoginAttempts() {
		loginAttempts++;
	}
	
	public void resetLoginAttempts() {
		loginAttempts = 0;
	}

	public String getName(){
		return currentUser.getName();
	}
	
	public String getPhoneAtIndex0(){
		return currentUser.getPhoneNumberAtIndex(0);
	}
	
	public String getEmail(){
		return currentUser.getEmail();
	}
	
	public void setName(String name){
		currentUser.setName(name);
	}
	
	public boolean addPhone(String phone){
		return currentUser.addPhoneNumber(phone);
	}
	
	public void setEmail(String mail){
		currentUser.setEmail(mail);
	}
	public String getAddress(){
		return currentUser.getAddress();
	}
}
