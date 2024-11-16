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
		
		btnHome.addActionListener(_ -> {
			showScreen("StartScreen");
			resetFields();
		});
		
		btnMenu.addActionListener(_ -> {
			showScreen("MenuGUI");
			resetFields();
		});
		
		btnSignIn_SignUp.addActionListener(_ -> {
			showScreen("SignIn");
			resetFields();
		});
		
		btnSignUp.addActionListener(_ -> {
			// Call a method that will show an error if there's an error and halt the execution if an error was thrown
			for (JTextField f : requiredTextFields) {
				if (isTextEmpty(f, "Please complete all required fields", "", 0))
					return;
			}
			// Call a method that will show an error if there's an error and halt the execution if an error was thrown
			if (isPasswordInvalid(txtPassword))
				return;
			// Call a method that will show an error if there's an error and halt the execution if an error was thrown
			if (isPhoneInvalid(txtPhoneNumber, "Please enter a valid phone number", "", 0))
				return;
			// Call a method that will show an error if there's an error and halt the execution if an error was thrown
			for (JComboBox<Integer> b : requiredComboBoxes) {
				if (isComboBoxUnselected(b, "Please complete all required fields", "", 0))
					return;
			}
			// Call a method that will show an error if there's an error and halt the execution if an error was thrown
			if (isEmailTaken(txtEmail, "There is already an account with this email", "", 0))
				return;
			
			Customer newCustomer = createNewCustomer();
			info.UserDatabase().storeUser(newCustomer); // add the customer to the database
			info.setCurrentUser(newCustomer); // set the current user and the newly made customer object
			info.setLoggedIn(true);
			// show user a confirmation pop-up window welcoming them to the system
			JOptionPane.showMessageDialog(null,
					"Your account has successfully been created!\n" +
							"Welcome to Mom and Pop's Pizza Shop, " + txtFname.getText() + "!",
					"",
					JOptionPane.INFORMATION_MESSAGE);
			showScreen("MenuGUI"); // take the new user to the menu screen
		});
	}
	
	public JPanel getScreenPanel() {
		return pnlSignUp;
	}
	
	private Customer createNewCustomer() {
		String fullName = txtFname.getText() + " " + txtMname.getText() + " " + txtLname.getText();
		String fullAddress = txtStreet.getText() + " " + txtCity.getText() + " " + txtState.getText() + " " +  txtZIP.getText();
		String password = convertPasswordToString(txtPassword.getPassword());
		// Take all info and create new customer object
		return new Customer(fullName, txtEmail.getText(), password, fullAddress, txtPhoneNumber.getText());
	}
	
	// method to reset all fields in the screen when the user goes to another screen
	public void resetFields() {
	    txtFname.setText("");
	    txtMname.setText("");
	    txtLname.setText("");
		txtPassword.setText("");
		txtEmail.setText("");
		
		showPasswordCheckBox.setSelected(false);
		txtPhoneNumber.setText("");
		
		txtStreet.setText("");
		txtCity.setText("");
		txtState.setText("");
		txtZIP.setText("");
		
		boxCard.setSelected(false);
		boxCash.setSelected(false);
		
		cboxMonth.setSelectedIndex(0);
		cboxDay.setSelectedIndex(0);
		cboxYear.setSelectedIndex(0);
	}
}
