import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;

public class Cart extends CardScreen {
	private JPanel pnlCart;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnSignOut;
	private JButton btnCart;
	private JPanel pnlCartLogo;
	private JPanel pnlLogo;
	private JLabel lblHiName;
	private JLabel lblCurTotal;
	
	public Cart (CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
		super(screenLayoutController, screenContainer, info, panelName);
		setScreenPanel(pnlCart);
		info.registerScreenName(Screen.CART, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());

		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
	}
	
	@Override
	public boolean onAttemptLeaveScreen(ProgramInfo info) {
		return true;
	}
	
	@Override
	public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
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
