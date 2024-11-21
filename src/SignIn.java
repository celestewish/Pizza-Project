import javax.swing.*;
import java.awt.*;
/**
 * The SignIn class represents the Sign in screen in the application.
 * It displays text fields for the user to enter valid credentials, and allows navigation
 * to the menu screen.
 */

public class SignIn extends CardScreen {
	private JPanel pnlSignIn;
	
	private JPanel pnlLogo;
	
	private JButton btnHome;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnReturn;
	private JButton btnMenu;
	private JButton btnSignUp_SignIn;
	
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	
	private JLabel lblEmailNotExist;
	private JCheckBox showPasswordCheckBox;
	
	private JButton btnValidateCredentials;
	private JButton btnSignUp;

	/**
	 * Constructor for the SignIn screen.
	 *
	 * @param screenLayoutController The CardLayout controller for switching screens.
	 * @param screenContainer        The container holding all screens.
	 * @param panelName              The unique name for this panel.
	 *
	 *      Sets up conditions for valid password and email credentials
	 */
	
	public SignIn(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlSignIn);
		lblEmailNotExist.setText("");
		info.registerScreenName(Screen.SIGN_IN, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		addJComponent(txtEmail);
		addJComponent(txtPassword);
		addJComponent(showPasswordCheckBox);
		
		setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
		
		btnReturn.addActionListener(_ -> showScreen(Screen.RETURN));
		
		btnSignUp.addActionListener(_ -> showScreen(Screen.SIGN_UP));
		
        btnValidateCredentials.addActionListener(_ -> {
			if (isTextEmpty(true, txtEmail))
				return;
			
			if (isEmailInvalid(txtEmail))
				return;
			
			if (!info.UserDatabase().customerExists(txtEmail.getText())) {
				lblEmailNotExist.setText("No account exists for this email. Please make an account.");
				return;
			}
			
			if (isTextEmpty(true, txtPassword)) {
				return;
			}
			
			if (!doesPasswordMatchEmail(txtEmail.getText(), convertPasswordToString(txtPassword.getPassword())))
				return;
			
			onSignIn(txtEmail.getText());
			showScreen(Screen.MENU);
		});

		showPasswordCheckBox.addActionListener(_ -> {
			if (showPasswordCheckBox.isSelected())
				txtPassword.setEchoChar((char)0);
			else
				txtPassword.setEchoChar('*');
		});
		
		// Listener to reset a warning label when the text is changed
		txtEmail.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				lblEmailNotExist.setText(""); // Clear the label
			}
			
			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				lblEmailNotExist.setText(""); // Clear the label
			}
			
			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				// No action needed for plain text fields
			}
		});
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
		if (info.getCurScreen() == Screen.SIGN_IN)
			return Screen.SIGN_UP;
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
