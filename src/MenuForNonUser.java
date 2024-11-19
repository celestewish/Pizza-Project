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
	private JPanel pnlLogo;
	
	public MenuForNonUser(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlMenuNonUser);
		info.registerScreenName(Screen.MENU_NON_USER, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
		
		btnCreateAccount.addActionListener(_ -> showScreen(Screen.SIGN_IN));
	}
	
	@Override
	public boolean onAttemptLeaveScreen() {
		return true;
	}
	
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		return toScreen;
	}
	
	@Override
	public void onEnterScreen() {
	
	}
	
	private void createUIComponents() {
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
