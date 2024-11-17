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
		
		StartScreen startScreen = new StartScreen((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(startScreen.getScreenPanel(), "StartScreen");
		
		SignUp signUp = new SignUp((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(signUp.getScreenPanel(), "SignUp");
		
		SignIn signIn = new SignIn((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(signIn.getScreenPanel(), "SignIn");
		
		Menu menu = new Menu((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(menu.getScreenPanel(), "Menu");

		PizzaGUI pizza = new PizzaGUI((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(pizza.getScreenPanel(), "PizzaGUI");

		Deals deals = new Deals((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(deals.getScreenPanel(), "Deals");

		CheckOut checkout = new CheckOut((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(checkout.getScreenPanel(), "CheckOut");

		PaymentScreen paymentScreen = new PaymentScreen((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(paymentScreen.getScreenPanel(), "paymentScreen");

		LocationScreen location = new LocationScreen((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(location.getScreenPanel(), "LocationScreen");
		
		ToppingsGUI toppings = new ToppingsGUI((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(toppings.getScreenPanel(), "ToppingsGUI");
		
		MenuForNonUser menuForNonUser = new MenuForNonUser((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(menuForNonUser.getScreenPanel(), "MenuForNonUser");
		

		
		((CardLayout) screenContainer.getLayout()).show(screenContainer, "StartScreen");
	}
}
