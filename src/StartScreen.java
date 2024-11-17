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
	private JButton btnDeals;
	private JButton btnLocations;


	public StartScreen(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);
		
		
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


		btnLocations.addActionListener(_ ->{ showScreen("Location");});

		btnDeals.addActionListener(_ -> {
            showScreen("Deals");
        });



	}
	
	public JPanel getScreenPanel() {
		return pnlStartScreen;
	}
}
