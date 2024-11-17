public class ProgramInfo {
	private Customer currentUser;
	private boolean isLoggedIn;
	private int loginAttempts;
	private Order curOrder;
	private Pizza curPizza;
	
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
	
	public void setCurrentUser(Customer customer) {currentUser = customer;}
	
	
	public Order getCurOrder() {
		return curOrder;
	}
	
	public void setCurOrder(Order order) {
		curOrder = order;
	}
	
	
	
	public Pizza getCurPizza() {
		return curPizza;
	}
	
	public void setCurPizza(Pizza curPizza) {
		this.curPizza = curPizza;
	}
	
	
	
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
