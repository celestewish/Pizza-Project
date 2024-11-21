import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Menu extends CardScreen {
	private JPanel pnlMenu;
	
	private JScrollPane scrollPane;
	
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
	
	private double currentVerticalScrollPos = 0;
	
	public Menu(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlMenu);
		info.registerScreenName(Screen.MENU, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
		
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		
		lblCurTotal.setText("Current Total: $0.00 ");
		addTotalCostField(lblTotalCost);
		addTotalCostField(lblCurTotal);
		
		addJComponent(txtAreaDrinkInfo);
		addJComponent(txtAreaGarlicBreadInfo);
		addJComponent(txtAreaGarlicKnotsInfo);
		addJComponent(txtAreaPizzaInfo);
		addJComponent(txtAreaSaladInfo);
		addJComponent(txtAreaWingInfo);
		addJComponent(lblDrinkPrice);
		addJComponent(lblGarlicBreadPrice);
		addJComponent(lblPizzaPrice);
		addJComponent(lblGarlicKnotsPrice);
		addJComponent(lblWingPrice);
		addJComponent(lblSaladPrice);
		addJComponent(lblTotalCost);

		for (JComponent j : getComponents()) {
			j.setFocusable(false);
		}
		
		btnAddDrink.addActionListener(_ -> DrinkOptionPopUp());
		btnAddWings.addActionListener(_ -> WingsOptionPopUp());
		btnAddGarlicBread.addActionListener(_ -> GarlicBreadOptionPopUp());
		btnAddGarlicKnots.addActionListener(_ -> GarlicKnotsOptionPopUp());
		btnAddSalad.addActionListener(_ -> SaladOptionPopUp());
		btnEditDrinks.addActionListener(_ -> showScreen(Screen.CART));
		btnEditGarlicBread.addActionListener(_ -> showScreen(Screen.CART));
		btnEditGarlicKnots.addActionListener(_ -> showScreen(Screen.CART));
		btnEditSalad.addActionListener(_ -> showScreen(Screen.CART));
		btnEditWings.addActionListener(_ -> showScreen(Screen.CART));
		btnEditPizza.addActionListener(_ -> showScreen(Screen.CART));
		btnViewOrder.addActionListener(_ -> viewOrderScreen());
		
		btnCreatePizza.addActionListener(_ -> {
			if (info.getCurOrder().getNumberPizzasInOrder() >= 10)
				showInfoDialogue("You've reached the maximum amount of pizzas for one order!", "Fine, but I'll be back for more pizza later", "Pizza limit reached!");
			else
				showScreen(Screen.CREATE_PIZZA);
		});
		
		btnPlaceOrder.addActionListener(_ ->  {
			if (info.getCurOrder().getItems().isEmpty())
				showInfoDialogue("Please add an item to your order!", "Okay", "No items in cart");
			else
				showScreen(Screen.CHECK_OUT);
		});
	}

	public void viewOrderScreen() {
        JPanel orderPanel = new JPanel();
		currentVerticalScrollPos = scrollPane.getViewport().getViewPosition().getY();
		JTextArea orderInfo = new JTextArea();
		orderInfo.setFocusable(false);
		orderInfo.setFont(info.getTextFont());
		
		orderInfo.append(info.getCurOrder().toString());
		
		orderPanel.add(orderInfo);
		
        JOptionPane.showMessageDialog(null, orderPanel, "View Order", JOptionPane.INFORMATION_MESSAGE);
    }

	@Override
	public boolean onAttemptLeaveScreen(Screen destinationScreen) {
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
		setUpUserAndOrderInfo(lblHiName, lblCurTotal);
		resetScreen();
		resetCostFields();
		
		refillInfoFields();
		updateSubCostField(lblPizzaPrice, new Pizza());
		currentVerticalScrollPos = 0;
		SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition((new Point(0, 0))));
		info.setCurPizza(null);
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
		
		JComboBox<String> cobxType = new JComboBox<>();
		Utils.populateComboBox(cobxType, DrinkType.class);
		cobxType.setFont(optionsFont);
		Map<String, DrinkType> drinkTypeMap = Utils.createEnumMap(DrinkType.class);
		
		JComboBox<String> cobxSize = new JComboBox<>();
		Utils.populateComboBox(cobxSize, DrinkSize.class);
		cobxSize.setFont(optionsFont);
		Map<String, DrinkSize> drinkSizeMap = Utils.createEnumMap(DrinkSize.class);
		
		JComboBox<Integer> cobxNumber = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		cobxNumber.setFont(optionsFont);
		
		currentVerticalScrollPos = scrollPane.getViewport().getViewPosition().getY();
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
		
		DrinkSize selectedDrinkSize = drinkSizeMap.get((String) cobxSize.getSelectedItem());
		DrinkType selectedDrinkType = drinkTypeMap.get((String) cobxType.getSelectedItem());
		MenuItemWithCount drinkCount = new MenuItemWithCount(
				new Drink(selectedDrinkSize, selectedDrinkType, 1F),
				count);
		
		if (result == JOptionPane.OK_OPTION) {
			addItemToOrder(drinkCount);
			updateFields();
		}
		resetScrollPos();
	}
	
	public void WingsOptionPopUp() {
		Font textFont = new Font("Times New Roman", Font.BOLD, 24);
		Font optionsFont = new Font("Arial", Font.PLAIN, 20);
		
		JComboBox<String> cobxType = new JComboBox<>();
		Utils.populateComboBox(cobxType, WingType.class);
		cobxType.setFont(optionsFont);
		Map<String, WingType> wingTypeMap = Utils.createEnumMap(WingType.class);
		
		JComboBox<Integer> cobxCount = new JComboBox<>(new Integer[]{5, 10});
		cobxCount.setFont(optionsFont);
		
		JComboBox<Integer> cobxNumber = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		cobxNumber.setFont(optionsFont);
		
		currentVerticalScrollPos = scrollPane.getViewport().getViewPosition().getY();
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
		
		WingType selectedWingType = wingTypeMap.get((String) cobxType.getSelectedItem());
		MenuItemWithCount wingsCount = new MenuItemWithCount(
				new Wings(SideType.WINGS, 6F, wingCt, selectedWingType),
				count);
		
		if (result == JOptionPane.OK_OPTION) {
			addItemToOrder(wingsCount);
			updateFields();
		}
		resetScrollPos();
	}
	
	public void GarlicBreadOptionPopUp() {
		Font textFont = new Font("Times New Roman", Font.BOLD, 24);
		Font optionsFont = new Font("Arial", Font.PLAIN, 20);
		
		JComboBox<Integer> cobxCount = new JComboBox<>(new Integer[]{5, 10});
		cobxCount.setFont(optionsFont);
		
		JComboBox<Integer> cobxNumber = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		cobxNumber.setFont(optionsFont);
		
		currentVerticalScrollPos = scrollPane.getViewport().getViewPosition().getY();
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
		
		MenuItemWithCount garlicBreadCount = new MenuItemWithCount(
				new Side(SideType.GARLIC_BREAD, 4F, breadCt),
				count);
		
		if (result == JOptionPane.OK_OPTION) {
			addItemToOrder(garlicBreadCount);
			updateFields();
		}
		resetScrollPos();
	}
	
	public void GarlicKnotsOptionPopUp() {
		Font textFont = new Font("Times New Roman", Font.BOLD, 24);
		Font optionsFont = new Font("Arial", Font.PLAIN, 20);
		
		JComboBox<Integer> cobxCount = new JComboBox<>(new Integer[]{5, 10});
		cobxCount.setFont(optionsFont);
		
		JComboBox<Integer> cobxNumber = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		cobxNumber.setFont(optionsFont);
		
		currentVerticalScrollPos = scrollPane.getViewport().getViewPosition().getY();
		// Create a panel to hold the combo box
		JPanel panel = new JPanel();
		JLabel txt1 = new JLabel("Count:");
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
		
		MenuItemWithCount garlicKnotsCount = new MenuItemWithCount(
				new Side(SideType.GARLIC_KNOTS, 5F, knotCt),
				count);
		
		if (result == JOptionPane.OK_OPTION) {
			addItemToOrder(garlicKnotsCount);
			updateFields();
		}
		resetScrollPos();
	}
	
	public void SaladOptionPopUp() {
		JComboBox<Integer> cobxCount = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		cobxCount.setFont(info.getOptionsFont());
		
		currentVerticalScrollPos = scrollPane.getViewport().getViewPosition().getY();
		// Create a panel to hold the combo box
		JPanel panel = new JPanel();
		JLabel txt1 = new JLabel("Count:");
		txt1.setFont(info.getTextFont());
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
		
		MenuItemWithCount saladCount = new MenuItemWithCount(
				new Side(SideType.CAESAR_SALAD, 3F),
				count);
		
		if (result == JOptionPane.OK_OPTION) {
			addItemToOrder(saladCount);
			updateFields();
		}
		resetScrollPos();
	}
	
	
	public void refillInfoFields() {
		for (MenuItemWithCount m : info.getCurOrder().getItems()) {
			MenuItem item = m.getItem();
			if (item instanceof Pizza) {
				txtAreaPizzaInfo.append(m + "\n");
			} else if (item instanceof Drink) {
				txtAreaDrinkInfo.append(m + "\n");
			} else if (item instanceof Side sideItem) {
				// Handle the case for WINGS type
				if (sideItem.getType() == SideType.WINGS) {
					txtAreaWingInfo.append(m + "\n");
				} else {
					// For other side types
					switch (sideItem.getType()) {
						case CAESAR_SALAD -> txtAreaSaladInfo.append(m + "\n");
						case GARLIC_BREAD -> txtAreaGarlicBreadInfo.append(m + "\n");
						case GARLIC_KNOTS -> txtAreaGarlicKnotsInfo.append(m + "\n");
					}
				}
			}
		}
	}
	
	public void updateFields() {
		resetScreen();
		resetCostFields();
		lblTotalCost.setText("Total: $0.00");
		refillInfoFields();
		updateTotalCostFields();
		updateSubCostField(lblPizzaPrice, new Pizza());
		updateSubCostField(lblGarlicKnotsPrice, new Side(SideType.GARLIC_KNOTS, 0, 1));
		updateSubCostField(lblGarlicBreadPrice, new Side(SideType.GARLIC_BREAD, 0, 1));
		updateSubCostField(lblPizzaPrice, new Pizza());
		updateSubCostField(lblWingPrice, new Wings(SideType.WINGS, 0, 1, WingType.HOT_WINGS));
		updateSubCostField(lblSaladPrice, new Side(SideType.CAESAR_SALAD, 0, 1));
		updateSubCostField(lblDrinkPrice, new Drink(DrinkSize.MEDIUM, DrinkType.DR_PEPPER, 0));
		System.out.println(info.getCurOrder().toString());
	}
	
	public void resetCostFields() {
		lblDrinkPrice.setText("Sub Total: $0.00");
		lblGarlicBreadPrice.setText("Sub Total: $0.00");
		lblPizzaPrice.setText("Sub Total: $0.00");
		lblGarlicKnotsPrice.setText("Sub Total: $0.00");
		lblWingPrice.setText("Sub Total: $0.00");
		lblSaladPrice.setText("Sub Total: $0.00");
	}
	
	public void addItemToOrder(MenuItemWithCount itemWithCount) {
		int outcome = info.getCurOrder().addItem(itemWithCount);
		if (outcome == -1) {
			showInfoDialogue("You've reached the maximum number of this item", "Okay", "Max amount of 1 item is 10");
		}
	}
	
	public void resetScrollPos() {
		SwingUtilities.invokeLater(() -> {
			System.out.println("Setting scroll position to: " + currentVerticalScrollPos);
			scrollPane.getViewport().setViewPosition(new Point(0, (int) currentVerticalScrollPos));
		});
		
		// Ensure layout and repaint are complete
		scrollPane.revalidate();
		scrollPane.repaint();
	}
}
