import javax.swing.*;
import java.awt.*;

public class SignIn extends CardScreen {
	private JPanel pnlSignIn;

	private JButton btnHome;

	private JButton btnReturn;

	private JButton btnMenu;
	private JButton btnSignUp;
	private JTextField textEmail;
	private JTextField textPassword;
	private JCheckBox keepMeLoggedInCheckBox;
	private JButton signInButton;

	public SignIn(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);
		
		btnHome.addActionListener(_ -> showScreen("StartScreen"));
		btnSignUp.addActionListener(_ -> showScreen("SignUp"));
	}
	
	public JPanel getScreenPanel() {
		return pnlSignIn;
	}
}
