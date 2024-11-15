import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignIn extends CardScreen {
	private JPanel pnlSignIn;

	private JButton btnHome;

	private JButton btnReturn;

	private JButton btnMenu;
	private JTextField textEmail;
	private JTextField textPassword;
	private JCheckBox keepMeLoggedInCheckBox;
	private JButton signInSignUpButton;
	private JButton btnSignIn;
	private JButton signUpButton;

	public SignIn(CardLayout screenLayoutController, JPanel screenContainer) {
		super(screenLayoutController, screenContainer);
		btnReturn.addActionListener((ActionEvent) -> {
		
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
				showScreen("MenuScreen");
			}
		});
		signInSignUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("SignIn");
			}
		});
	}
	
	public JPanel getScreenPanel() {
		return pnlSignIn;
	}
}
