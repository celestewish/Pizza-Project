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
	
	public abstract boolean onAttemptLeaveScreen();
	
	public abstract Screen onAttemptEnterScreen(Screen toScreen);
	
	public abstract void onEnterScreen();
	
	public boolean onSignOut(ProgramInfo info) {
		if (showConfirmationDialog(
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
	
	public void updateTotalCostFields() {
		for (JLabel lbl : totalCostFields) {
			String[] curText = lbl.getText().split("\\$");
			String display = curText[0] + " $" + info.formatter.format(info.getCurOrder().calcTotalOrderCost());
			lbl.setText(display);
		}
	}
	
	public void showScreen(Screen screen) {
		if (!onAttemptLeaveScreen())
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
		info.Screens().get(screen).onEnterScreen();
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
			}
		}
	}
	
	public void addTotalCostField(JLabel totalCostField) {
		totalCostFields.add(totalCostField);
	}
	
	public void addJComponent(JComponent component) {
		components.add(component);
	}
	
	public ArrayList<JComponent> getComponents() {
		return components;
	}
	
	public static boolean showConfirmationDialog(String message, String option1, String defaultOption, String title) {
		// Define the options for the dialog
		String[] options = {option1, defaultOption};
		
		// Show the confirmation dialog
		int choice = JOptionPane.showOptionDialog(
				null, // Parent component (null for center of the screen)
				message,
				title,
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1] // Default option
		);
		
		// return true if the user chose option1, otherwise false
		return choice == 0;
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
