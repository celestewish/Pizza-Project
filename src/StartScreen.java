import javax.swing.*;
import java.awt.*;

public class StartScreen extends CardScreen {
	private JPanel pnlStartScreen;

	private JButton btnLogin;
	private JButton btnSignIn;
	private JButton btnMenu;
	private JButton btnHome;
	private JButton btnDeals;
	private JButton btnLocations;


	public StartScreen(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer);
		
		
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


		btnLocations.addActionListener(_ -> showScreen("Locations"));

		btnDeals.addActionListener(_ -> {
            showScreen("Deals");
        });
	}
	
	public JPanel getScreenPanel() {
		return pnlStartScreen;
	}
}
