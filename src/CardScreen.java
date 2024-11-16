import javax.swing.*;
import java.awt.*;

public abstract class CardScreen {
	private final CardLayout screenLayoutController;
	private final JPanel screenContainer;
	private final ProgramInfo info;
	
	private static final char[] SPECIAL_CHARS = {
			'!', '#', '$', '^', '_', '~', ',', '.', '@', '[', ']', '`', '{', '}', '*', '+', '-', ':'
	};
	
	public CardScreen(CardLayout screenLayoutController, JPanel screenContainer) {
		this.screenLayoutController = screenLayoutController;
		this.screenContainer = screenContainer;
		this.info = info;
	}
	
	public void showScreen(String pnlName) {
		screenLayoutController.show(screenContainer, pnlName);
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
	
	public boolean isEmailTaken(JTextField t, String message, String title, int optionPaneType) {
		if (info.UserDatabase().customerExists(t.getText())) {
			JOptionPane.showMessageDialog(
					null,
					message,
					title,
					optionPaneType);
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
								email,
						"",
						1);
			}
			return false;
		}
		return true;
	}
	
	public boolean isTextEmpty (JTextField t, String message, String title, int optionPaneType) {
		if (t.getText().isBlank()) {
			JOptionPane.showMessageDialog(
					null,
					message,
					title,
					optionPaneType);
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
			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			} else if (Character.isDigit(c)) {
				hasNumber = true;
			} else if (isSpecialChar(c)) {
				hasSpecialChar = true;
			}
		}
		if (!hasUppercase || !hasNumber || !hasSpecialChar) {
			JOptionPane.showMessageDialog(null,
					"""
							Password must include at least one:
							-Uppercase Letter
							-Number
							-Special character (excluding \
							%, /, , &, <, >, ?, |, "
							""",
					"",
					JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return  false;
	}
	
	public boolean isPhoneInvalid(JTextField t, String message, String title, int optionPaneType) {
		String p = t.getText();
		if (p.length() != 10 || !p.matches("\\d+")) {
			JOptionPane.showMessageDialog(
					null,
					message,
					title,
					optionPaneType);
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
	
	public boolean isComboBoxUnselected (JComboBox t, String message, String title, int optionPaneType) {
		if (t.getSelectedItem() == "...") {
			JOptionPane.showMessageDialog(
					null,
					message,
					title,
					optionPaneType);
			return true;
		}
		return  false;
	}
}
