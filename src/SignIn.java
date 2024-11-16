import javax.swing.*;
import java.awt.*;

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
	
	
	public SignIn(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer,info);

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
