import javax.swing.*;
import java.awt.*;

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
		
		btnReturn.addActionListener(_ -> {
			showScreen(Screen.RETURN);
			resetScreen();
		});
		
		btnHome.addActionListener(_ -> {
			showScreen(Screen.LOGIN);
			resetScreen();
		});
		
		btnMenu.addActionListener(_ -> {
			showScreen(Screen.MENU);
			resetScreen();
		});
		
		btnDeals.addActionListener(_ -> {
			showScreen(Screen.DEALS);
			resetScreen();
		});
		
		btnSignUp_SignIn.addActionListener(_ -> {
			showScreen(Screen.SIGN_IN);
			resetScreen();
		});
		
		btnLocations.addActionListener(_ -> {
			showScreen(Screen.LOCATIONS);
			resetScreen();
		});
		
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
	public Screen onAttemptLeaveScreen(ProgramInfo info, Screen fromScreen) {
		return fromScreen;
	}
	
	@Override
	public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
		if (info.getCurScreen() == Screen.SIGN_UP)
			return Screen.SIGN_IN;
		return toScreen;
	}
}
