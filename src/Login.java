import javax.swing.*;
import java.awt.*;

public class Login extends CardScreen {
	private JPanel pnlLogin;
	private JPanel pnlLogo;
	
	private JButton btnSignUp_SignIn;
	private JButton btnMenu;
	private JButton btnHome;
	private JButton btnDeals;
	private JButton btnLocations;
	
	private JButton btnLogin;
	private JButton btnCreateAccount;
	
	public Login(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlLogin);
		info.registerScreenName(Screen.LOGIN, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
		
		btnLogin.addActionListener(_ -> showScreen(Screen.SIGN_IN));
		
		btnCreateAccount.addActionListener(_ -> showScreen(Screen.SIGN_UP));
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
