import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Cart class represents a screen that displays the details about the customer order.
 * This screen provides navigation to the checkout screen.
 */

public class Cart extends CardScreen {
	private JPanel pnlCart;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnSignOut;
	private JButton btnCart;
	private JPanel pnlCartLogo;
	private JPanel pnlLogo;
	private JLabel lblHiName;
	private JLabel lblCurTotal;
	private JLabel FoodType;
	private JLabel FoodDescription;
	private JButton editButton;
	private JButton removeButton;
	private JLabel SecondDescription;
	private JPanel orderPanel;
	private JLabel costNumber;
	private JButton returnButton;
	private JButton btnCheckout;
	private JPanel bigPanel;
	private JTextField textField1;
	private JTextArea textArea1;

	/**
	 * Constructor for the Cart screen.
	 *
	 * @param screenLayoutController The CardLayout controller for switching screens.
	 * @param screenContainer        The container holding all screens.
	 * @param panelName              The unique name for this panel.
	 */

	public Cart (CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlCart);
		info.registerScreenName(Screen.CART, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		removeButton.addActionListener(_ -> showDropdownPopup());
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
		returnButton.addActionListener(_ -> showScreen(Screen.MENU));
		btnCheckout.addActionListener(_ -> showScreen(Screen.CHECK_OUT));
	}
	//this dropdown allows the user to delete items in their order
	public void showDropdownPopup() {
		// Data for the dropdown (JComboBox)

		// Create the combo box (dropdown)
		JComboBox<MenuItemWithCount> comboBox = new JComboBox<>(info.getCurOrder().getItems().toArray(new MenuItemWithCount[0]));

		// Create a message in the popup
		JPanel panel = new JPanel();
		panel.add(new JLabel("Select item to delete:"));
		panel.add(comboBox);

		MenuItemWithCount toDelete = (MenuItemWithCount) comboBox.getSelectedItem();
		for (int i = 0; i < info.getCurOrder().getItems().size(); i++) {
			if (info.getCurOrder().getItems().contains(toDelete)) {
				info.getCurOrder().removeItem(info.getCurOrder().getItems().get(i).getItem());
			}
		}

		// Create the dialog popup to show the dropdown
		int option = JOptionPane.showConfirmDialog(null, panel, "Select Option", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		// Handle the selection after the popup closes
		if (option == JOptionPane.OK_OPTION) {
			String selectedOption = (String) comboBox.getSelectedItem();
			JOptionPane.showMessageDialog(null, "You deleted: " + selectedOption);
		}
	}

	/**
	 * Clears the order details displayed when leaving the screen.
	 *
	 * @param destinationScreen The screen the user is navigating to.
	 * @return True to allow navigation, false otherwise.
	 */

	@Override
	public boolean onAttemptLeaveScreen(Screen destinationScreen) {
		return true;
	}

	/**
	 * Handles logic for entering this screen
	 *
	 * @return The screen to navigate to.
	 */
	
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		return toScreen;
	}

	/**
	 * Sets up the screen when entering it including order details, and fonts.
	 */
	
	@Override
	public void onEnterScreen() {
		setUpUserAndOrderInfo(lblHiName, lblCurTotal);

		if (info.getCurPizza() != null) {
			Pizza myPizza = info.getCurPizza();
			FoodType.setText("Custom Pizza");
			FoodDescription.setText(myPizza.toString());
			SecondDescription.setText("");
		}
		textArea1.setText(info.getCurOrder().toString());
		textArea1.setFont(info.getOptionsFont());
		costNumber.setText(String.valueOf(info.getCurOrder().calcTotalOrderCost()));
	}

	/**
	 * Initializes custom UI components, such as images for logos.
	 */
	
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
