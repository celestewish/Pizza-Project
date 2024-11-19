import javax.swing.*;
import java.awt.*;

public class Menu extends CardScreen{
	private JPanel pnlMenu;
	
	private JButton btnHome;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnMenu;
	private JButton btnSignOut;
	private JButton btnCart;
	private JLabel lblHiName;
	private JLabel lblCurTotal;
	
	private JButton placeYourOrderButton;
	
	private JButton btnViewOrder;
	private JLabel lblPizzaPrice;
	private JLabel lblTotalCost;
	private JTextArea txtAreaPizzaInfo;
	private JPanel pnlLogo;
	private JPanel pnlCartLogo;
	private JPanel imgPizza;
	private JButton btnCreatePizza;
	private JPanel imgWings;
	private JPanel imgDrinks;
	private JButton btnAddDrink;
	private JButton btnAddWings;
	private JLabel lblWingPrice;
	private JLabel lblDrinkPrice;
	private JLabel lblGarlicBread;
	private JPanel imgGarlicBread;
	private JButton btnAddGarlicBread;
	private JLabel lblSaladPrice;
	private JTextArea txtAreaSaladInfo;
	private JButton btnAddSalad;
	private JTextArea txtAreaWingInfo;
	private JTextArea txtAreaDrinkInfo;
	private JTextArea txtAreaGarlicInfo;
	private JPanel imgSalad;
	private JPanel imgGarlicKnots;
	private JButton btnAddGarlicKnots;
	private JButton btnEditPizza;
	private JButton btnEditWings;
	private JButton btnEditSalad;
	private JButton btnEditGarlicKnots;
	private JButton btnEditGarlicBread;
	private JButton btnEditDrinks;
	
	public Menu(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlMenu);
		info.registerScreenName(Screen.MENU, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
		
	}
	
	@Override
	public boolean onAttemptLeaveScreen() {
		return true;
	}
	
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		if (!info.isLoggedIn())
			return Screen.MENU_NON_USER;
		return toScreen;
	}
	
	@Override
	public void onEnterScreen() {
		lblHiName.setText("Hi, " + info.CurrentUser().getName().split(" ")[0]);
		if (info.getCurOrder() != null)
			lblCurTotal.setText("Current Total: $" + info.getCurOrder().calcTotalOrderCost());
	}
	
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
		
		imgWings = new ImagePanel("wings.jpg");
		imgPizza = new ImagePanel("pizza.jpg");
		imgDrinks = new ImagePanel("drink.jpg");
		imgGarlicBread = new ImagePanel("garlicbread.jpg");
		imgSalad = new ImagePanel("salad.jpg");
		imgGarlicKnots = new ImagePanel("garlicknots.jpg");
	}
}
