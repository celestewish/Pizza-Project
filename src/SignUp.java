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
	private JPasswordField txtPassword;
	private JTextField txtEmail;
	
	private JCheckBox showPasswordCheckBox;
	
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
	
	public SignUp(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);
		
		btnReturn.addActionListener(_ -> showScreen("StartScreen"));
		
		showPasswordCheckBox.addActionListener(_ -> {
			if (showPasswordCheckBox.isSelected())
				txtPassword.setEchoChar((char)0);
			else
				txtPassword.setEchoChar('*');
		});
		
		btnHome.addActionListener(_ -> showScreen("StartScreen"));
	}
	
	public JPanel getScreenPanel() {
		return pnlSignUp;
	}
}
