import javax.swing.*;
import java.awt.*;

public class MenuForNonUser extends CardScreen {
	private JPanel pnlMenuNonUser;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnSignUp_SignIn;
	
	private JButton btnCreateAccount;
	
	public MenuForNonUser(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
		super(screenLayoutController, screenContainer, info, panelName);
		setScreenPanel(pnlMenuNonUser);
		info.registerScreenName(Screen.MENU_NON_USER, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
		
		btnCreateAccount.addActionListener(_ -> showScreen(Screen.SIGN_IN));
	}
	
	@Override
	public boolean onAttemptLeaveScreen(ProgramInfo info) {
		return true;
	}
	
	@Override
	public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
		return toScreen;
	}
}
