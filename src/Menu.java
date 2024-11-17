import javax.swing.*;
import java.awt.*;

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
	private JComboBox optDrinkType;
	private JComboBox optDrinkSize;
	private JComboBox optDrinkCt;
	private JComboBox optDessertType;
	private JComboBox optDessertCt;
	private JComboBox optWingType;
	private JComboBox optWingSize;
	private JComboBox optWingCt;
	private JComboBox optGarlicSize;
	private JComboBox optGarlicCt;
	private JComboBox optSaladType;
	private JComboBox optSaladDressing;
	private JComboBox optSaladCount;
	
	public Menu(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);

	}
	
	public JPanel getScreenPanel() {
		return menuPanel;
	}
}
