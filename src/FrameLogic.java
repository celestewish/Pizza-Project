import javax.swing.*;
import java.awt.*;

public class FrameLogic extends JFrame{
	private JPanel screenContainer;
	private JPanel login;
	private JButton btnLogin;
	private JPanel sign_in;
	private JButton btnReturn;
	private JButton btnHome_s;
	
	public FrameLogic() {
		setContentPane(screenContainer);
		setTitle("Mom and Pop's Shop");
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		screenContainer.setLayout(new CardLayout());
		
		StartScreen startScreen = new StartScreen((CardLayout)screenContainer.getLayout(), screenContainer);
		screenContainer.add(startScreen.getScreenPanel(), "StartScreen");
		
		SignUp signUp = new SignUp((CardLayout)screenContainer.getLayout(), screenContainer);
		screenContainer.add(signUp.getScreenPanel(), "SignUp");
		
		SignIn signIn = new SignIn((CardLayout)screenContainer.getLayout(), screenContainer );
		screenContainer.add(signIn.getScreenPanel(), "SignIn");
		
		MenuGUI menu = new MenuGUI((CardLayout)screenContainer.getLayout(), screenContainer);
		screenContainer.add(menu.getScreenPanel(), "MenuGUI");

		CheckOut checkOut = new CheckOut((CardLayout)screenContainer.getLayout(), screenContainer);
		screenContainer.add(menu.getScreenPanel(), "CheckOut");
		
		((CardLayout) screenContainer.getLayout()).show(screenContainer, "StartScreen");
	}
}
