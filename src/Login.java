import javax.swing.*;
import java.awt.*;

/**
 * The Login class represents the Login screen in the application.
 * It allows for the user to enter a valid email and password, and allows navigation
 * to the menu for established users.
 */
public class Login extends CardScreen {
	private JPanel pnlLogin;
	private JPanel pnlLogo;
	
	private JButton btnSignUp_SignIn;
	private JButton btnMenu;
	private JButton btnHome;
	private JButton btnDeals;
	private JButton btnLocations;
	
	private JButton btnLogin;
	private JButton btnCreateAccount;
	
	/**
	 * Constructor for the Login screen.
	 *
	 * @param screenLayoutController The CardLayout controller for switching screens.
	 * @param screenContainer        The container holding all screens.
	 * @param panelName              The unique name for this panel.
	 */
	public Login(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlLogin);
		info.registerScreenName(Screen.LOGIN, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
		
		btnLogin.addActionListener(_ -> showScreen(Screen.SIGN_IN));
		
		btnCreateAccount.addActionListener(_ -> showScreen(Screen.SIGN_UP));
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
	 * @param toScreen The screen the user is navigating to.
	 * @return The screen to navigate to.
	 */
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		return toScreen;
	}

	/**
	 * Sets up the screen when entering it, including user info, order details, and fonts.
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
