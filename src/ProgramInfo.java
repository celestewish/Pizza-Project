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
	
	private final Font comboBoxFont = new Font("Times New Roman", Font.PLAIN, 24);
	private final Font checkBoxFont = new Font("Arial", Font.BOLD, 20);
	private final Font textFont = new Font("Times New Roman", Font.BOLD, 24);
	private final Font optionsFont = new Font("Arial", Font.PLAIN, 20);
	private final Font totalFont = new Font("Times New Roman", Font.PLAIN, 16);
	private final Font paymentFont = new Font("Times New Roman", Font.BOLD, 28);

	
	protected final DecimalFormat formatter;
	
	private static final Map<Screen, CardScreen> screenNames = new HashMap<>();
	
	public ProgramInfo() {
		userDatabase = new UserDatabase();
		currentUser = null;
		isLoggedIn = false;
		loginAttempts = 0;
		curOrder = null;
		curScreen = Screen.LOGIN;
		lastScreen = null;
		formatter = new DecimalFormat("#0.00");
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
		if (curOrder == null)
			curOrder = new Order(new LinkedList<>());
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
	
	public void addCurPizzaToOrder(int count) {
		curOrder.addItem(new MenuItemWithCount(curPizza, count));
	}
	
	
	public boolean isLoggedIn() {
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


}
