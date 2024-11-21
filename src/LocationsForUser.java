import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LocationsForUser class represents a screen displaying locations for logged-in users.
 * This screen provides navigation options, displays user-specific information, and ensures that
 * only logged-in users can access it.
 */
public class LocationsForUser extends CardScreen {
	private JPanel pnlLocationsUser;
	
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
	private JLabel ThankYou;
	private JLabel OrderPlace;
	private JLabel Total;
	private JButton btnCreateAccount;

	/**
	 * Constructor for the LocationsForUser screen.
	 *
	 * @param screenLayoutController The CardLayout controller for managing screen transitions.
	 * @param screenContainer        The container holding all screens.
	 * @param panelName              The unique name for this panel.
	 */
	public LocationsForUser(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlLocationsUser);
		info.registerScreenName(Screen.LOCATIONS_USER, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		//sets up the buttons seen at the top of the screen
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
		btnCreateAccount.addActionListener(_ -> showScreen(Screen.MENU));
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
	 * Redirects the user to the general locations screen if they are not logged in.
	 *
	 * @param toScreen The screen the user is attempting to navigate to.
	 * @return The general locations screen if not logged in; otherwise, the requested screen.
	 */
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		if (!info.isLoggedIn())
			return Screen.LOCATIONS;
		return toScreen;
	}

	/**
	 * Handles any setup or logic when the screen is entered.
	 * Sets up user-specific information like their name and cart total.
	 */
	@Override
	public void onEnterScreen() {
		setUpUserAndOrderInfo(lblHiName, lblCurTotal);
	
	}

	/**
	 * Initializes custom UI components, such as the cart logo and main logo panels.
	 */
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
