import javax.swing.*;
import java.awt.*;

public class FrameLogic extends JFrame {
	private JPanel screenContainer;
	
	public FrameLogic() {
		setContentPane(screenContainer);
		setTitle("Mom and Pop's Shop");
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Create a new instance of ProgramInfo
		ProgramInfo info = new ProgramInfo();
		
		
		screenContainer.setLayout(new CardLayout());
		
		Login login = new Login((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"Login");
		
		SignUp signUp = new SignUp((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"SignUp");
		
		SignIn signIn = new SignIn((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"SignIn");
		
		Menu menu = new Menu((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"Menu");

		PizzaGUI createPizza = new PizzaGUI((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"CreatePizza");

		Deals deals = new Deals((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"Deals");

		CheckOut checkout = new CheckOut((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"CheckOut");

		PaymentInfo paymentScreen = new PaymentInfo((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"PaymentInfo");

		Locations location = new Locations((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"Locations");
		
		ToppingsGUI toppings = new ToppingsGUI((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"Toppings");
		
		MenuForNonUser menuForNonUser = new MenuForNonUser((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"MenuForNonUser");
		
		Payment_Receipt paymentReceipt = new Payment_Receipt((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"PaymentReceipt");
		
		Cart cart = new Cart((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"Cart");
		
		LocationsForUser locationsForUser = new LocationsForUser((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"LocationsForUser");
		
		DealsForUser dealsForUser = new DealsForUser((CardLayout)screenContainer.getLayout(), screenContainer, info,
				"DealsForUser");
		
		((CardLayout) screenContainer.getLayout()).show(screenContainer, login.getPanelName());
		info.setCurScreen(Screen.LOGIN);
	}
}
