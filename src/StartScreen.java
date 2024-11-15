import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends CardScreen {
	private JPanel pnlStartScreen;

	private JButton btnLogin;
	private JButton btnSignIn;
	private JButton btnMenu;
	private JButton btnHome;

	public StartScreen(CardLayout screenLayoutController, JPanel screenContainer) {
		super(screenLayoutController, screenContainer);
		
		
		btnLogin.addActionListener((ActionEvent ) -> {
			showScreen("SignIn");
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
				showScreen("MenuGUI");
			}
		});
		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showScreen("SignIn");
			}
		});
	}
	
	public JPanel getScreenPanel() {
		return pnlStartScreen;
	}
}
