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
	private JTextField textEmail;
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

		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("StartScreen");
			}
		});

		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("Menu");
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

		signInButton.addActionListener(new ActionListener() {

            @Override
			public void actionPerformed(ActionEvent e) {
				showScreen("MenuGUI");
			}
			if (isTextEmpty(textEmail, "Please enter an email","",0)) {
				return;
			}
		});

		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("SignUp");
			}
		});

		btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("StartScreen");
			}
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
}
