import javax.swing.*;
import java.awt.*;

/**
 * The FrameLogic class represents the main frame for the application,
 * which contains a variety of panels such as login, sign-up, menu, and more,
 * using a CardLayout for navigation between different screens.
 * This class is responsible for setting up the window and initializing
 * various screens that the user can interact with.
 */
public class FrameLogic extends JFrame {
	private JPanel screenContainer;

	/**
	 * Constructs a new FrameLogic object that sets up the main window
	 * and initializes various screens with CardLayout for navigation.
	 */
	public FrameLogic() {
		setContentPane(screenContainer);
		setTitle("Mom and Pop's Shop");
		setSize(1300, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		UIManager.put("Button.font", new Font("Arial", Font.BOLD, 18));
		
		screenContainer.setLayout(new CardLayout());
		
		Login login = new Login((CardLayout)screenContainer.getLayout(), screenContainer,
				"Login");
		
		SignUp signUp = new SignUp((CardLayout)screenContainer.getLayout(), screenContainer,
				"SignUp");
		
		SignIn signIn = new SignIn((CardLayout)screenContainer.getLayout(), screenContainer,
				"SignIn");
		
		Menu menu = new Menu((CardLayout)screenContainer.getLayout(), screenContainer,
				"Menu");

		PizzaGUI createPizza = new PizzaGUI((CardLayout)screenContainer.getLayout(), screenContainer,
				"CreatePizza");

		Deals deals = new Deals((CardLayout)screenContainer.getLayout(), screenContainer,
				"Deals");

		CheckOut checkout = new CheckOut((CardLayout)screenContainer.getLayout(), screenContainer,
				"CheckOut");

		PaymentInfo paymentScreen = new PaymentInfo((CardLayout)screenContainer.getLayout(), screenContainer,
				"PaymentInfo");

		Locations location = new Locations((CardLayout)screenContainer.getLayout(), screenContainer,
				"Locations");
		
		ToppingsGUI toppings = new ToppingsGUI((CardLayout)screenContainer.getLayout(), screenContainer,
				"Toppings");
		
		MenuForNonUser menuForNonUser = new MenuForNonUser((CardLayout)screenContainer.getLayout(), screenContainer,
				"MenuForNonUser");
		
		Payment_Receipt paymentReceipt = new Payment_Receipt((CardLayout)screenContainer.getLayout(), screenContainer,
				"PaymentReceipt");
		
		Cart cart = new Cart((CardLayout)screenContainer.getLayout(), screenContainer,
				"Cart");
		
		LocationsForUser locationsForUser = new LocationsForUser((CardLayout)screenContainer.getLayout(), screenContainer,
				"LocationsForUser");
		
		DealsForUser dealsForUser = new DealsForUser((CardLayout)screenContainer.getLayout(), screenContainer,
				"DealsForUser");

		// Initially show the login screen
		((CardLayout) screenContainer.getLayout()).show(screenContainer, login.getPanelName());
	}
}
