import javax.swing.*;
import java.awt.*;

public class StartScreen extends CardScreen {
	private JPanel pnlStartScreen;
	
	private JButton btnLogin;
	private JButton btnSignIn;
	private JButton btnMenu;
	private JButton btnHome;
	
	public StartScreen(CardLayout screenLayoutController, JPanel screenContainer) {
		super(screenLayoutController, screenContainer);
		
		
		btnLogin.addActionListener(_ -> {
			showScreen("SignUp");
		});
	}
	
	public JPanel getScreenPanel() {
		return pnlStartScreen;
	}
}
