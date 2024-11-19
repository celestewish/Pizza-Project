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
		
		addJComponent(txtAreaDrinkInfo);
		addJComponent(txtAreaGarlicBreadInfo);
		addJComponent(txtAreaGarlicKnotsInfo);
		addJComponent(txtAreaPizzaInfo);
		addJComponent(txtAreaSaladInfo);
		addJComponent(txtAreaWingInfo);
		
		btnAddDrink.addActionListener(_ -> DrinkOptionPopUp());
		btnAddWings.addActionListener(_ -> WingsOptionPopUp());
		btnAddGarlicBread.addActionListener(_ -> GarlicBreadOptionPopUp());
		btnAddGarlicKnots.addActionListener(_ -> GarlicKnotsOptionPopUp());
		btnAddSalad.addActionListener(_ -> SaladOptionPopUp());
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
		
		resetScreen();
		refillInfoFields();
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
		
		System.out.println(info.getCurOrder().toString());
		
		int count = cobxNumber.getSelectedIndex() + 1;
		Drink drink = new Drink((DrinkSize) cobxSize.getSelectedItem(), (DrinkType) cobxType.getSelectedItem(),  1F);
		MenuItemWithCount drinkCount = new MenuItemWithCount(drink, count);
		
		if (result == JOptionPane.OK_OPTION) {
			if (info.getCurOrder().addItem(drinkCount)) {
				txtAreaDrinkInfo.append(drinkCount.toString());
				txtAreaDrinkInfo.append("\n");
			}
			else {
				resetScreen();
				refillInfoFields();
				updateSubCostField(lblDrinkPrice, drink);
			}
			
			updateTotalCostFields();
			System.out.println(info.getCurOrder().toString());
		}
	}
	
	public void WingsOptionPopUp() {
		Font textFont = new Font("Times New Roman", Font.BOLD, 24);
		Font optionsFont = new Font("Arial", Font.PLAIN, 20);
		SideType[] wingOptions = {SideType.CHICKEN_WINGS, SideType.LEMON_PEPPER_WINGS};
		JComboBox<SideType> cobxType = new JComboBox<>(wingOptions);
		cobxType.setFont(optionsFont);
		
		JComboBox<Integer> cobxCount = new JComboBox<>(new Integer[]{5, 10});
		cobxCount.setFont(optionsFont);
		
		JComboBox<Integer> cobxNumber = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		cobxNumber.setFont(optionsFont);
		
		// Create a panel to hold the combo box
		JPanel panel = new JPanel();
		JLabel txt1 = new JLabel("Choose Your Wing Type and Count:");
		txt1.setFont(textFont);
		panel.add(txt1);
		panel.add(cobxType);
		panel.add(cobxCount);
		JLabel txt2 = new JLabel("How Many:");
		txt2.setFont(textFont);
		panel.add(txt2);
		panel.add(cobxNumber);
		
		Font originalFont = UIManager.getFont("Button.font");
		UIManager.put("Button.font", new Font("Times New Roman", Font.PLAIN, 18));
		int result = JOptionPane.showConfirmDialog(
				null,          // Parent component (null for center of screen)
				panel,         // Content panel
				"Add an Order of Wings", // Title
				JOptionPane.OK_CANCEL_OPTION, // Buttons: OK and Cancel
				JOptionPane.PLAIN_MESSAGE  // Icon type
		);
		UIManager.put("Button.font", originalFont);
		
		System.out.println(info.getCurOrder().toString());
		
		int count = cobxNumber.getSelectedIndex() + 1;
		int wingCt = cobxCount.getSelectedIndex() == 0 ? 5 : 10;
		Wings wings = new Wings((SideType) cobxType.getSelectedItem(), 6F, wingCt);
		MenuItemWithCount wingsCount = new MenuItemWithCount(wings, count);
		
		if (result == JOptionPane.OK_OPTION) {
			if (info.getCurOrder().addItem(wingsCount)) {
				txtAreaWingInfo.append(wingsCount.toString());
				txtAreaWingInfo.append("\n");
			}
			else {
				resetScreen();
				refillInfoFields();
				updateSubCostField(lblWingPrice, wings);
			}
			
			updateTotalCostFields();
			System.out.println(info.getCurOrder().toString());
		}
	}
	
	public void GarlicBreadOptionPopUp() {
		Font textFont = new Font("Times New Roman", Font.BOLD, 24);
		Font optionsFont = new Font("Arial", Font.PLAIN, 20);
		
		JComboBox<Integer> cobxCount = new JComboBox<>(new Integer[]{5, 10});
		cobxCount.setFont(optionsFont);
		
		JComboBox<Integer> cobxNumber = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		cobxNumber.setFont(optionsFont);
		
		// Create a panel to hold the combo box
		JPanel panel = new JPanel();
		JLabel txt1 = new JLabel("Choose Your Garlic Bread Count:");
		txt1.setFont(textFont);
		panel.add(txt1);
		panel.add(cobxCount);
		JLabel txt2 = new JLabel("How Many:");
		txt2.setFont(textFont);
		panel.add(txt2);
		panel.add(cobxNumber);
		
		Font originalFont = UIManager.getFont("Button.font");
		UIManager.put("Button.font", new Font("Times New Roman", Font.PLAIN, 18));
		int result = JOptionPane.showConfirmDialog(
				null,          // Parent component (null for center of screen)
				panel,         // Content panel
				"Add Garlic Bread", // Title
				JOptionPane.OK_CANCEL_OPTION, // Buttons: OK and Cancel
				JOptionPane.PLAIN_MESSAGE  // Icon type
		);
		UIManager.put("Button.font", originalFont);
		
		System.out.println(info.getCurOrder().toString());
		
		int count = cobxNumber.getSelectedIndex() + 1;
		int breadCt = cobxCount.getSelectedIndex() == 0 ? 5 : 10;
		Side garlicBread = new Side(SideType.GARLIC_BREAD, 4F, breadCt);
		MenuItemWithCount garlicBreadCount = new MenuItemWithCount(garlicBread, count);
		
		if (result == JOptionPane.OK_OPTION) {
			if (info.getCurOrder().addItem(garlicBreadCount)) {
				txtAreaGarlicBreadInfo.append(garlicBreadCount.toString());
				txtAreaGarlicBreadInfo.append("\n");
			} else {
				resetScreen();
				refillInfoFields();
				updateSubCostField(lblGarlicBreadPrice, garlicBread);
			}
			
			updateTotalCostFields();
			System.out.println(info.getCurOrder().toString());
		}
	}
	
	public void GarlicKnotsOptionPopUp() {
		Font textFont = new Font("Times New Roman", Font.BOLD, 24);
		Font optionsFont = new Font("Arial", Font.PLAIN, 20);
		
		JComboBox<Integer> cobxCount = new JComboBox<>(new Integer[]{5, 10});
		cobxCount.setFont(optionsFont);
		
		JComboBox<Integer> cobxNumber = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		cobxNumber.setFont(optionsFont);
		
		// Create a panel to hold the combo box
		JPanel panel = new JPanel();
		JLabel txt1 = new JLabel("Choose Your Garlic Knots Count:");
		txt1.setFont(textFont);
		panel.add(txt1);
		panel.add(cobxCount);
		JLabel txt2 = new JLabel("How Many:");
		txt2.setFont(textFont);
		panel.add(txt2);
		panel.add(cobxNumber);
		
		Font originalFont = UIManager.getFont("Button.font");
		UIManager.put("Button.font", new Font("Times New Roman", Font.PLAIN, 18));
		int result = JOptionPane.showConfirmDialog(
				null,          // Parent component (null for center of screen)
				panel,         // Content panel
				"Add Garlic Knots", // Title
				JOptionPane.OK_CANCEL_OPTION, // Buttons: OK and Cancel
				JOptionPane.PLAIN_MESSAGE  // Icon type
		);
		UIManager.put("Button.font", originalFont);
		
		System.out.println(info.getCurOrder().toString());
		
		int count = cobxNumber.getSelectedIndex() + 1;
		int knotCt = cobxCount.getSelectedIndex() == 0 ? 5 : 10;
		Side garlicKnots = new Side(SideType.GARLIC_KNOTS, 5F, knotCt);
		MenuItemWithCount garlicKnotsCount = new MenuItemWithCount(garlicKnots, count);
		
		if (result == JOptionPane.OK_OPTION) {
			if (info.getCurOrder().addItem(garlicKnotsCount)) {
				txtAreaGarlicKnotsInfo.append(garlicKnotsCount.toString());
				txtAreaGarlicKnotsInfo.append("\n");
			} else {
				resetScreen();
				refillInfoFields();
				updateSubCostField(lblGarlicKnotsPrice, garlicKnots);
			}
			
			updateTotalCostFields();
			System.out.println(info.getCurOrder().toString());
		}
	}
	
	
	public void SaladOptionPopUp() {
		Font textFont = new Font("Times New Roman", Font.BOLD, 24);
		Font optionsFont = new Font("Arial", Font.PLAIN, 20);
		
		JComboBox<Integer> cobxCount = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
		cobxCount.setFont(optionsFont);
		
		// Create a panel to hold the combo box
		JPanel panel = new JPanel();
		JLabel txt1 = new JLabel("Choose How Many Caesar Salads:");
		txt1.setFont(textFont);
		panel.add(txt1);
		panel.add(cobxCount);
		
		Font originalFont = UIManager.getFont("Button.font");
		UIManager.put("Button.font", new Font("Times New Roman", Font.PLAIN, 18));
		int result = JOptionPane.showConfirmDialog(
				null,          // Parent component (null for center of screen)
				panel,         // Content panel
				"Add Caesar Salad", // Title
				JOptionPane.OK_CANCEL_OPTION, // Buttons: OK and Cancel
				JOptionPane.PLAIN_MESSAGE  // Icon type
		);
		UIManager.put("Button.font", originalFont);
		
		System.out.println(info.getCurOrder().toString());
		
		int count = cobxCount.getSelectedIndex() + 1;
		Side salad = new Side(SideType.CAESAR_SALAD, 3F, count);
		MenuItemWithCount saladCount = new MenuItemWithCount(salad, 1);
		
		if (result == JOptionPane.OK_OPTION) {
			if (info.getCurOrder().addItem(saladCount)) {
				txtAreaSaladInfo.append(saladCount.toString());
				txtAreaSaladInfo.append("\n");
			} else {
				resetScreen();
				refillInfoFields();
				updateSubCostField(lblSaladPrice, salad);
			}
			
			updateTotalCostFields();
			System.out.println(info.getCurOrder().toString());
		}
	}
	
	
	public void refillInfoFields() {
		for (MenuItemWithCount m : info.getCurOrder().getItems()) {
			MenuItem item = m.getItem();
			if (item instanceof Pizza) {
				txtAreaPizzaInfo.append(m + "\n");
			} else if (item instanceof Drink) {
				txtAreaDrinkInfo.append(m + "\n");
			} else if (item instanceof Side) {
				switch (((Side) item).getType()) {
					case CAESAR_SALAD -> txtAreaSaladInfo.append(m + "\n");
					case GARLIC_BREAD -> txtAreaGarlicBreadInfo.append((m) + "\n");
					case GARLIC_KNOTS -> txtAreaGarlicKnotsInfo.append(m + "\n");
					case CHICKEN_WINGS, LEMON_PEPPER_WINGS -> txtAreaWingInfo.append(m + "\n");
				}
			}
		}
	}
}
