import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ProgramInfo {
	private Customer currentUser;
	private boolean isLoggedIn;
	private int loginAttempts;
	private Order curOrder;
	private Pizza curPizza;
	private final UserDatabase userDatabase;
	private Screen curScreen;
	private Screen lastScreen;
	
	private static final Map<Screen, CardScreen> screenNames = new HashMap<>();
	
	public ProgramInfo() {
		userDatabase = new UserDatabase();
		currentUser = null;
		isLoggedIn = false;
		loginAttempts = 0;
		curOrder = null;
		curScreen = null;
		lastScreen = null;
	}
	
	public Map<Screen, CardScreen> Screens() {
		return screenNames;
	}
	
	public void registerScreenName(Screen screenName, CardScreen panel) {
		screenNames.put(screenName, panel);
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
	
	
	
	public Screen getCurScreen() {
		return curScreen;
	}
	
	public void setCurScreen(Screen screen) {
		curScreen = screen;
	}
	
	public void advanceScreen(Screen screen) {
		lastScreen = curScreen;
		curScreen = screen;
	}
	
	public Screen getLastScreen() {
		return lastScreen;
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
