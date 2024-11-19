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
	private JButton btnCreatePizza;
	
	private JButton btnViewOrder;
	private JLabel lblPizzaPrice;
	private JLabel lblTotalCost;
	private JTextArea pizza1Pizza1InfoTextArea;
	private JPanel pnlLogo;
	private JPanel pnlCartLogo;
	
	public Menu(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
		super(screenLayoutController, screenContainer, info, panelName);
		setScreenPanel(pnlMenu);
		info.registerScreenName(Screen.MENU, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
		
//		//variables
//		AtomicInteger drinkSize = new AtomicInteger();
//		AtomicInteger amount = new AtomicInteger();
//		LinkedList<MenuItem> menuItems = new LinkedList<>();
//
//		//listeners to add menu items to the order
//		optDrinkSize.addActionListener(_ -> {
//			if (optDrinkSize.getSelectedIndex() == 0)
//				return;
//
//			String size = (String) optDrinkSize.getSelectedItem();
//			if (size == null)
//				return;
//
//			switch (size) {
//				case "Small" -> drinkSize.set(0);
//				case "Medium" -> drinkSize.set(1);
//				case "Large" -> drinkSize.set(2);
//			}
//		});
//		optDrinkCt.addActionListener(_ -> {
//			amount.set(optDrinkCt.getSelectedIndex());
//		});
//		btnAddDrink.addActionListener(_ -> {
//			for (int i = 0; i < amount.get(); i++) {
//				Drink myDrink = new Drink(drinkSize.get());
//				menuItems.add(myDrink);
//			}
//		});
//		optDessertCt.addActionListener(_ -> {
//			amount.set((int) optDessertCt.getSelectedIndex());
//		});
//		btnAddDessert.addActionListener(_ -> {
//			for (int i = 0; i < amount.get(); i++) {
//				Dessert dessert = new Dessert();
//				menuItems.add(dessert);
//			}
//		});
//		optWingCt.addActionListener(_ -> {
//			amount.set((int) optWingCt.getSelectedIndex());
//		});
//		btnAddWing.addActionListener(_ -> {
//			for (int i = 0; i < amount.get(); i++) {
//				Side wing = new Side();
//				menuItems.add(wing);
//			}
//		});
//		optGarlicCt.addActionListener(_ -> {
//			amount.set((int) optGarlicCt.getSelectedIndex());
//		});
//		btnAddGarlicBread.addActionListener(_ -> {
//			for (int i = 0; i < amount.get(); i++) {
//				Side garlic = new Side();
//				menuItems.add(garlic);
//			}
//		});
//		optSaladCount.addActionListener(_ -> {
//			amount.set((int) optSaladCount.getSelectedIndex());
//		});
//		btnAddSalad.addActionListener(_ -> {
//			for (int i = 0; i < amount.get(); i++) {
//				Side salad = new Side();
//				menuItems.add(salad);
//			}
//		});
//		placeYourOrderButton.addActionListener(_ -> {
//			menuItems.add(info.getCurPizza());
//			Order myOrder = new Order(menuItems);
//			showScreen(Screen.CHECK_OUT);
//		});
	}
	
	@Override
	public boolean onAttemptLeaveScreen(ProgramInfo info) {
		return true;
	}
	
	@Override
	public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
		if (!info.isLoggedIn())
			return Screen.MENU_NON_USER;
		return toScreen;
	}
	
	@Override
	public void onEnterScreen(ProgramInfo info) {
		lblHiName.setText("Hi, " + info.CurrentUser().getName().split(" ")[0]);
		if (info.getCurOrder() != null)
			lblCurTotal.setText("Current Total: $" + info.getCurOrder().calcTotalOrderCost());
	}
	
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("src/main/resources/images/cart.png");
		pnlLogo = new ImagePanel("src/main/resources/images/PizzaLogo.png");
	}
}
