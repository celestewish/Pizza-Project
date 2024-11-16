import javax.swing.*;
import java.awt.*;

public abstract class CardScreen {
	private final CardLayout screenLayoutController;
	private final JPanel screenContainer;
	private final ProgramInfo info;
	
	private static final char[] SPECIAL_CHARS = {
			'!', '#', '$', '^', '_', '~', ',', '.', '@', '[', ']', '`', '{', '}', '*', '+', '-', ':'
	};
	
	public CardScreen(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		this.screenLayoutController = screenLayoutController;
		this.screenContainer = screenContainer;
		this.info = info;
	}
	
	public void showScreen(String pnlName) {
		screenLayoutController.show(screenContainer, pnlName);
	}
	
	public boolean isEmailTaken(String email, String message, String title, int optionPaneType) {
		if (info.UserDatabase().customerExists(email)) {
			JOptionPane.showMessageDialog(
					null,
					message,
					title,
					optionPaneType);
			return true;
		}
		return  false;
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
					"Password must include at least one:\n" +
							"-Uppercase Letter\n" +
							"-Number\n" +
							"-Special character (excluding " +
							"!, #, $, ^, _, ~, ,, ., @, [, ], `, {, }, *, +, -, :)",
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
