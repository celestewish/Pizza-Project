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
		});

		btnHome.addActionListener(_ -> showScreen("StartScreen"));

		btnDeals.addActionListener(_ -> showScreen("Deals"));

		btnMenu.addActionListener(_ -> showScreen("MenuGUI"));

		btnLocations.addActionListener(_ -> showScreen("Locations"));

		btnSignIn_SignUp.addActionListener(_ -> showScreen("SignUp"));

		keepMeLoggedInCheckBox.addActionListener(_ -> {

		});

        btnSignIn_ValidateCredentials.addActionListener(_ -> {
			if (isTextEmpty(txtEmail, "Please enter an email.", "", 0)) {
				return;
			}
			if (isTextEmpty(txtPassword, "Please enter a password.", "", 0)) {
				return;
			}
			showScreen("MenuGUI");
				});

		btnSignUp.addActionListener(e -> showScreen("SignUp"));

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
}
