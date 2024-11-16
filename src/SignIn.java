import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SignIn extends CardScreen {
	private JPanel pnlSignIn;

	private JButton btnHome;

	private JButton btnReturn;

	private JButton btnMenu;
	private JButton btnSignIn;
	private JTextField textEmail;
	private JTextField textPassword;
	private JCheckBox keepMeLoggedInCheckBox;
	private JButton signInButton;
	private JButton signUpButton;
	private JButton btnDeals;
	private JButton btnLocations;


	public SignIn(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer,info);

		ArrayList<JTextField> requiredTextFields = new ArrayList<>();
		requiredTextFields.add(textEmail);
		requiredTextFields.add(textPassword);

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
			for (JTextField f : requiredTextFields){

			}
			for (JTextField f : requiredTextFields) {
				if (isTextEmpty(f, "Please complete all required fields", "", 0))
					return;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("MenuGUI");
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

	}
	
	public JPanel getScreenPanel() {
		return pnlSignIn;
	}
}
