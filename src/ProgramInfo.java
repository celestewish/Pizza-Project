import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
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
	private boolean isAttemptingLogout;

	//fonts used for screens
	private final Font comboBoxFont = new Font("Times New Roman", Font.PLAIN, 24);
	private final Font checkBoxFont = new Font("Arial", Font.BOLD, 20);
	private final Font textFont = new Font("Times New Roman", Font.BOLD, 24);
	private final Font optionsFont = new Font("Arial", Font.PLAIN, 20);
	private final Font totalFont = new Font("Times New Roman", Font.PLAIN, 16);
	private final Font paymentFont = new Font("Times New Roman", Font.BOLD, 28);
	private final Font checkOutFont1 = new Font("Times New Roman", Font.BOLD, 28);
	private final Font checkOutFont2 = new Font("Times New Roman", Font.BOLD, 18);


	//general formatter, mostly for the total
	protected final DecimalFormat formatter;
	
	private static final Map<Screen, CardScreen> screenNames = new HashMap<>();

	//constructor for ProgramInfo
	public ProgramInfo() {
		userDatabase = new UserDatabase();
		currentUser = null;
		isLoggedIn = false;
		loginAttempts = 0;
		curOrder = null;
		curScreen = Screen.LOGIN;
		lastScreen = null;
		formatter = new DecimalFormat("#0.00");
		isAttemptingLogout = false;
	}


	public Map<Screen, CardScreen> Screens() {
		return screenNames;
	}
	
	public void registerScreenName(Screen screenName, CardScreen panel) {
		screenNames.put(screenName, panel);
	}

	//returns the current user
	public Customer CurrentUser() {
		return currentUser;
	}

	//sets the current order
	public void setCurrentUser(Customer customer) {currentUser = customer;}
	
	//returns the current order
	public Order getCurOrder() {
		if (curOrder == null) {
			setCurOrder(new Order(new LinkedList<>()));
		}
		return curOrder;
	}

	//sets the current order
	public void setCurOrder(Order order) {
		curOrder = order;
	}

	//clears the current order, used for when the order is paid for
	public void clearCurOrder(){ curOrder = null;}

	
	
	//returns the current pizza
	public Pizza getCurPizza() {
		return this.curPizza;
	}

	//sets the current pizza
	public void setCurPizza(Pizza inputPizza) {
		this.curPizza = inputPizza;
	}

	//adds the current pizza to the order
	public void addCurPizzaToOrder(int count) {
		this.curOrder.addItem(new MenuItemWithCount(this.curPizza, count));
	}



	//get/set for checking if the user is logged in
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public void setLoggedIn(boolean isLoggedIn) {this.isLoggedIn = isLoggedIn;}
	
	
	//returns the current userDataBase
	public UserDatabase UserDatabase() {
		return userDatabase;
	}
	
	
	//get/set for screens, as well as moving through the screen
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
	
	
	//get/set for login attempts
	public int getLoginAttempts() {
		return loginAttempts;
	}
	
	public void incrementLoginAttempts() {
		loginAttempts++;
	}
	
	public void resetLoginAttempts() {
		loginAttempts = 0;
	}

	//get/set for user information
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
	


	//get methods for the fonts used in various screens
	public Font getComboBoxFont() {
		return comboBoxFont;
	}
	
	public Font getCheckBoxFont() {
		return checkBoxFont;
	}
	
	public Font getTextFont() {
		return textFont;
	}
	
	public Font getOptionsFont() {
		return optionsFont;
	}

	public Font getTotalFont(){return totalFont;}

	public Font getPaymentFont(){return paymentFont;}

	public Font getCheckOutFont1(){return checkOutFont1;}
	public Font getCheckOutFont2(){return checkOutFont2;}
	
	public boolean isAttemptingLogout() {
		return isAttemptingLogout;
	}
	
	public void setAttemptingLogout(boolean attemptingLogout) {
		isAttemptingLogout = attemptingLogout;
	}
}
