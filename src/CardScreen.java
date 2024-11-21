import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
	
	public CardScreen(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		this.screenLayoutController = screenLayoutController;
		this.screenContainer = screenContainer;
		this.panelName = panelName;
		components = new ArrayList<>();
		totalCostFields = new ArrayList<>();
	}
	
	
	
	public abstract boolean onAttemptLeaveScreen(Screen destinationScreen);
	
	public abstract Screen onAttemptEnterScreen(Screen toScreen);
	
	public abstract void onEnterScreen();
	
	public boolean onSignOut(ProgramInfo info) {
		if (showConfirmationDialogue(
				"Would you like to sign out?",
				"Yes, sign me out",
				"No, keep me signed in", "Sign out?")) {
			info.setCurrentUser(null);
			info.setCurPizza(null);
			info.setCurOrder(null);
			info.setLoggedIn(false);
			return true;
		}
		return false;
	}
	
	public void onSignIn(String email) {
		info.setLoggedIn(true);
		info.setCurrentUser(info.UserDatabase().getUser(email));
	}
	

	
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
		
		resetScreen();
		Screen targetScreen = screen;
		SwingUtilities.invokeLater(() -> info.Screens().get(targetScreen).onEnterScreen());
		info.advanceScreen(screen);
		screenLayoutController.show(screenContainer, info.Screens().get((screen)).getPanelName());
	}
	
	
	public void setUpNavBar_LoggedOut(JButton home, JButton menu, JButton deals, JButton locations, JButton sign_up_sign_in) {
		home.addActionListener(_ -> showScreen(Screen.HOME));
		menu.addActionListener(_ -> showScreen(Screen.MENU));
		deals.addActionListener(_ -> showScreen(Screen.DEALS));
		locations.addActionListener(_ -> showScreen(Screen.LOCATIONS));
		sign_up_sign_in.addActionListener(_ -> showScreen(Screen.SIGN_IN));
	}
	
	public void setUpNavBar_LoggedIn(JButton home, JButton menu, JButton deals, JButton locations, JButton sign_out, JButton cart) {
		home.addActionListener(_ -> showScreen(Screen.HOME));
		menu.addActionListener(_ -> showScreen(Screen.MENU));
		deals.addActionListener(_ -> showScreen(Screen.DEALS));
		locations.addActionListener(_ -> showScreen(Screen.LOCATIONS));
		cart.addActionListener(_ -> showScreen(Screen.CART));
		sign_out.addActionListener(_ -> {
			if (!onSignOut(info))
				return;
			showScreen(Screen.LOGIN);
		});
	}
	
	public void setUpUserAndOrderInfo(JLabel lblHiName, JLabel lblCurTotal) {
		if (info.CurrentUser() != null)
			lblHiName.setText("Hi, " + info.CurrentUser().getName().split(" ")[0]);
		if (info.getCurOrder() != null)
			lblCurTotal.setText("Current Total: $" + info.formatter.format(info.getCurOrder().calcTotalOrderCost()));
	}

	public void setUpForCheckOut(JLabel lblCheckName, JLabel lblCheckEmail, JLabel lblCheckPhone){
		lblCheckName.setText(info.CurrentUser().getName());
		lblCheckEmail.setText(info.getEmail());
		lblCheckPhone.setText(info.getPhoneAtIndex0());
	}

	public void setUpForPaymentInfo(JTextArea txtAreaTotal, JTextArea txtAreaCustAddress){
		txtAreaTotal.setText("Subtotal : $" + info.formatter.format(info.getCurOrder().calcTotalOrderCost()) +
		"\n\nTax: $" + info.formatter.format(0.07*info.getCurOrder().calcTotalOrderCost()) + "\n\nTotal :$" + info.formatter.format(1.07*info.getCurOrder().calcTotalOrderCost()));
		txtAreaCustAddress.setText(info.getAddress());
	}


	
	
	public String getPanelName() {
		return panelName;
	}
	
	public JPanel getScreenPanel() {
		return screenPanel;
	}
	
	public void setScreenPanel(JPanel screenPanel) {
		this.screenPanel = screenPanel;
	}
	
	
	
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
	
	
	
	public void addTotalCostField(JLabel totalCostField) {
		totalCostFields.add(totalCostField);
	}
	
	public void updateTotalCostFields() {
		for (JLabel lbl : totalCostFields) {
			String[] curText = lbl.getText().split("\\$");
			String display = curText[0].trim() + " $" + info.formatter.format(info.getCurOrder().calcTotalOrderCost());
			lbl.setText(display);
		}
	}
	
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



	
	public void showPopUpWindow(String message, String title, int optionPaneType) {
		JOptionPane.showMessageDialog(
				null,
				message,
				title,
				optionPaneType
		);
	}
	
	
	
	public String convertPasswordToString (char[] password) {
		StringBuilder passwordString = new StringBuilder();
		for (char c : password)
			passwordString.append(c);
		return passwordString.toString();
	}
	
	
	
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
	
	public boolean doesPasswordMatchEmail(String email, String password) {
		if (!info.UserDatabase().customerExists(email)) {
			showPopUpWindow(
					"That email does not exist in our database...\nPlease sign up with the button below!",
					"",
					1);
			return false;
		}
		if (!info.UserDatabase().getUser(email).checkPassword(password)) {
			showPopUpWindow(
					"Incorrect password.",
					"",
					0);
			info.incrementLoginAttempts();
			if (info.getLoginAttempts() >= 3) {
				showPopUpWindow(
						"You have surpassed the limit of 3 login attempts.\n" +
								"Sending a password reset link to the email:\n\t\t" +
								email.toLowerCase(),
						"",
						1);
			}
			return false;
		}
		return true;
	}
	
	public boolean isTextEmpty (boolean required, JTextField t) {
		if (t.getText().isBlank()) {
			if (required) {
				JOptionPane.showMessageDialog(
						null,
						"Please complete all required fields",
						"",
						JOptionPane.ERROR_MESSAGE);
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
	
	public boolean isEmailInvalid(JTextField t) {
		String email = t.getText().trim();
		
		// Check if the email contains exactly one '@'
		String[] parts = email.split("@");
		if (parts.length != 2) {
			showPopUpWindow(
					"Please enter a valid email.",
					"",
					0);
			return true; // Invalid: No '@' or multiple '@'
		}
		
		// Check the local part (before '@')
		String localPart = parts[0];
		if (localPart.isEmpty()) {
			showPopUpWindow(
					"Please enter a valid email.",
					"",
					0);
			return true; // Invalid: Local part is empty
		}
		
		// Check the domain part (after '@')
		String domainPart = parts[1];
		String[] domainParts = domainPart.split("\\.");
		if (domainParts.length < 2) {
			showPopUpWindow(
					"Please enter a valid email.",
					"",
					0);
			return true; // Invalid: No '.' in domain
		}
		
		// Ensure all domain parts are non-empty
		for (String part : domainParts) {
			if (part.isEmpty()) {
				showPopUpWindow(
						"Please enter a valid email.",
						"",
						0);
				return true; // Invalid: Empty domain part
			}
		}
		
		// If all checks pass, the email is valid
		return false;
	}
	
	public boolean isComboBoxUnselected (boolean required, JComboBox<?> t) {
		if (t.getSelectedIndex() == 0) {
			if (required) {
				JOptionPane.showMessageDialog(
						null,
						"Please complete all required fields",
						"title",
						JOptionPane.ERROR_MESSAGE);
			}
			return true;
		}
		return  false;
	}
}
