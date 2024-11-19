import javax.swing.*;
import java.awt.*;

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
	
	public LocationsForUser(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
		super(screenLayoutController, screenContainer, info, panelName);
		setScreenPanel(pnlLocationsUser);
		info.registerScreenName(Screen.LOCATIONS_USER, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
	}
	
	@Override
	public boolean onAttemptLeaveScreen(ProgramInfo info) {
		return true;
	}
	
	@Override
	public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
		if (!info.isLoggedIn())
			return Screen.LOCATIONS;
		return toScreen;
	}
	
	@Override
	public void onEnterScreen(ProgramInfo info) {
	
	}
	
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
