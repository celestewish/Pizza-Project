import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Menu extends CardScreen{
	
	private JPanel menuPanel;
	
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnSignIn;
	private JButton placeYourOrderButton;
	private JLabel lblTotalCost;
	private JButton btnCreatePizza;
	private JLabel lblPizzaPrice;
	private JButton btnViewOrder;
	private JLabel lblDrinkPrice;
	private JLabel lblDessertPrice;
	private JLabel lblWingPrice;
	private JButton btnAddDrink;
	private JButton btnAddDessert;
	private JButton btnAddSalad;
	private JButton btnAddGarlicBread;
	private JButton btnAddWing;
	private JLabel lblGarlicPrice;
	private JLabel lblSaladPrice;
	private JComboBox<Integer> optDrinkType;
	private JComboBox<Integer> optDrinkSize;
	private JComboBox<Integer> optDrinkCt;
	private JComboBox<Integer> optDessertType;
	private JComboBox<Integer> optDessertCt;
	private JComboBox<Integer> optWingType;
	private JComboBox<Integer> optWingSize;
	private JComboBox<Integer> optWingCt;
	private JComboBox<Integer> optGarlicSize;
	private JComboBox<Integer> optGarlicCt;
	private JComboBox<Integer> optSaladType;
	private JComboBox<Integer> optSaladDressing;
	private JComboBox<Integer> optSaladCount;
	
	public Menu(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);
		//variables
		AtomicInteger drinkSize = new AtomicInteger();
		AtomicInteger amount = new AtomicInteger();
		LinkedList<MenuItem> menuItems = new LinkedList<>();

		//listeners
		btnCreatePizza.addActionListener(_ -> showScreen("PizzaGUI"));
		btnHome.addActionListener(_ -> showScreen("StartScreen"));
		btnLocations.addActionListener(_ -> showScreen("LocationScreen"));
		btnDeals.addActionListener(_ ->showScreen("Deals"));
		btnSignIn.addActionListener(_ -> showScreen("SignIn"));
		optDrinkSize.addActionListener(_ -> {
			if (optDrinkSize.getSelectedIndex() == 0)
				return;
			
			String size = (String)optDrinkSize.getSelectedItem();
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
		optDessertCt.addActionListener(_ -> {amount.set((int) optDessertCt.getSelectedIndex());});
		btnAddDessert.addActionListener(_ -> {
			for (int i = 0; i < amount.get(); i++) {
				Dessert dessert = new Dessert();
				menuItems.add(dessert);
			}
		});
		optWingCt.addActionListener(_ -> {amount.set((int) optWingCt.getSelectedIndex());});
		btnAddWing.addActionListener(_ -> {
			for (int i = 0; i < amount.get(); i++) {
				Side wing = new Side();
				menuItems.add(wing);
			}
		});
		optGarlicCt.addActionListener(_ -> {amount.set((int) optGarlicCt.getSelectedIndex());});
		btnAddGarlicBread.addActionListener(_ -> {
			for (int i = 0; i < amount.get(); i++) {
				Side garlic = new Side();
				menuItems.add(garlic);
			}
		});
		optSaladCount.addActionListener(_ -> {amount.set((int) optSaladCount.getSelectedIndex());});
		btnAddSalad.addActionListener(_ -> {
			for (int i = 0; i < amount.get(); i++) {
				Side salad = new Side();
				menuItems.add(salad);
			}
		});
		placeYourOrderButton.addActionListener(_ -> {
			menuItems.add(info.getCurPizza());
			Order myOrder = new Order(menuItems);
			showScreen("CheckOut");
		});
	}
	public JPanel getScreenPanel() {
		return menuPanel;
	}
}
