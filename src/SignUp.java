import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SignUp extends CardScreen {
	private JPanel pnlSignUp;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnSignIn_SignUp;
	
	private JTextField txtFname;
	private JTextField txtMname;
	private JTextField txtLname;
	private JPasswordField txtPassword;
	private JTextField txtEmail;
	
	private JCheckBox showPasswordCheckBox;
	private JTextField txtPhoneNumber;
	
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtZIP;
	
	private JCheckBox boxCard;
	private JCheckBox boxCash;
	
	private JComboBox<Integer> cboxMonth;
	private JComboBox<Integer> cboxDay;
	private JComboBox<Integer> cboxYear;
	
	private JButton btnSignUp;
	private JButton btnReturn;
	private JButton btnDeals;
	private JButton btnLocations;
	
	
	public SignUp(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);
		
		ArrayList<JTextField> requiredTextFields = new ArrayList<>();
		requiredTextFields.add(txtFname);
		requiredTextFields.add(txtMname);
		requiredTextFields.add(txtLname);
		requiredTextFields.add(txtEmail);
		requiredTextFields.add(txtStreet);
		requiredTextFields.add(txtCity);
		requiredTextFields.add(txtState);
		requiredTextFields.add(txtZIP);
		
		ArrayList<JComboBox<Integer>> requiredComboBoxes = new ArrayList<>();
		requiredComboBoxes.add(cboxMonth);
		requiredComboBoxes.add(cboxDay);
		requiredComboBoxes.add(cboxYear);
		
		btnReturn.addActionListener(_ -> showScreen("StartScreen"));
		
		showPasswordCheckBox.addActionListener(_ -> {
			if (showPasswordCheckBox.isSelected())
				txtPassword.setEchoChar((char)0);
			else
				txtPassword.setEchoChar('*');
		});
		
		btnHome.addActionListener(_ -> showScreen("StartScreen"));
		
		btnMenu.addActionListener(_ -> showScreen("MenuGUI"));
		
		btnSignIn_SignUp.addActionListener(_ -> showScreen("SignIn"));
		
		btnSignUp.addActionListener(_ -> {
			for (JTextField f : requiredTextFields) {
				if (isTextEmpty(f, "Please complete all required fields", "", 0))
					return;
			}
			
			if (isPasswordInvalid(txtPassword))
				return;
			
			if (isPhoneInvalid(txtPhoneNumber, "Please enter a valid phone number", "", 0))
				return;
			
			for (JComboBox<Integer> b : requiredComboBoxes) {
				if (isComboBoxUnselected(b, "Please complete all required fields", "", 0))
					return;
			}
			
			if (isEmailTaken(txtEmail, "There is already an account with this email", "", 0))
				return;
			
			String fullName = txtFname.getText() + " " + txtMname.getText() + " " + txtLname.getText();
			String fullAddress = txtStreet.getText() + " " + txtCity.getText() + " " + txtState.getText() + " " +  txtZIP.getText();
			StringBuilder password = new StringBuilder();
			for (char c : txtPassword.getPassword()) {
				password.append(c);
			}
			info.UserDatabase().storeUser(new Customer(fullName, txtEmail.getText(), password.toString(), fullAddress, txtPhoneNumber.getText()));
			JOptionPane.showMessageDialog(null,
					"Your account has successfully been created!\n" +
							"Welcome to Mom and Pop's Pizza Shop, " + txtFname.getText() + "!",
					"",
					JOptionPane.INFORMATION_MESSAGE);
			showScreen("MenuGUI");
		});
	}
	
	public JPanel getScreenPanel() {
		return pnlSignUp;
	}
}
