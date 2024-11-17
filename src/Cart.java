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
	private JButton btnSignIn_SignUp;
	private JPanel Checkout;

	public Cart (CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);


		btnHome.addActionListener(e -> showScreen("StartScreen"));
		btnMenu.addActionListener(e -> showScreen("MenuGUI"));
		btnDeals.addActionListener(e -> showScreen("Deals"));
		btnLocations.addActionListener(e -> showScreen("Deals"));
	}
	
}
