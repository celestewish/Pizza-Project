import javax.swing.*;
import java.awt.*;
/**
 * The DealsForUser class represents a screen displaying deals available for logged-in users.
 * It allows users to add specific deals to their order and navigate to other parts of the application.
 */
public class DealsForUser extends CardScreen{
	private JPanel pnlDealsUser;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnSignOut;
	private JButton btnCart;
	private JLabel lblHiName;
	private JLabel lblCurTotal;
	private JPanel pnlCartLogo;
	private JPanel pnlLogo;
	private JButton btnOrdernow1;
	private JButton btnOrdernow2;

	/**
	 * Constructor for the DealsForUser screen.
	 *
	 * @param screenLayoutController The CardLayout controller for managing screen transitions.
	 * @param screenContainer        The container holding all screens.
	 * @param panelName              The unique name for this panel.
	 */
	public DealsForUser(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlDealsUser);
		info.registerScreenName(Screen.DEALS_USER, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);


		btnOrdernow1.addActionListener(_ -> {
			if (showConfirmationDialogueGreen("Please Confirm", "Add Deal to Order?")) {
				//(Large 1 topping pizza, 12 wings, small order of breadsticks) - $20
				Pizza newPizza = new Pizza(PizzaSize.LARGE, CrustType.THIN_CRUST, SauceOption.MARINARA);
				info.getCurOrder().addItem(new MenuItemWithCount(newPizza, 1));

				Side breadSticks = new Side(SideType.GARLIC_BREAD,2.50f,5);
				info.getCurOrder().addItem(new MenuItemWithCount(breadSticks, 1));

				Wings wings = new Wings(SideType.WINGS,5.50f,10, WingType.HOT_WINGS);
				info.getCurOrder().addItem(new MenuItemWithCount(wings, 1));

				showScreen(Screen.MENU);
			}
		});

		btnOrdernow2.addActionListener(_ -> {
			if (showConfirmationDialogueGreen("Please Confirm", "Add Deal to Order?")) {
				//(Large 2 topping pizza, large order of breadsticks, 2-Liter soda)$25.00
				Pizza newPizza = new Pizza(PizzaSize.LARGE, CrustType.THIN_CRUST, SauceOption.MARINARA);
				info.getCurOrder().addItem(new MenuItemWithCount(newPizza, 2));

				Side breadSticks = new Side(SideType.GARLIC_BREAD,2.50f,1);
				info.getCurOrder().addItem(new MenuItemWithCount(breadSticks, 1));

				Drink drink = new Drink(DrinkSize.LARGE,DrinkType.DR_PEPPER,2.50f);
				info.getCurOrder().addItem(new MenuItemWithCount(drink, 1));
				showScreen(Screen.MENU);
			}
		});
	}


	/**
	 * Allows the user to leave the current screen.
	 *
	 * @param destinationScreen The screen the user is navigating to.
	 * @return Always returns true to allow navigation.
	 */
	@Override
	public boolean onAttemptLeaveScreen(Screen destinationScreen) {
		return true;
	}

	/**
	 * Redirects the user to the generic deals screen if they are not logged in.
	 *
	 * @param toScreen The screen the user is attempting to navigate to.
	 * @return The generic deals screen if not logged in; otherwise, the requested screen.
	 */
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		if (!info.isLoggedIn())
			return Screen.DEALS;
		return toScreen;
	}

	/**
	 * Handles logic for setting up the screen when entering it.
	 * Displays user-specific information, such as their name and current order total.
	 */
	@Override
	public void onEnterScreen() {
		setUpUserAndOrderInfo(lblHiName, lblCurTotal);
	
	}

	/**
	 * Initializes custom UI components, such as the logo and cart panels.
	 */
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
