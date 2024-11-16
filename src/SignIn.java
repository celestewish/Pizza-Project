import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignIn extends CardScreen {
	private JPanel pnlSignIn;

	private JButton btnHome;

	private JButton btnReturn;

	private JButton btnMenu;
	private JButton btnSignIn;
	private JTextField txtEmail;
	private JCheckBox keepMeLoggedInCheckBox;
	private JButton signInButton;
	private JButton signUpButton;
	private JButton btnDeals;
	private JButton btnLocations;
	private JCheckBox showPasswordCheckBox;
	private JPasswordField txtPassword;


	public SignIn(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer,info);

		btnReturn.addActionListener((ActionEvent) -> {
		showScreen("StartScreen");
		});

		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("StartScreen");
			}
		});

		btnDeals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("Deals");
			}
		});

		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("MenuGUI");
			}
		});

		btnLocations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("Locations");
			}
		});

		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("SignIn");
			}
		});

		keepMeLoggedInCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

        signInButton.addActionListener(e -> {
			if (isTextEmpty(txtEmail, "Please enter an email.", "", 0)) {
				return;
			}
			if (isTextEmpty(txtPassword, "Please enter a password.", "", 0)) {
				return;
			}
			showScreen("MenuGUI");
				});

		signUpButton.addActionListener(e -> showScreen("SignUp"));

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
