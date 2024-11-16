import javax.swing.*;
import java.awt.*;

public class StartScreen extends CardScreen {
	private JPanel pnlStartScreen;

	private JButton btnLogin;
	private JButton btnSignIn;
	private JButton btnMenu;
	private JButton btnHome;

	public StartScreen(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);
		
		
		btnLogin.addActionListener(_ -> {
			showScreen("SignIn");
		});
		
		btnHome.addActionListener(_ -> {
			showScreen("StartScreen");
		});
		
		btnMenu.addActionListener(_ -> {
			showScreen("MenuGUI");
		});
		
		btnSignIn.addActionListener(_ -> {
			showScreen("SignIn");
		});
	}
	
	public JPanel getScreenPanel() {
		return pnlStartScreen;
	}
}
