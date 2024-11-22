import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Locations class represents a screen displaying the locations of the business.
 * This screen provides navigation options and displays appropriate content based on the user's login status.
 */
public class Locations extends CardScreen{
	private JPanel pnlLocations;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnSignUp_SignIn;
	private JPanel pnlLogo;
	private JLabel ThankYou;
	private JLabel OrderPlace;
	private JLabel Total;
	private JButton btnCreateAccount;

	/**
	 * Constructor for the Locations screen.
	 *
	 * @param screenLayoutController The CardLayout controller for managing screen transitions.
	 * @param screenContainer        The container holding all screens.
	 * @param panelName              The unique name for this panel.
	 */
	public Locations(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlLocations);
		info.registerScreenName(Screen.LOCATIONS, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());

		setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
		btnCreateAccount.addActionListener(_ -> showScreen(Screen.SIGN_UP));
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
	 * Redirects the user to the user-specific locations screen if they are logged in.
	 *
	 * @param toScreen The screen the user is attempting to navigate to.
	 * @return The user-specific locations screen if logged in; otherwise, the requested screen.
	 */
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		if (info.isLoggedIn())
			return Screen.LOCATIONS_USER;
		return toScreen;
	}

	/**
	 * Handles any setup or logic when the screen is entered.
	 */
	@Override
	public void onEnterScreen() {
	}

	/**
	 * Initializes custom UI components, such as the logo panel.
	 */
	private void createUIComponents() {
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
