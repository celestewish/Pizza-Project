import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

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
	private JButton btnRemove;
	private JLabel SecondDescription;
	private JPanel orderPanel;
	private JButton btnCheckout;
	private JPanel pnlOrder;
	private JTextField textField1;
	private JTextArea txtAreaOrder;
	private JScrollPane scrollPane;
	private JButton btnReturn;
	
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
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
		
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		
		txtAreaOrder.setFocusable(false);
		
		addTotalCostField(lblCurTotal);
		
		btnRemove.addActionListener(_ -> showDropdownPopup());
		btnReturn.addActionListener(_ -> showScreen(Screen.MENU));
		btnCheckout.addActionListener(_ -> {
			if (info.getCurOrder().getItems().isEmpty())
				showInfoDialogue("Your cart is empty, you can't check out!", "Okay", "No items to check out with");
			else
				showScreen(Screen.CHECK_OUT);
		});
	}
	
	// This dropdown allows the user to delete or modify items in their order
	public void showDropdownPopup() {
		// Custom font
		Font customFont = info.getTextFont();
		
		if (info.getCurOrder().getItems().isEmpty()) {
			// Display info dialogue if the order is empty
			JLabel emptyMessage = new JLabel("No items in the order to modify.");
			emptyMessage.setFont(customFont);
			JOptionPane.showMessageDialog(
					null,
					emptyMessage,
					"Cart is empty",
					JOptionPane.INFORMATION_MESSAGE
			);
			return;
		}
		
		// Data for the dropdown (JComboBox)
		ArrayList<String> itemDescriptions = new ArrayList<>();
		for (MenuItemWithCount item : info.getCurOrder().getItems()) {
			if (item != null) {
				itemDescriptions.add(item.shortToString());
			}
		}
		
		// Create the combo box (dropdown)
		JComboBox<String> comboBox = new JComboBox<>(itemDescriptions.toArray(new String[0]));
		comboBox.setFont(customFont);
		
		// Create a message in the popup
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Select item to modify:");
		label.setFont(customFont);
		panel.add(label);
		panel.add(comboBox);
		
		// Create the dialog popup to show the dropdown
		int option = JOptionPane.showConfirmDialog(
				null,
				panel,
				"Select Option",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE
		);
		
		// Handle the selection after the popup closes
		if (option == JOptionPane.OK_OPTION) {
			String selectedOption = (String) comboBox.getSelectedItem();
			if (selectedOption == null) {
				JLabel errorMessage = new JLabel("No item selected.");
				errorMessage.setFont(customFont);
				JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			MenuItemWithCount toModify = null;
			for (MenuItemWithCount item : info.getCurOrder().getItems()) {
				if (item.shortToString().equals(selectedOption)) {
					toModify = item;
					break;
				}
			}
			
			if (toModify != null) {
				showModificationPopup(toModify, customFont);
			} else {
				JLabel notFoundMessage = new JLabel("Item not found.");
				notFoundMessage.setFont(customFont);
				JOptionPane.showMessageDialog(null, notFoundMessage, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		// Update the order text area and total cost
		txtAreaOrder.setText(info.getCurOrder().toString());
		updateTotalCostFields();
	}
	
	// Show a popup to modify the selected item
	private void showModificationPopup(MenuItemWithCount item, Font customFont) {
		// Create a panel and label for the modification popup
		JPanel modificationPanel = new JPanel();
		JLabel modificationLabel = new JLabel("Modify quantity of: " + item.shortToString());
		modificationLabel.setFont(customFont);
		modificationPanel.add(modificationLabel);
		
		// Create a custom font for the options dialog
		String[] options = {"Increase", "Decrease", "Set New", "Remove", "Cancel"};
		for (int i = 0; i < options.length; i++) {
			options[i] = "<html><span style='font-family:" + customFont.getFontName() + "; font-size:" + customFont.getSize() + "pt;'>" + options[i] + "</span></html>";
		}
		
		// Display the options dialog with custom font
		int result = JOptionPane.showOptionDialog(
				null,
				modificationPanel,
				"Modify Item",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE,
				null,
				options,
				options[4] // Default to "Cancel"
		);
		
		// Handle the user's selection
		switch (result) {
			case 0: // Increase
				if (item.getItem() instanceof Pizza) {
					if (info.getCurOrder().getNumberPizzasInOrder() >= 10) {
						showWarningDialogue(
								"Cannot add any more pizzas to this order!",
								"Alright...",
								"Max number of pizzas reached");
						break;
					}
				}
				item.increaseCount();
				JLabel increaseMessage = new JLabel("Increased quantity of " + item.shortToString());
				increaseMessage.setFont(customFont);
				JOptionPane.showMessageDialog(
						null,
						increaseMessage,
						"Updated",
						JOptionPane.INFORMATION_MESSAGE
				);
				break;
			
			case 1: // Decrease
				if (item.getCount() > 1) {
					item.decreaseCount();
					JLabel decreaseMessage = new JLabel("Decreased quantity of " + item.shortToString());
					decreaseMessage.setFont(customFont);
					JOptionPane.showMessageDialog(
							null,
							decreaseMessage,
							"Updated",
							JOptionPane.INFORMATION_MESSAGE
					);
				} else {
					JLabel errorMessage = new JLabel("Cannot decrease further. Use the remove button instead.");
					errorMessage.setFont(customFont);
					JOptionPane.showMessageDialog(
							null,
							errorMessage,
							"Error",
							JOptionPane.ERROR_MESSAGE
					);
				}
				break;
			
			case 2: // Set New
				int max = 10;
				if (item.getItem() instanceof Pizza) {
					max = 10 - (info.getCurOrder().getNumberPizzasInOrder() - item.getCount());
				}
				
				// Create a dropdown for selecting the new quantity
				Integer[] quantityOptions = new Integer[max];
				for (int i = 1; i <= max; i++) {
					quantityOptions[i - 1] = i;
				}
				JComboBox<Integer> quantityDropdown = new JComboBox<>(quantityOptions);
				quantityDropdown.setFont(customFont);
				quantityDropdown.setSelectedItem(item.getCount()); // Set the current count as the default
				
				// Panel to display the dropdown
				JPanel setNewPanel = new JPanel();
				JLabel setNewLabel = new JLabel("Select new quantity:");
				setNewLabel.setFont(customFont);
				setNewPanel.add(setNewLabel);
				setNewPanel.add(quantityDropdown);
				
				// Show the dropdown in a dialog
				int setNewResult = JOptionPane.showConfirmDialog(
						null,
						setNewPanel,
						"Set New Quantity",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE
				);
				
				if (setNewResult == JOptionPane.OK_OPTION) {
					Object selectedItem = quantityDropdown.getSelectedItem();
					if (selectedItem == null) {
						JLabel errorMessage = new JLabel("No quantity selected.");
						errorMessage.setFont(customFont);
						JOptionPane.showMessageDialog(
								null,
								errorMessage,
								"Error",
								JOptionPane.ERROR_MESSAGE
						);
					} else {
						int newQuantity = (int) selectedItem;
						item.setCount(newQuantity);
						JLabel setNewMessage = new JLabel("Set new quantity of " + item.shortToString());
						setNewMessage.setFont(customFont);
						JOptionPane.showMessageDialog(
								null,
								setNewMessage,
								"Updated",
								JOptionPane.INFORMATION_MESSAGE
						);
					}
				}
				break;
			
			case 3: // Remove Completely
				info.getCurOrder().removeItem(item.getItem());
				JLabel removeMessage = new JLabel("Removed " + item.shortToString());
				removeMessage.setFont(customFont);
				JOptionPane.showMessageDialog(
						null,
						removeMessage,
						"Removed",
						JOptionPane.INFORMATION_MESSAGE
				);
				break;
			
			default: // Cancel or close dialog
				break;
		}
		
		// Update the order text area and total cost
		txtAreaOrder.setText(info.getCurOrder().toString());
		updateTotalCostFields();
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
	 * Populates screen with current order items
	 */
	
	@Override
	public void onEnterScreen() {
		setUpUserAndOrderInfo(lblHiName, lblCurTotal);

		if (info.getCurPizza() != null) {
			Pizza myPizza = info.getCurPizza();
//			FoodType.setText("Custom Pizza");
			FoodDescription.setText(myPizza.toString());
			SecondDescription.setText("");
		}
		txtAreaOrder.setText(info.getCurOrder().toString());
		txtAreaOrder.setFont(info.getTextFont());
		updateTotalCostFields();
	}

	/**
	 * Initializes custom UI components, such as images for logos.
	 */
	
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
