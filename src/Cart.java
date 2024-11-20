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
    private JButton returnButton;
	private JLabel FoodType;
	private JLabel FoodDescription;
	private JButton editButton;
	private JButton removeButton;
	private JLabel SecondDescription;
	private JPanel orderPanel;
	private JLabel costNumber;

	public Cart (CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlCart);
		info.registerScreenName(Screen.CART, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());

		Font textFont = new Font("Times New Roman", Font.BOLD, 24);
		Font optionsFont = new Font("Arial", Font.PLAIN, 20);

		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
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
	
	@Override
	public boolean onAttemptLeaveScreen() {
		return false;
	}
	
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {
		return null;
	}
	
	@Override
	public void onEnterScreen() {
	
	}
	
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}
