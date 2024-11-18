import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

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
	private JButton btnAddDrink;
	private JButton btnAddDessert;
	private JButton btnAddSalad;
	private JButton btnAddGarlicBread;
	private JButton btnAddWing;
	private JComboBox<?> optDrinkSize;
	private JComboBox<?> optDrinkCt;
	private JComboBox<?> optDessertCt;
	private JComboBox<?> optWingCt;
	private JComboBox<?> optGarlicCt;
	private JComboBox<?> optSaladCount;

	private JButton btnViewOrder;
	private JLabel lblPizzaPrice;
	private JLabel lblWingPrice;
	private JComboBox optWingType;
	private JComboBox optWingSize;
	private JLabel lblDrinkPrice;
	private JComboBox optDrinkType;
	private JLabel lblGarlicPrice;
	private JComboBox optGarlicSize;
	private JLabel lblDessertPrice;
	private JComboBox optDessertType;
	private JLabel lblSaladPrice;
	private JComboBox optSaladType;
	private JComboBox optSaladDressing;
	private JLabel lblTotalCost;
	
	public Menu(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
		super(screenLayoutController, screenContainer, info, panelName);
		setScreenPanel(pnlMenu);
		info.registerScreenName(Screen.MENU, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart, lblHiName, lblTotalCost);
		
		//variables
		AtomicInteger drinkSize = new AtomicInteger();
		AtomicInteger amount = new AtomicInteger();
		LinkedList<MenuItem> menuItems = new LinkedList<>();
		
		//listeners to add menu items to the order
		optDrinkSize.addActionListener(_ -> {
			if (optDrinkSize.getSelectedIndex() == 0)
				return;
			
			String size = (String) optDrinkSize.getSelectedItem();
			if (size == null)
				return;
			
			switch (size) {
				case "Small" -> drinkSize.set(0);
				case "Medium" -> drinkSize.set(1);
				case "Large" -> drinkSize.set(2);
			}
		});
		optDrinkCt.addActionListener(_ -> {
			amount.set(optDrinkCt.getSelectedIndex());
		});
		btnAddDrink.addActionListener(_ -> {
			for (int i = 0; i < amount.get(); i++) {
				Drink myDrink = new Drink(drinkSize.get());
				menuItems.add(myDrink);
			}
		});
		optDessertCt.addActionListener(_ -> {
			amount.set((int) optDessertCt.getSelectedIndex());
		});
		btnAddDessert.addActionListener(_ -> {
			for (int i = 0; i < amount.get(); i++) {
				Dessert dessert = new Dessert();
				menuItems.add(dessert);
			}
		});
		optWingCt.addActionListener(_ -> {
			amount.set((int) optWingCt.getSelectedIndex());
		});
		btnAddWing.addActionListener(_ -> {
			for (int i = 0; i < amount.get(); i++) {
				Side wing = new Side();
				menuItems.add(wing);
			}
		});
		optGarlicCt.addActionListener(_ -> {
			amount.set((int) optGarlicCt.getSelectedIndex());
		});
		btnAddGarlicBread.addActionListener(_ -> {
			for (int i = 0; i < amount.get(); i++) {
				Side garlic = new Side();
				menuItems.add(garlic);
			}
		});
		optSaladCount.addActionListener(_ -> {
			amount.set((int) optSaladCount.getSelectedIndex());
		});
		btnAddSalad.addActionListener(_ -> {
			for (int i = 0; i < amount.get(); i++) {
				Side salad = new Side();
				menuItems.add(salad);
			}
		});
		placeYourOrderButton.addActionListener(_ -> {
			menuItems.add(info.getCurPizza());
			Order myOrder = new Order(menuItems);
			showScreen(Screen.CHECK_OUT);
		});
	}
	
	@Override
	public Screen onAttemptLeaveScreen(ProgramInfo info, Screen fromScreen) {
		return fromScreen;
	}
	
	@Override
	public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
		return toScreen;
	}
}
