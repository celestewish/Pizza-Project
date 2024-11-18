import javax.swing.*;
import java.awt.*;


public class Login extends CardScreen {
	private JPanel pnlLogin;

	//buttons
	private JButton btnLogin;
	private JButton btnSignUp_SignIn;
	private JButton btnMenu;
	private JButton btnHome;
	private JButton btnDeals;
	private JButton btnLocations;
	
	//constructor for start screen
	public Login(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
		super(screenLayoutController, screenContainer, info, panelName);
		setScreenPanel(pnlLogin);
		
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
		
		btnSignUp_SignIn.addActionListener(_ -> {
			showScreen("SignIn");
		});
		
		btnLocations.addActionListener(_ -> {
			showScreen("LocationScreen");
		});

		btnDeals.addActionListener(_ -> {
            showScreen("Deals");
        });
	}
}
