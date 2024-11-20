import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public Cart (CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlCart);
		info.registerScreenName(Screen.CART, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());

		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
		returnButton.addActionListener(ActionListener_ -> showScreen(Screen.MENU));
		btnCheckout.addActionListener(ActionListener_ -> showScreen(Screen.CHECK_OUT));
	}
	
	@Override
	public boolean onAttemptLeaveScreen(Screen destinationScreen) {
		return true;
	}
	
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		return toScreen;
	}
	
	@Override
	public void onEnterScreen() {
		setUpUserAndOrderInfo(lblHiName, lblCurTotal);

		Font textFont = new Font("Times New Roman", Font.BOLD, 24);
		Font optionsFont = new Font("Arial", Font.PLAIN, 20);
		if (info.getCurPizza() != null) {
			Pizza myPizza = info.getCurPizza();
			FoodType.setText("Custom Pizza");
			FoodDescription.setText(myPizza.toString());
			SecondDescription.setText("");
		}



		editButton.addActionListener(_ -> {showScreen(Screen.CREATE_PIZZA);});
		removeButton.addActionListener(_ -> {
			info.setCurPizza(null);
		});
		for (int i = 0; i < info.getCurOrder().getItems().size(); i++) {
			JLabel lblItem = new JLabel(info.getCurOrder().getItems().get(i).toString());
			lblItem.setFont(optionsFont);
			JButton newEditButton = new JButton("Edit");
			JButton newRemoveButton = new JButton("Remove");
			orderPanel.add(lblItem);
			orderPanel.add(newEditButton);
			orderPanel.add(newRemoveButton);
		}
		costNumber.setText(String.valueOf(info.getCurOrder().calcTotalOrderCost()));
	}
	
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
