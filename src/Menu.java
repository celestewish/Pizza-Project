import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
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
		AtomicInteger drinkSize = new AtomicInteger();
		AtomicInteger amount = new AtomicInteger();
		AtomicReference<AtomicReferenceArray<Drink>> drinks = null;
		AtomicReference<AtomicReferenceArray<Dessert>> desserts = null;
		AtomicReference<AtomicReferenceArray<Side>> wings = null;
		AtomicReference<AtomicReferenceArray<Side>> garlics = null;
		AtomicReference<AtomicReferenceArray<Side>> salads = null;
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
			drinks.set(new AtomicReferenceArray<>(new Drink[amount.get()]));
			for (int i = 0; i < amount.get(); i++) {
				drinks.get().set(i, new Drink(drinkSize.get()));
			}
		});
		optDrinkCt.addActionListener(_ -> {amount.set((int) optDessertCt.getSelectedItem());});
		btnAddDessert.addActionListener(_ -> {
			desserts.set(new AtomicReferenceArray<>(new Dessert[amount.get()]));
			for (int i = 0; i < amount.get(); i++) {
				desserts.get().set(i, new Dessert());
			}
		});
		optWingCt.addActionListener(_ -> {amount.set((int) optWingCt.getSelectedItem());});
		btnAddWing.addActionListener(_ -> {
			wings.set(new AtomicReferenceArray<>(new Side[amount.get()]));
			for (int i = 0; i < amount.get(); i++) {
				wings.get().set(i, new Side());
			}
		});
		optGarlicCt.addActionListener(_ -> {amount.set((int) optGarlicCt.getSelectedItem());});
		btnAddGarlicBread.addActionListener(_ -> {
			garlics.set(new AtomicReferenceArray<>(new Side[amount.get()]));
			for (int i = 0; i < amount.get(); i++) {
				garlics.get().set(i, new Side());
			}
		});
		optSaladCount.addActionListener(_ -> {amount.set((int) optSaladCount.getSelectedItem());});
		btnAddSalad.addActionListener(_ -> {
			salads.set(new AtomicReferenceArray<>(new Side[amount.get()]));
			for (int i = 0; i < amount.get(); i++) {
				salads.get().set(i, new Side());
			}
		});
		placeYourOrderButton.addActionListener(_ -> {
			LinkedList<MenuItem> orderItems = new LinkedList<MenuItem>();
			for (int i = 0; i < drinks.get().length(); i++) {

			}
			Order myOrder = new Order(orderItems);
		});
	}
	
	public JPanel getScreenPanel() {
		return menuPanel;
	}
}
