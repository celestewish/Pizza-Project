import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Locations extends CardScreen{
	private JPanel pnlLocations;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnSignUp_SignIn;
	private JPanel pnlLogo;
	private JLabel ThankYou;
	private JLabel OrderPlace;
	private JLabel Total;
	private JButton btnCreateAccount;
	private JButton btnSignOut;
	private JButton btnCart;
	private JLabel lblHiName;
	private JLabel lblCurTotal;
	private JPanel pnlNavBarLoggedIn;
	
	public Locations(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlLocations);
		info.registerScreenName(Screen.LOCATIONS, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		//sets up the buttons seen at the top of the screen
		setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
		btnCreateAccount.addActionListener(_ -> showScreen(Screen.SIGN_IN));
	}
	
	@Override
	public boolean onAttemptLeaveScreen(Screen destinationScreen) {
		return true;
	}

	//if the user is logged in, they will be sent to a different screen.
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		if (info.isLoggedIn())
			return Screen.LOCATIONS_USER;
		return toScreen;
	}
	
	@Override
	public void onEnterScreen() {
	
	}
	//creates the images seen on the screen
	private void createUIComponents() {
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
