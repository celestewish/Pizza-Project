import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationsForUser extends CardScreen {
	private JPanel pnlLocationsUser;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnSignOut;
	private JButton btnCart;
	private JLabel lblHiName;
	private JLabel lblCurTotal;
	private JPanel pnlCartLogo;
	private JPanel pnlLogo;
	private JLabel ThankYou;
	private JLabel OrderPlace;
	private JLabel Total;
	private JButton btnCreateAccount;

	public LocationsForUser(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlLocationsUser);
		info.registerScreenName(Screen.LOCATIONS_USER, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		//sets up the buttons seen at the top of the screen
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
		btnCreateAccount.addActionListener(_ -> showScreen(Screen.MENU));
	}
	
	@Override
	public boolean onAttemptLeaveScreen(Screen destinationScreen) {
		return true;
	}

	//if the user is not logged in, they will be sent to a different screen
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		if (!info.isLoggedIn())
			return Screen.LOCATIONS;
		return toScreen;
	}
	
	@Override
	public void onEnterScreen() {
		setUpUserAndOrderInfo(lblHiName, lblCurTotal);
	
	}
	
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
