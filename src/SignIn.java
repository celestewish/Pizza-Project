import javax.swing.*;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

public class SignIn extends CardScreen {
	private JPanel pnlSignIn;

	private JButton btnHome;

	private JButton btnReturn;

	private JButton btnMenu;
	private JButton btnSignIn_SignUp;
	private JTextField txtEmail;
	private JCheckBox keepMeLoggedInCheckBox;
	private JButton btnSignIn_ValidateCredentials;
	private JButton btnSignUp;
	private JButton btnDeals;
	private JButton btnLocations;
	private JCheckBox showPasswordCheckBox;
	private JPasswordField txtPassword;
	private JLabel lblEmailNotExist;
	
	
	public SignIn(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer,info);
		lblEmailNotExist.setText("");
		
		btnReturn.addActionListener((_) -> {
			showScreen("StartScreen");
			resetScreen();
			info.resetLoginAttempts();
		});

		btnHome.addActionListener(_ -> {
			showScreen("StartScreen");
			resetScreen();
			info.resetLoginAttempts();
		});

		btnDeals.addActionListener(_ -> {
			showScreen("Deals");
			resetScreen();
			info.resetLoginAttempts();
		});

		btnMenu.addActionListener(_ -> {
			showScreen("MenuGUI");
			resetScreen();
			info.resetLoginAttempts();
		});

		btnLocations.addActionListener(_ -> {
			showScreen("Locations");
			resetScreen();
			info.resetLoginAttempts();
		});

		btnSignIn_SignUp.addActionListener(_ -> {
			showScreen("SignUp");
			resetScreen();
			info.resetLoginAttempts();
		});

		keepMeLoggedInCheckBox.addActionListener(_ -> {
		
		});
		
        btnSignIn_ValidateCredentials.addActionListener(_ -> {
			if (isTextEmpty(txtEmail, "Please enter an email.", "", 0)) {
				return;
			}
			
			if (isEmailInvalid(txtEmail))
				return;
			
			if (!info.UserDatabase().customerExists(txtEmail.getText())) {
				lblEmailNotExist.setText("! No account exists for this email");
				return;
			}
			if (isTextEmpty(txtPassword, "Please enter a password.", "", 0)) {
				return;
			}
			if (!doesPasswordMatchEmail(txtEmail.getText(), convertPasswordToString(txtPassword.getPassword())))
				return;
			
			info.setCurrentUser(info.UserDatabase().getUser(txtEmail.getText()));
			info.setLoggedIn(true);
	        info.resetLoginAttempts();
			showScreen("MenuGUI");
		});

		btnSignUp.addActionListener(_ -> {
			showScreen("SignUp");
			resetScreen();
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
	
	public JPanel getScreenPanel() {
		return pnlSignIn;
	}
	
	public void resetScreen() {
		txtEmail.setText("");
		txtPassword.setText("");
		keepMeLoggedInCheckBox.setSelected(false);
		showPasswordCheckBox.setSelected(false);
	}
}
