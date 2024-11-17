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
		screenContainer.add(menu.getScreenPanel(), "MenuGUI");

		PizzaGUI pizza = new PizzaGUI((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(pizza.getScreenPanel(), "PizzaGUI");

		Deals deals = new Deals((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(deals.getScreenPanel(), "Deals");

		CheckOut checkout = new CheckOut((CardLayout)screenContainer.getLayout(), screenContainer, info);
		screenContainer.add(checkout.getScreenPanel(), "CheckOut");

		Locations locations = new Locations((CardLayout)screenContainer.getLayout(), screenContainer, info);
		//screenContainer.add(locations.getScreenPanel(), "Locations");
		
		
		((CardLayout) screenContainer.getLayout()).show(screenContainer, "StartScreen");
	}
}
