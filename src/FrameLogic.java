import javax.swing.*;
import java.awt.*;

public class FrameLogic extends JFrame{
	private JPanel screenContainer;
	private JPanel login;
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnSignIn;
	private JButton btnLogin;
	private JPanel sign_in;
	private JTextField emailField;
	private JLabel email;
	private JLabel password;
	private JPasswordField passwordField;
	private JCheckBox checkStaySignedIn;
	private JButton btnAcceptCredentials;
	private JLabel txtlabel;
	private JButton signUpButton;
	private JButton btnReturn;
	
	public FrameLogic() {
		setContentPane(screenContainer);
		setTitle("Mom and Pop's Shop");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		screenContainer.setLayout(new CardLayout());
		screenContainer.add(login, "Login");
		screenContainer.add(sign_in, "SignIn");
		
		btnLogin.addActionListener(e -> {
			CardLayout layout = (CardLayout) screenContainer.getLayout();
			layout.show(screenContainer, "SignIn");
		});
		btnReturn.addActionListener(e -> {
			CardLayout layout = (CardLayout) screenContainer.getLayout();
			layout.show(screenContainer, "Login");
		});
	}
}
