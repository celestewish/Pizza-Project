import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignIn extends CardScreen {
	private JPanel pnlSignIn;
	
	private JButton btnMenu;
	private JButton btnSignIn_SignUp;
	private JButton btnHome;
	
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	
	private JCheckBox checkStaySignedIn;
	private JButton btnSignIn;
	private JButton btnSignUp;
	private JButton btnReturn;
	
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
	}
	
	public JPanel getScreenPanel() {
		return pnlSignIn;
	}
}
