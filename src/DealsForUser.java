import javax.swing.*;
import java.awt.*;

public class DealsForUser extends CardScreen{
	private JPanel pnlDealsUser;
	
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
	private JButton btnOrdernow1;
	private JButton btnOrdernow2;

	public DealsForUser(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlDealsUser);
		info.registerScreenName(Screen.DEALS_USER, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);


		btnOrdernow1.addActionListener(_ -> {
			if (showConfirmationDialogueGreen("Please Confirm", "Add Deal to Order?")) {
				/*Pizza pizza = info.getCurPizza().clone();

				info.getCurOrder().addItem(new MenuItemWithCount(pizza, cobxCount.getSelectedIndex() + 1));
				orderComplete = true;

				 */
				showScreen(Screen.MENU);
			}
		});

		btnOrdernow2.addActionListener(_ -> {
			if (showConfirmationDialogueGreen("Please Confirm", "Add Deal to Order?")) {
				/*Pizza pizza = info.getCurPizza().clone();

				info.getCurOrder().addItem(new MenuItemWithCount(pizza, cobxCount.getSelectedIndex() + 1));
				orderComplete = true;

				 */
				showScreen(Screen.MENU);
			}
		});
	}
	
	@Override
	public boolean onAttemptLeaveScreen(Screen destinationScreen) {
		return true;
	}
	
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
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
