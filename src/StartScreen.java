import javax.swing.*;
import java.awt.*;


public class StartScreen extends CardScreen {
	private JPanel pnlStartScreen;

	//buttons
	private JButton btnLogin;
	private JButton btnSignIn;
	private JButton btnMenu;
	private JButton btnHome;
	private JButton btnDeals;
	private JButton btnLocations;

	//constructor for start screen
	public StartScreen(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);
		
		//action listeners for the buttons
		btnLogin.addActionListener(_ -> {
			showScreen("SignIn");
		});
		
		btnHome.addActionListener(_ -> {
			showScreen("StartScreen");
		});
		
		btnMenu.addActionListener(_ -> {
			showScreen("Menu");
		});
		
		btnSignIn.addActionListener(_ -> {
			showScreen("SignIn");
		});
		
		btnLocations.addActionListener(_ -> {
			showScreen("LocationScreen");
		});

		btnDeals.addActionListener(_ -> {
            showScreen("Deals");
        });



	}
	
	public JPanel getScreenPanel() {
		return pnlStartScreen;
	}
}
