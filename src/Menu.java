import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu extends CardScreen{
	private JPanel pnlMenu;
	
	private JPanel pnlLogo;
	private JPanel pnlCartLogo;
	
	private JButton btnHome;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnMenu;
	private JButton btnSignOut;
	private JButton btnCart;
	private JLabel lblHiName;
	private JLabel lblCurTotal;
	
	private JButton btnViewOrder;

	private JLabel lblTotalCost;
	private JButton btnPlaceOrder;
	
	private JPanel imgPizza;
	private JLabel lblPizzaPrice;
	private JButton btnCreatePizza;
	private JButton btnEditPizza;
	private JTextArea txtAreaPizzaInfo;
	
	private JPanel imgWings;
	private JLabel lblWingPrice;
	private JButton btnAddWings;
	private JButton btnEditWings;
	private JTextArea txtAreaWingInfo;
	
	private JPanel imgSalad;
	private JLabel lblSaladPrice;
	private JButton btnAddSalad;
	private JButton btnEditSalad;
	private JTextArea txtAreaSaladInfo;
	
	private JPanel imgGarlicKnots;
	private JLabel lblGarlicKnotsPrice;
	private JButton btnAddGarlicKnots;
	private JButton btnEditGarlicKnots;
	private JTextArea txtAreaGarlicKnotsInfo;
	
	private JPanel imgGarlicBread;
	private JLabel lblGarlicBreadPrice;
	private JButton btnAddGarlicBread;
	private JButton btnEditGarlicBread;
	private JTextArea txtAreaGarlicBreadInfo;
	
	private JPanel imgDrinks;
	private JLabel lblDrinkPrice;
	private JButton btnAddDrink;
	private JButton btnEditDrinks;
	private JTextArea txtAreaDrinkInfo;
	
	
	public Menu(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlMenu);
		info.registerScreenName(Screen.MENU, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
		
		btnAddDrink.addActionListener(_ -> DrinkOptionPopUp());
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
	
	public void DrinkOptionPopUp() {
		JComboBox<DrinkType> cobxType = new JComboBox<>(DrinkType.values());
		JComboBox<DrinkSize> cobxSize = new JComboBox<>(DrinkSize.values());
		JComboBox<Integer> cobxNumber = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
	
		// Create a panel to hold the combo box
		JPanel panel = new JPanel();
		panel.add(new JLabel("Select Drink Size:"));
		panel.add(cobxType);
		panel.add(cobxSize);
		
		int result = JOptionPane.showConfirmDialog(
				null,          // Parent component (null for center of screen)
				panel,         // Content panel
				"Choose Pizza Size", // Title
				JOptionPane.OK_CANCEL_OPTION, // Buttons: OK and Cancel
				JOptionPane.QUESTION_MESSAGE  // Icon type
		);
		
		if (result == JOptionPane.OK_OPTION)
			info.getCurOrder().addItem(new Drink((DrinkSize) cobxSize.getSelectedItem(), 1F));
	}
}
