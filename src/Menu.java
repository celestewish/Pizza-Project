import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

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
		AtomicInteger drinkSize = new AtomicInteger();
		AtomicInteger amount = new AtomicInteger();
		btnCreatePizza.addActionListener(_ -> showScreen("PizzaGUI"));
		btnHome.addActionListener(_ -> showScreen("StartScreen"));
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
		optDrinkCt.addActionListener(_ -> {amount.set((int) optDrinkCt.getSelectedItem());});
		btnAddDrink.addActionListener(_ -> {
			Drink[] drinks = new Drink[amount.get()];
			for (int i = 0; i < amount.get(); i++) {
				drinks[i] = new Drink(drinkSize.get());
			}
		});
		optDrinkCt.addActionListener(_ -> {amount.set((int) optDessertCt.getSelectedItem());});
		btnAddDessert.addActionListener(_ -> {
			Dessert[] desserts = new Dessert[amount.get()];
			for (int i = 0; i < amount.get(); i++) {
				desserts[i] = new Dessert();
			}
		});
		optWingCt.addActionListener(_ -> {amount.set((int) optWingCt.getSelectedItem());});
		btnAddWing.addActionListener(_ -> {
			Side[] wings = new Side[amount.get()];
			for (int i = 0; i < amount.get(); i++) {
				wings[i] = new Side();
			}
		});
		optGarlicCt.addActionListener(_ -> {amount.set((int) optGarlicCt.getSelectedItem());});
		btnAddGarlicBread.addActionListener(_ -> {
			Side[] garlics = new Side[amount.get()];
			for (int i = 0; i < amount.get(); i++) {
				garlics[i] = new Side();
			}
		});
		optSaladCount.addActionListener(_ -> {amount.set((int) optSaladCount.getSelectedItem());});
		btnAddSalad.addActionListener(_ -> {
			Side[] salads = new Side[amount.get()];
			for (int i = 0; i < amount.get(); i++) {
				salads[i] = new Side();
			}
		});
		placeYourOrderButton.addActionListener(_ -> {

		});
	}
	
	public JPanel getScreenPanel() {
		return menuPanel;
	}
}
