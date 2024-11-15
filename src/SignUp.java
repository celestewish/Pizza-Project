import javax.swing.*;
import java.awt.*;

public class SignUp extends CardScreen {
	private JPanel pnlSignUp;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnSignIn;
	
	private JTextField txtFname;
	private JTextField txtMname;
	private JTextField txtLname;
	private JPasswordField txtPasword;
	private JTextField txtEmail;
	
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtZIP;
	
	private JTextField txtPhoneNumber;
	
	
	private JCheckBox boxCard;
	private JCheckBox boxCash;
	
	private JComboBox<String> cboxMonth;
	private JComboBox<Integer> cboxDay;
	private JComboBox<Integer> cboxYear;
	
	private JButton btnSignUp;
	private JButton btnReturn;
	
	public SignUp(CardLayout screenLayoutController, JPanel screenContainer) {
		super(screenLayoutController, screenContainer);
		
		btnReturn.addActionListener(e -> {
			showScreen("Login");
		});
	}
	
	public JPanel getScreenPanel() {
		return pnlSignUp;
	}
}
