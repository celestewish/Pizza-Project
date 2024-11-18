import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SignUp extends CardScreen {
	private JPanel pnlSignUp;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnLocations;
	private JButton btnDeals;
	private JButton btnSignUp_SignIn;
	
	private JTextField txtFname;
	private JTextField txtMname;
	private JTextField txtLname;
	private JPasswordField txtPassword;
	private JTextField txtEmail;
	
	private JLabel lblEmailTaken;
	
	private JCheckBox chbxShowPassword;
	private JTextField txtPhoneNumber;
	
	private JTextField txtStreet;
	private JTextField txtCity;
	private JComboBox cobxState;
	private JTextField txtZIP;
	
	private JCheckBox chbxCard;
	private JCheckBox chbxCash;
	
	private JComboBox cobxMonth;
	private JComboBox cobxDay;
	private JComboBox cobxYear;
	
	private JButton btnSignUp;
	private JButton btnReturn;
	
	public SignUp(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
		super(screenLayoutController, screenContainer, info, panelName);
		setScreenPanel(pnlSignUp);
		
		ArrayList<JTextField> requiredTextFields = new ArrayList<>();
		requiredTextFields.add(txtFname);
		requiredTextFields.add(txtMname);
		requiredTextFields.add(txtLname);
		requiredTextFields.add(txtEmail);
		requiredTextFields.add(txtStreet);
		requiredTextFields.add(txtCity);
		requiredTextFields.add(txtZIP);
		
		ArrayList<JComboBox<Integer>> requiredComboBoxes = new ArrayList<>();
		requiredComboBoxes.add(cobxState);
		requiredComboBoxes.add(cobxMonth);
		requiredComboBoxes.add(cobxDay);
		requiredComboBoxes.add(cobxYear);
		
		btnReturn.addActionListener(_ -> {
			showScreen("StartScreen");
			resetFields();
		});
		
		chbxShowPassword.addActionListener(_ -> {
			if (chbxShowPassword.isSelected())
				txtPassword.setEchoChar((char)0);
			else
				txtPassword.setEchoChar('*');
		});
		
		btnHome.addActionListener(_ -> {
			showScreen("StartScreen");
			resetFields();
		});
		
		btnMenu.addActionListener(_ -> {
			showScreen("Menu");
			resetFields();
		});
		
		btnDeals.addActionListener(_ -> {
			showScreen("Deals");
		});
		
		btnSignUp_SignIn.addActionListener(_ -> {
			showScreen("SignIn");
			resetFields();
		});
		
		btnLocations.addActionListener(_ -> {
			showScreen("LocationScreen");
			resetFields();
		});
		
		btnSignUp.addActionListener(_ -> {
			for (JTextField f : requiredTextFields) {
				if (isTextEmpty(f, "Please complete all required fields", "", 0))
					return;
			}
			
			if (isEmailInvalid(txtEmail))
				return;
			
			// Call a method that will show an error if there's an error and halt the execution if an error was thrown
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
			String fullAddress = txtStreet.getText() + " " + txtCity.getText() + " " + cobxState.getSelectedItem() + " " +  txtZIP.getText();
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
			showScreen("Menu");
		});
	}
	
	// method to reset all fields in the screen when the user goes to another screen
	public void resetFields() {
		txtFname.setText("");
		txtMname.setText("");
		txtLname.setText("");
		txtPassword.setText("");
		txtEmail.setText("");
		
		chbxShowPassword.setSelected(false);
		txtPhoneNumber.setText("");
		
		txtStreet.setText("");
		txtCity.setText("");
		txtZIP.setText("");
		
		chbxCard.setSelected(false);
		chbxCash.setSelected(false);
		
		cobxState.setSelectedIndex(0);
		cobxMonth.setSelectedIndex(0);
		cobxDay.setSelectedIndex(0);
		cobxYear.setSelectedIndex(0);
	}
}
