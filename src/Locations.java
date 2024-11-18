import javax.swing.*;
import java.awt.*;

public class Locations extends CardScreen{
	private JPanel pnlLocations;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnSignUp_SignIn;
	private JButton btnSignOut;
	private JButton btnCart;
	private JLabel lblHiName;
	private JLabel lblCurTotal;
	private JPanel pnlNavBarLoggedIn;
	private JPanel pnlNavBarLoggedOut;
	
	public Locations(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
		super(screenLayoutController, screenContainer, info, panelName);
		setScreenPanel(pnlLocations);
		info.registerScreenName(Screen.LOCATIONS, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
	}
	
	@Override
	public Screen onAttemptLeaveScreen(ProgramInfo info, Screen fromScreen) {
		return fromScreen;
	}
	
	@Override
	public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
		return toScreen;
	}
}
