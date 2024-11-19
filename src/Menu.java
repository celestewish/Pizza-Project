import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu extends CardScreen {
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
		
		lblCurTotal.setText("Total cost for this order: $0.00 ");
		lblCurTotal.setText("Current Total: $0.00 ");
		addTotalCostField(lblTotalCost);
		addTotalCostField(lblCurTotal);
		
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
		Font textFont = new Font("Times New Roman", Font.BOLD, 24);
		Font optionsFont = new Font("Arial", Font.PLAIN, 20);
		JComboBox<DrinkType> cobxType = new JComboBox<>(DrinkType.values());
		cobxType.setFont(optionsFont);
		JComboBox<DrinkSize> cobxSize = new JComboBox<>(DrinkSize.values());
		cobxSize.setFont(optionsFont);
		JComboBox<Integer> cobxNumber = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		cobxNumber.setFont(optionsFont);
		
		// Create a panel to hold the combo box
		JPanel panel = new JPanel();
		JLabel txt1 = new JLabel("Choose Your Drink:");
		txt1.setFont(textFont);
		panel.add(txt1);
		panel.add(cobxType);
		panel.add(cobxSize);
		JLabel txt2 = new JLabel("How Many:");
		txt2.setFont(textFont);
		panel.add(txt2);
		panel.add(cobxNumber);
		
		Font originalFont = UIManager.getFont("Button.font");
		UIManager.put("Button.font", new Font("Times New Roman", Font.PLAIN, 18));
		
		int result = JOptionPane.showConfirmDialog(
				null,          // Parent component (null for center of screen)
				panel,         // Content panel
				"Add a drink", // Title
				JOptionPane.OK_CANCEL_OPTION, // Buttons: OK and Cancel
				JOptionPane.PLAIN_MESSAGE  // Icon type
		);
		
		UIManager.put("Button.font", originalFont);
		
		Drink drink = new Drink((DrinkSize) cobxSize.getSelectedItem(),(DrinkType) cobxType.getSelectedItem(),  1F);
		drink.setCount(cobxNumber.getSelectedIndex() + 1);
		
		if (result == JOptionPane.OK_OPTION) {
			info.getCurOrder().addItem(drink);
			updateTotalCostFields();
			
			txtAreaDrinkInfo.append(drink.toString());
		}
	}
}
