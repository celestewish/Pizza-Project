import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cart extends CardScreen {

	private JPanel Background;
	private JPanel header;
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnDeals;
	private JButton btnLocations;
	private JPanel Checkout;
	private JButton btnCheckout;
	private JButton btnBack;
	private JButton btnChange;
	private JLabel lblHiCustomerName;
	private JButton btnSignOut;
	private JButton viewCartButton;
	
	public Cart (CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);


		btnHome.addActionListener(e -> showScreen("StartScreen"));
		btnMenu.addActionListener(e -> showScreen("MenuGUI"));
		btnDeals.addActionListener(e -> showScreen("Deals"));
		btnLocations.addActionListener(e -> showScreen("Deals"));
		btnCheckout.addActionListener(e -> showScreen("PaymentScreen"));
		btnChange.addActionListener(e -> showScreen("Locations"));
		btnBack.addActionListener(e -> showScreen("Menu"));
	}
}
