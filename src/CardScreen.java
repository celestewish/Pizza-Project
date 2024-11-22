import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
*   Card Screen is an abstract class that contains all info about a particular screen.
*   All methods defined here are actions that are performed by the screen
*/

public abstract class CardScreen {
	protected static final ProgramInfo info = new ProgramInfo();
	
	private final CardLayout screenLayoutController;
	private final JPanel screenContainer;
	private JPanel screenPanel;
	private final String panelName;
	private final ArrayList<JComponent> components;
	private final ArrayList<JLabel> totalCostFields;
	
	private static final char[] SPECIAL_CHARS = {
			'!', '#', '$', '^', '_', '~', ',', '.', '@', '[', ']', '`', '{', '}', '*', '+', '-', ':', '&'
	};

	/**
	 * Constructor for the CardScreen class.
	 *
	 * @param screenLayoutController the layout manager for the screen
	 * @param screenContainer the container holding the screen
	 * @param panelName the name of the panel for this screen
	 */
	public CardScreen(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		this.screenLayoutController = screenLayoutController;
		this.screenContainer = screenContainer;
		this.panelName = panelName;
		components = new ArrayList<>();
		totalCostFields = new ArrayList<>();
	}


	/**
	 * Abstract method that defines the behavior when attempting to leave the current screen.
	 *
	 * @param destinationScreen the screen the user is attempting to navigate to
	 * @return true if leaving the screen is allowed, false otherwise
	 */
	public abstract boolean onAttemptLeaveScreen(Screen destinationScreen);

	/**
	 * Abstract method that determines what should happen when attempting to enter a new screen.
	 *
	 * @param toScreen the screen the user is attempting to enter
	 * @return the resulting screen after attempting to enter
	 */
	public abstract Screen onAttemptEnterScreen(Screen toScreen);

	/**
	 * Abstract method for actions that occur when entering the screen.
	 */
	public abstract void onEnterScreen();

	/**
	 * Signs the user out of the application after confirming the action with the user.
	 *
	 * @return true if the user successfully signed out, false otherwise
	 */
	public boolean onAttemptSignOut() {
		if (showConfirmationDialogue(
				"Would you like to sign out?",
				"Yes, sign me out",
				"No, keep me signed in", "Sign out?")) {
			info.setAttemptingLogout(true);
			return true;
		}
		return false;
	}
	
	public void onSignOut() {
		info.setCurrentUser(null);
		info.setCurPizza(null);
		info.setCurOrder(null);
		info.setLoggedIn(false);
		showScreen(Screen.LOGIN);
	}

	/**
	 * Signs the user in by setting the user data in the program's information.
	 *
	 * @param email the email of the user to sign in
	 */
	public void onSignIn(String email) {
		info.setLoggedIn(true);
		info.setCurrentUser(info.UserDatabase().getUser(email));
	}


	/**
	 * Displays a specified screen by navigating through different logic and handling actions.
	 *
	 * @param screen the screen to display
	 */
	public void showScreen(Screen screen) {
		if (!onAttemptLeaveScreen(screen))
			return;
		
		if (screen == Screen.RETURN)
			screen = info.getLastScreen();
		
		if (screen == Screen.HOME)
			if (info.isLoggedIn())
				screen = Screen.MENU;
			else
				screen = Screen.LOGIN;
			
		screen = info.Screens().get(screen).onAttemptEnterScreen(screen);
		
		if (screen == null) {
			System.out.println("yoink");
			return;
		}
		
		resetScreen();
		Screen targetScreen = screen;
		SwingUtilities.invokeLater(() -> info.Screens().get(targetScreen).onEnterScreen());
		info.advanceScreen(screen);
		screenLayoutController.show(screenContainer, info.Screens().get((screen)).getPanelName());
	}


	/**
	 * Sets up the navigation bar for when the user is logged out.
	 *
	 * @param home the home button
	 * @param menu the menu button
	 * @param deals the deals button
	 * @param locations the locations button
	 * @param sign_up_sign_in the sign up/sign in button
	 */
	public void setUpNavBar_LoggedOut(JButton home, JButton menu, JButton deals, JButton locations, JButton sign_up_sign_in) {
		home.addActionListener(_ -> showScreen(Screen.HOME));
		menu.addActionListener(_ -> showScreen(Screen.MENU));
		deals.addActionListener(_ -> showScreen(Screen.DEALS));
		locations.addActionListener(_ -> showScreen(Screen.LOCATIONS));
		sign_up_sign_in.addActionListener(_ -> showScreen(Screen.SIGN_IN));
	}

	/**
	 * Sets up the navigation bar for when the user is logged in.
	 *
	 * @param home the home button
	 * @param menu the menu button
	 * @param deals the deals button
	 * @param locations the locations button
	 * @param sign_out the sign-out button
	 * @param cart the cart button
	 */
	public void setUpNavBar_LoggedIn(JButton home, JButton menu, JButton deals, JButton locations, JButton sign_out, JButton cart) {
		home.addActionListener(_ -> showScreen(Screen.HOME));
		menu.addActionListener(_ -> showScreen(Screen.MENU));
		deals.addActionListener(_ -> showScreen(Screen.DEALS));
		locations.addActionListener(_ -> showScreen(Screen.LOCATIONS));
		cart.addActionListener(_ -> showScreen(Screen.CART));
		sign_out.addActionListener(_ -> {
			if (!onAttemptSignOut())
				return;
			showScreen(Screen.LOGIN);
		});
	}

	/**
	 * Sets up user information such as the user's name and current order total.
	 *
	 * @param lblHiName the label to display the user's name
	 * @param lblCurTotal the label to display the current total cost
	 */
	public void setUpUserAndOrderInfo(JLabel lblHiName, JLabel lblCurTotal) {
		if (info.CurrentUser() != null)
			lblHiName.setText("Hi, " + info.CurrentUser().getName().split(" ")[0]);
		if (info.getCurOrder() != null)
			lblCurTotal.setText("Current Total: $" + info.formatter.format(info.getCurOrder().calcTotalOrderCost()));
	}

	/**
	 * Prepares and displays checkout information.
	 *
	 * @param lblCheckName the label displaying the customer's name
	 * @param lblCheckEmail the label displaying the customer's email
	 * @param lblCheckPhone the label displaying the customer's phone number
	 */
	public void setUpForCheckOut(JLabel lblCheckName, JLabel lblCheckEmail, JLabel lblCheckPhone){
		lblCheckName.setText(info.CurrentUser().getName());
		lblCheckEmail.setText(info.getEmail());
		lblCheckPhone.setText(info.getPhoneAtIndex0());
	}

	/**
	 * Prepares and displays payment information including total cost, tax, and customer address.
	 *
	 * @param txtAreaTotal the text area displaying the total cost information
	 * @param txtAreaCustAddress the text area displaying the customer's address
	 */
	public void setUpForPaymentInfo(JTextArea txtAreaTotal, JTextArea txtAreaCustAddress){
		txtAreaTotal.setText("Subtotal:\t$" + info.formatter.format(info.getCurOrder().calcTotalOrderCost()) +
		"\n\nTax:   \t$" + info.formatter.format(0.07*info.getCurOrder().calcTotalOrderCost()) + "\n\nTotal:   \t$" + info.formatter.format(1.07*info.getCurOrder().calcTotalOrderCost()));
		txtAreaCustAddress.setText(info.getAddress());
	}



	/**
	 * Gets the name of the panel associated with this screen.
	 *
	 * @return the name of the panel
	 */
	public String getPanelName() {
		return panelName;
	}

	/**
	 * Gets the JPanel object that represents the screen.
	 *
	 * @return the screen's JPanel
	 */
	public JPanel getScreenPanel() {
		return screenPanel;
	}

	/**
	 * Sets the JPanel for the screen.
	 *
	 * @param screenPanel the JPanel to set for this screen
	 */
	public void setScreenPanel(JPanel screenPanel) {
		this.screenPanel = screenPanel;
	}


	/**
	 * Resets the screen by clearing all user input and resetting the components.
	 */
	public void resetScreen() {
		info.resetLoginAttempts();
		
		for (JComponent c : components) {
			if (c instanceof JTextField) {
				((JTextField) c).setText(""); // Reset text for JTextField
			} else if (c instanceof JComboBox) {
				((JComboBox<?>) c).setSelectedIndex(0); // Reset selection for JComboBox
			} else if (c instanceof JCheckBox) {
				((JCheckBox) c).setSelected(false); // Reset selection for JCheckBox
			} else if (c instanceof JTextArea) {
				((JTextArea) c).setText("");
			} else if (c instanceof JLabel) {
				((JLabel) c).setText("");
			}
		}
	}


	/**
	 * Adds a JLabel to the list of total cost fields to be updated later.
	 *
	 * @param totalCostField the JLabel to add
	 */
	public void addTotalCostField(JLabel totalCostField) {
		totalCostFields.add(totalCostField);
	}

	/**
	 * Updates all total cost fields to reflect the current total order cost.
	 */
	public void updateTotalCostFields() {
		for (JLabel lbl : totalCostFields) {
			String[] curText = lbl.getText().split("\\$");
			String display = curText[0].trim() + " $" + info.formatter.format(info.getCurOrder().calcTotalOrderCost());
			lbl.setText(display);
		}
	}

	/**
	 *
	 * @param lbl passes any label that holds the subtotal
	 * @param item passes any MenuItem to add to the subtotal
	 */
	public void updateSubCostField(JLabel lbl, MenuItem item) {
		int costBreakdownIndex = -1;
		if (item instanceof Pizza) {
			costBreakdownIndex = 0;
		} else if (item instanceof Drink) {
			costBreakdownIndex = 1;
		} else if (item instanceof Side side) {
			switch (side.getType()) {
				case CAESAR_SALAD -> costBreakdownIndex = 2;
				case GARLIC_BREAD -> costBreakdownIndex = 3;
				case GARLIC_KNOTS -> costBreakdownIndex = 4;
				case WINGS -> costBreakdownIndex = 5;
			}
		} else if (item instanceof Dessert) {
			costBreakdownIndex = 6;
		}
		
		String[] curText = lbl.getText().split("\\$");
		String display = curText[0].trim() + " $" + info.formatter.format(info.getCurOrder().totalCostBreakDown()[costBreakdownIndex]);
		lbl.setText(display);
	}

	/**
	 *
	 * @param component passes a Java swing component to be edited
	 */
	public void addJComponent(JComponent component) {
		components.add(component);
	}
	
	public ArrayList<JComponent> getComponents() {
		return components;
	}
	
	public void setFontForJCompsOfAType(Font font, Class<? extends JComponent> componentType) {
		// Loop through the components list
		for (JComponent comp : components) {
			// Check if the component is an instance of the provided type
			if (componentType.isInstance(comp)) {
				// Set the font for the matching component
				comp.setFont(font);
			}
		}
	}

	/**
	 *
	 * @param title passes a string that is used for the popup
	 * @param message passes a string that is used for the popup
	 * @return returns true if yes is clicked
	 */
	public boolean showConfirmationDialogueGreen(String title, String message) {
		// Create a JPanel to hold the custom content
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		
		// Create a custom JLabel for the message with styling
		JLabel messageLabel = new JLabel(message);
		messageLabel.setFont(info.getTextFont());
		messageLabel.setForeground(Color.BLACK); // Set text color
		
		// Load or create a green checkmark icon
		Icon icon = UIManager.getIcon("OptionPane.questionIcon");
		
		// Add the icon and message to the panel
		JLabel iconLabel = new JLabel(icon);
		panel.add(iconLabel, BorderLayout.WEST);
		panel.add(messageLabel, BorderLayout.CENTER);
		
		// Display the confirmation dialog
		int choice = JOptionPane.showConfirmDialog(
				null,
				panel,
				title,
				JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE
		);
		
		// Return true if the user clicked "Yes" (proceed), false otherwise
		return choice == JOptionPane.YES_OPTION;
	}


	/**
	 *
	 * @param message passes a string that is used for the message of the pop up
	 * @param option1 used for the text of the left button
	 * @param defaultOption used for the text of the right button
	 * @param title used for the title of the popup
	 * @return returns true if user choose option1
	 */
	public static boolean showConfirmationDialogue(String message, String option1, String defaultOption, String title) {
		// Create a JPanel to hold custom content
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		
		// Create a JLabel for the message with custom font
		JLabel messageLabel = new JLabel(message);
		messageLabel.setFont(info.getTextFont()); // Set your desired font here
		messageLabel.setForeground(Color.BLACK); // Optional: Set text color
		
		// Add the message to the panel
		panel.add(messageLabel, BorderLayout.CENTER);
		
		// Define the options for the dialog
		String[] options = {option1, defaultOption};
		
		// Show the confirmation dialog
		int choice = JOptionPane.showOptionDialog(
				null,            // Parent component (null for center of the screen)
				panel,           // Custom panel with styled content
				title,           // Title of the dialog
				JOptionPane.DEFAULT_OPTION, // Option type
				JOptionPane.QUESTION_MESSAGE, // Message type with a question icon
				null,            // Icon (null for default icon)
				options,         // Options for buttons
				options[1]       // Default option
		);
		
		// Return true if the user chose option1, otherwise false
		return choice == 0;
	}

	/**
	 *
	 * @param message passes a string that is used for the popup
	 * @param buttonText passes a string that is used for the text of the button
	 * @param title passes a string that is used for the title of the popup
	 */
	public static void showInfoDialogue(String message, String buttonText, String title) {
		// Create a JPanel to hold custom content
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		
		// Create a JLabel for the message with custom font
		JLabel messageLabel = new JLabel(message);
		messageLabel.setFont(info.getTextFont()); // Set your desired font here
		messageLabel.setForeground(Color.BLACK); // Optional: Set text color
		
		// Add the message to the panel
		panel.add(messageLabel, BorderLayout.CENTER);
		
		// Show the info dialog with a single button
		JOptionPane.showOptionDialog(
				null,                // Parent component (null for center of the screen)
				panel,               // Custom panel with styled content
				title,               // Title of the dialog
				JOptionPane.DEFAULT_OPTION, // Only one option
				JOptionPane.INFORMATION_MESSAGE, // Message type with an info icon
				null,                // Icon (null for default info icon)
				new String[]{buttonText}, // Single button text
				buttonText           // Default button
		);
	}
	
	
	public static void showWarningDialogue(String message, String buttonText, String title) {
		// Create a JPanel to hold custom content
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		
		// Create a JLabel for the message with custom font and red color
		JLabel messageLabel = new JLabel(message);
		messageLabel.setFont(info.getTextFont()); // Set your desired font here
		messageLabel.setForeground(Color.RED);    // Set the text color to red
		
		// Add the message to the panel
		panel.add(messageLabel, BorderLayout.CENTER);
		
		// Show the warning dialog with a single button
		JOptionPane.showOptionDialog(
				null,                // Parent component (null for center of the screen)
				panel,               // Custom panel with styled content
				title,               // Title of the dialog
				JOptionPane.DEFAULT_OPTION, // Only one option
				JOptionPane.WARNING_MESSAGE, // Message type with a warning icon
				null,                // Icon (null for default warning icon)
				new String[]{buttonText}, // Single button text
				buttonText           // Default button
		);
	}
	
	
	
	/**
	 *
	 * @param password passes an array of chars, used to convert them to string
	 * @return passes the char array converted into a string
	 */
	public String convertPasswordToString (char[] password) {
		StringBuilder passwordString = new StringBuilder();
		for (char c : password)
			passwordString.append(c);
		return passwordString.toString();
	}


	/**
	 *
	 * @param t takes in the JTextField that contains the text
	 * @return returns true if the email already exists in the database, otherwise false
	 */
	public boolean isEmailTaken(JTextField t) {
		if (info.UserDatabase().customerExists(t.getText())) {
			JOptionPane.showMessageDialog(
					null,
					"There is already an account with this email",
					"",
					JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return  false;
	}

	/**
	 *
	 * @param email takes in a string
	 * @param password takes in a string
	 * @return returns true if the email exists and is correlated to the password, otherwise false
	 */
	public boolean doesPasswordMatchEmail(String email, String password) {
		if (!info.UserDatabase().customerExists(email)) {
			showWarningDialogue(
					"That email does not exist in our database...\nPlease sign up with the button below!",
					"Okay",
					"");
			return false;
		}
		if (!info.UserDatabase().getUser(email).checkPassword(password)) {
			int attemptsLeft = 3 - info.getLoginAttempts();
			showWarningDialogue(
					"Incorrect password. You have " + attemptsLeft + " attempts left",
					"Try Again",
					"Incorrect password.");
			info.incrementLoginAttempts();
			if (info.getLoginAttempts() >= 3) {
				showWarningDialogue(
						"You have surpassed the limit of 3 login attempts.\n" +
								"Sending a password reset link to the email:\n\t\t" +
								email.toLowerCase(),
						"Okay",
						"Password reset link sent to your email");
			}
			return false;
		}
		return true;
	}

	/**
	 *
	 * @param required takes in a boolean to show that the textfield is required
	 * @param t takes in a text field to get the text entered from the user
	 * @return returns true if the text field is not empty, otherwise false
	 */
	public boolean isTextEmpty (boolean required, JTextField t) {
		if (t.getText().isBlank()) {
			if (required) {
			
			}
			return true;
		}
		return  false;
	}
	
	private boolean isSpecialChar(char c) {
		for (char specialChar : SPECIAL_CHARS) {
			if (c == specialChar) {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param p takes in a password field
	 * @return returns true if the password is invalid
	 */
	public boolean isPasswordInvalid(JPasswordField p){
		if (p.getPassword().length < 8) {
			JOptionPane.showMessageDialog(
					null,
					"Password must be at least 8 characters long",
					"",
					JOptionPane.ERROR_MESSAGE);
			return true;
		}
		
		boolean hasUppercase = false;
		boolean hasNumber = false;
		boolean hasSpecialChar = false;
		
		char[] password = p.getPassword();
		
		for (char c : password) {
			if (Character.isUpperCase(c))
				hasUppercase = true;
			else if (Character.isDigit(c))
				hasNumber = true;
			else if (isSpecialChar(c))
				hasSpecialChar = true;
		}
		
		if (!hasUppercase || !hasNumber || !hasSpecialChar) {
			JOptionPane.showMessageDialog(null,
					"""
							Password must include at least one:
							-Uppercase Letter
							-Number
							-Special character (excluding \
							%, /, , &, <, >, ?, |, and ")
							""",
					"",
					JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return  false;
	}

	/**
	 *
	 * @param t takes in a text field to grab the user inputs
	 * @return returns true if the phone is invalid, otherwise false
	 */
	public boolean isPhoneInvalid(JTextField t) {
		String p = t.getText();
		if (p.length() != 10 || !p.matches("\\d+")) {
			JOptionPane.showMessageDialog(
					null,
					"Please enter a valid phone number",
					"",
					JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param t takes in a text field to grab the user's inputs
	 * @return returns true if the email is invalid, otherwise false
	 */
	public boolean isEmailInvalid(JTextField t) {
		String email = t.getText().trim();

		// Check if the email contains exactly one '@'
		String[] parts = email.split("@");
		if (parts.length != 2) {
			showWarningDialogue(
					"Please enter a valid email.",
					"Okay",
					"Email Invalid");
			return true;
		}
		
		// Check the local part (before '@')
		String localPart = parts[0];
		if (localPart.isEmpty()) {
			showWarningDialogue(
					"Please enter a valid email.",
					"Okay",
					"Email Invalid");
			return true;
		}
		
		// Check the domain part (after '@')
		String domainPart = parts[1];
		String[] domainParts = domainPart.split("\\.");
		if (domainParts.length < 2) {
			showWarningDialogue(
					"Please enter a valid email.",
					"Okay",
					"Email Invalid");
			return true;
		}
		
		// Ensure all domain parts are non-empty
		for (String part : domainParts) {
			if (part.isEmpty()) {
				showWarningDialogue(
						"Please enter a valid email.",
						"Okay",
						"Email Invalid");
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param required takes in a boolean
	 * @param t takes in a combo box
	 * @return returns true of the combo box is selected, otherwise false
	 */
	public boolean isComboBoxUnselected (boolean required, JComboBox<?> t) {
		if (t.getSelectedIndex() == 0) {
			if (required) {
				showInfoDialogue(
						"Please complete all required fields!",
						"Okay",
						"Required fields unfilled");
			}
			return true;
		}
		return  false;
	}
}
