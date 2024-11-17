import javax.swing.*;
import java.awt.*;

public class LocationScreen extends CardScreen {
	private JPanel pnlBackground;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnSignIn_SignUp;
	private JButton btnDeals;
	private JButton btnLocations;
	
	
	public LocationScreen(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);
		
		btnHome.addActionListener(_ -> {
			showScreen("StartScreen");
		});
		
		btnMenu.addActionListener(_ -> {
			showScreen("Menu");
		});
		
		btnSignIn_SignUp.addActionListener(_ -> {
			showScreen("SignIn");
		});
		
		btnDeals.addActionListener(_ -> {
			showScreen("Deals");
		});
		
		btnLocations.addActionListener(_ -> {
			showScreen("LocationScreen");
		});
	}
	
	public JPanel getScreenPanel() {
		return pnlBackground;
	}
}
