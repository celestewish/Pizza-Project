import javax.swing.*;
import java.awt.*;

public class SignUp extends CardScreen {
	private JPanel pnlSignUp;
	private JPanel pnlLogo;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnLocations;
	private JButton btnDeals;
	private JButton btnSignUp_SignIn;
	
	private JTextField txtFname;
	private JTextField txtMname;
	private JTextField txtLname;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	
	private JCheckBox chbxShowPassword;
	private JLabel lblEmailTaken;
	
	private JTextField txtStreet;
	private JTextField txtCity;
	private JComboBox<?> cobxState;
	private JTextField txtZIP;
	
	private JTextField txtPhoneNumber;
	
	private JComboBox<?> cobxMonth;
	private JComboBox<?> cobxDay;
	private JComboBox<?> cobxYear;
	
	private JCheckBox chbxCard;
	private JCheckBox chbxCash;
	
	private JButton btnSignUp;
	private JButton btnReturn;
	
	public SignUp(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
		super(screenLayoutController, screenContainer, info, panelName);
		setScreenPanel(pnlSignUp);
		info.registerScreenName(Screen.SIGN_UP, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
		
		lblEmailTaken.setText("");
		
		addJComponent(txtFname);
		addJComponent(txtMname);
		addJComponent(txtLname);
		addJComponent(txtEmail);
		addJComponent(txtStreet);
		addJComponent(txtCity);
		addJComponent(txtZIP);
		addJComponent(cobxState);
		addJComponent(cobxMonth);
		addJComponent(cobxDay);
		addJComponent(cobxYear);
		
		btnSignUp.addActionListener(_ -> {
			if (info.UserDatabase().customerExists(txtEmail.getText())) {
				lblEmailTaken.setText("! There already exists an account with this email");
				return;
			}
			
			for (JComponent jcomp : getComponents()) {
				if (jcomp instanceof JTextField && isTextEmpty(true, (JTextField) jcomp))
					return;
				else if (jcomp instanceof JComboBox && isComboBoxUnselected(true, (JComboBox<?>) jcomp))
					return;
			}
			
			if (isEmailInvalid(txtEmail))
				return;
			
			if (isPasswordInvalid(txtPassword))
				return;
			
			if (isPhoneInvalid(txtPhoneNumber))
				return;
			
			if (isEmailTaken(txtEmail))
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
			
			onSignIn(info, txtEmail.getText());
			showScreen(Screen.MENU);
		});
		
		// Listener to reset a warning label when the text is changed
		txtEmail.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				lblEmailTaken.setText(""); // Clear the label
			}
			
			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				lblEmailTaken.setText(""); // Clear the label
			}
			
			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				// No action needed for plain text fields
			}
		});
		
		chbxShowPassword.addActionListener(_ -> {
			if (chbxShowPassword.isSelected())
				txtPassword.setEchoChar((char)0);
			else
				txtPassword.setEchoChar('*');
		});
	}
	
	@Override
	public boolean onAttemptLeaveScreen(ProgramInfo info) {
		return true;
	}
	
	@Override
	public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
		if (info.getCurScreen() == Screen.SIGN_UP)
			return Screen.SIGN_IN;
		return toScreen;
	}
	
	@Override
	public void onEnterScreen(ProgramInfo info) {
	
	}
	
	private void createUIComponents() {
		pnlLogo = new ImagePanel("src/main/resources/images/PizzaLogo.png");
	}
}
