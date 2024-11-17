import javax.swing.*;
import java.awt.*;

public class MenuForNonUser extends CardScreen {
	private JPanel pnlMenu;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnSignIn_SignUp;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnCreateAccount;
	private JTextArea cokeDietCokePepsiTextArea;
	private JTextArea cookieBrowniesCinnamonKnotsTextArea;
	
	public MenuForNonUser(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);
		
		
		btnHome.addActionListener(_ -> {
			showScreen("StartScreen");
		});
		
		btnDeals.addActionListener(_ -> {
			showScreen("Deals");
		});
		
		btnMenu.addActionListener(_ -> {
			showScreen("Menu");
		});
		
		btnSignIn_SignUp.addActionListener(_ -> {
			showScreen("SignIn");
		});
		
		btnLocations.addActionListener(_ -> {
			showScreen("LocationScreen");
		});
		
		btnCreateAccount.addActionListener(_ -> {
			showScreen("SignUp");
		});
	}
	
	public JPanel getScreenPanel() {
		return pnlMenu;
	}
}
