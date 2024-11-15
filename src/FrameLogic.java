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
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		screenContainer.setLayout(new CardLayout());
		SignUp signUp = new SignUp((CardLayout)screenContainer.getLayout(), screenContainer);
		screenContainer.add(signUp.getScreenPanel(), "SignUp");
		((CardLayout) screenContainer.getLayout()).show(screenContainer, "SignUp");
		
		
		screenContainer.add(login, "Login");
		screenContainer.add(sign_in, "SignIn");
		
		btnLogin.addActionListener(_ -> {
			CardLayout layout = (CardLayout) screenContainer.getLayout();
			layout.show(screenContainer, "SignIn");
		});
		btnReturn.addActionListener(_ -> {
			CardLayout layout = (CardLayout) screenContainer.getLayout();
			layout.show(screenContainer, "Login");
		});
		btnHome_s.addActionListener(_ -> {
			CardLayout layout = (CardLayout) screenContainer.getLayout();
			layout.show(screenContainer, "Login");
		});
	}
}
