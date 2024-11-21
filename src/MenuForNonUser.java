import javax.swing.*;
import java.awt.*;
/**
 * The MenuForNonUser class represents the menu available to nonUsers, It shows a list of menu items, allows navigation
 * to the login/create account screen.
 */

public class MenuForNonUser extends CardScreen {
	private JPanel pnlMenuNonUser;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnSignUp_SignIn;
	
	private JButton btnCreateAccount;
	private JPanel pnlLogo;

	/**
	 * Constructor for the CheckOut screen.
	 *
	 * @param screenLayoutController The CardLayout controller for switching screens.
	 * @param screenContainer        The container holding all screens.
	 * @param panelName              The unique name for this panel.
	 */
	
	public MenuForNonUser(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlMenuNonUser);
		info.registerScreenName(Screen.MENU_NON_USER, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
		
		btnCreateAccount.addActionListener(_ -> showScreen(Screen.SIGN_IN));
	}

	/**
	 * Clears the order details displayed when leaving the screen.
	 *
	 * @param destinationScreen The screen the user is navigating to.
	 * @return True to allow navigation, false otherwise.
	 */
	
	@Override
	public boolean onAttemptLeaveScreen(Screen destinationScreen) {
		return true;
	}

	/**
	 * Handles logic for entering this screen
	 *
	 * @return The screen to navigate to.
	 */
	
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		return toScreen;
	}

	/**
	 * Sets up the screen when entering it including order details, and fonts.
	 */
	
	@Override
	public void onEnterScreen() {
	
	}

	/**
	 * Initializes custom UI components, such as images for logos.
	 */
	
	private void createUIComponents() {
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
