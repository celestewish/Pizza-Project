import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.text.MaskFormatter;

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
	
	private JFormattedTextField ftxtPhoneNumber;
	
	private JCheckBox boxCard;
	private JCheckBox boxCash;
	
	private JComboBox<String> cboxMonth;
	private JComboBox<Integer> cboxDay;
	private JComboBox<Integer> cboxYear;
	
	private JButton btnSignUp;
	private JButton btnReturn;
	
	
	public SignUp(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		super(screenLayoutController, screenContainer, info);
		
		createUIComponents();
		
		ArrayList<JTextField> requiredTextFields = new ArrayList<>();
		requiredTextFields.add(txtFname);
		requiredTextFields.add(txtMname);
		requiredTextFields.add(txtLname);
		requiredTextFields.add(txtEmail);
		requiredTextFields.add(txtStreet);
		requiredTextFields.add(txtCity);
		requiredTextFields.add(txtState);
		requiredTextFields.add(txtZIP);
		
		ArrayList<JComboBox> requiredComboBoxes = new ArrayList<>();
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
		
		btnSignUp.addActionListener(_ -> {
			for (JTextField f : requiredTextFields) {
				if (isTextEmpty(f, "Please complete all required fields", "", 0))
					return;
			}
			if (isFTextEmpty(ftxtPhoneNumber, "Please enter a valid phone number", "", 0))
				return;
			for (JComboBox b : requiredComboBoxes) {
				if (isComboBoxUnselected(b, "Please complete all required fields", "", 0))
					return;
			}
			showScreen("MenuGUI");
		});
	}
	
	public JPanel getScreenPanel() {
		return pnlSignUp;
	}
	
	private void createUIComponents() {
		try {
			MaskFormatter phoneFormatter = new MaskFormatter("(###) ###-####");
			phoneFormatter.setPlaceholderCharacter('_');
			ftxtPhoneNumber = new JFormattedTextField(phoneFormatter);
			ftxtPhoneNumber.setColumns(14);
		} catch (ParseException _) { }
	}
}
