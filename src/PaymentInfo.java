import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentInfo extends CardScreen {
	private JPanel pnlPaymentInfo;
	
	private JButton btnHome;
	private JButton btnMenu;
	private JButton btnDeals;
	private JButton btnLocations;
	private JButton btnSignOut;
	private JButton btnCart;
	
	private JLabel streetName;
	private JLabel cityStateZip;
	private JTextField cardHoldName;
	private JButton submitPaymentButton;
	private JLabel lblHiName;
	private JLabel lblCurTotal;
	private JPanel pnlCartLogo;
	private JPanel pnlLogo;
	private JTextArea txtAreaStoreAddress;
	private JTextArea txtAreaCustAddress;
	private JLabel lblStoreAddress;
	private JLabel lblCustAddress;
	private JTextField cardNumberInput;
	private JTextField CVV;
	private JTextField expDateInput;
	private JTextField zipCodeInput;
	private JLabel expDate;
	private JButton makePaymentButton;
	private JTextArea txtAreaTotal;
	private JLabel deliveryDetails;
	private JLabel securePayment;


	public PaymentInfo(CardLayout screenLayoutController, JPanel screenContainer, String panelName){
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlPaymentInfo);
		info.registerScreenName(Screen.PAYMENT_INFO, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
		
		//submits payment
		makePaymentButton.addActionListener(_ -> {
			if(!checkName()){
				showInfoDialogue("Customer name must only contain letters. Please avoid any special characters or numbers", "Ok", "Incorrect Format");
			}
			else if(!checkCardNumber()){
				showInfoDialogue("Card Number can only contain numbers. Please avoid special characters and letters. Card number must be in xxxx xxxx xxxx xxxx format", "Ok", "Incorrect format");
			}
			else if(!checkCVV()){
				showInfoDialogue("CVV can only contain numbers. Please avoid letters and special characters", "Ok", "Incorrect format");
			}
			else if(!isValidDate(expDateInput.getText())){
				showInfoDialogue("Expiration date can only contain numbers. Please enter in MM/YY format", "Ok", "Incorrect Format");
			}
			else if(!checkZipCode()){
				showInfoDialogue("Zip code can only contain numbers. Please avoid letters special characters", "Ok", "Incorrect Format");
			}
			else{
				showScreen(Screen.PAYMENT_RECEIPT);
			}

		});

	}
	
	public boolean isValidDate(String date){
		boolean checkMonth;
		boolean checkYear;
		String dateRegex = "^(0[1-9]|1[0-2])/\\d{2}$";

		if(!date.matches(dateRegex)){
			checkMonth = false;
			checkYear = false;
		}

		
		String[] parts = date.split("/");
		int month = Integer.parseInt(parts[0]);
		int year = Integer.parseInt(parts[1]);

        checkMonth = month >= 1 && month <= 12;

        checkYear = year >= 0 && year <= 99;

		System.out.println(checkMonth + " " + checkYear);

		return checkMonth && checkYear;
	}

	public boolean checkCardNumber(){
		boolean checkCard = true;
		for(char c :cardNumberInput.getText().toCharArray()){
			if(!Character.isDigit(c) && c!=' '){
				checkCard = false;
				break;
			}
		}
		System.out.println(cardNumberInput.getText().length());
		if(cardNumberInput.getText().length() != 19){
			checkCard = false;
		}

		System.out.println("Check card number is: " + checkCard);

		return checkCard;
	}

	public boolean checkCVV(){
		boolean checkCVVInput = true;
		Pattern digitP = Pattern.compile("[0-9]");
		Matcher checkDigit =digitP.matcher(CVV.getText());

		if(CVV.getText().length() !=3){
			checkCVVInput = false;
		}
		System.out.println("Check CVV is: "+ checkCVVInput);

		return checkCVVInput;
	}

	public boolean checkZipCode(){
		boolean checkZip;
		Pattern digitP = Pattern.compile("[0-9]");
		Matcher checkDigit = digitP.matcher(zipCodeInput.getText());
		checkZip = checkDigit.find();

		if(zipCodeInput.getText().length() !=5){
			checkZip = false;
		}

		System.out.println("Check zip is: " + checkZip);

		return checkZip;
	}

	public boolean checkName(){
		Pattern p = Pattern.compile("[!@#$%&*()_+=|<>?\\[\\]~-]");
		Matcher m = p.matcher(cardHoldName.getText());
		boolean checkNameBool = m.find();
		System.out.println("Check name is: " + checkNameBool);
		return !checkNameBool;
	}



	
	@Override
	public boolean onAttemptLeaveScreen(Screen destinationScreen) {
		cardNumberInput.setText(null);
		CVV.setText(null);
		expDateInput.setText(null);
		zipCodeInput.setText(null);
		cardHoldName.setText(null);
		return true;
	}
	
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {

		return toScreen;
	}
	
	@Override
	public void onEnterScreen() {
		setUpForPaymentInfo(txtAreaTotal, txtAreaCustAddress);
		txtAreaTotal.setFont(info.getTotalFont());
		txtAreaCustAddress.setFont(info.getTextFont());
		txtAreaStoreAddress.setText("680 Arnston Rd, Suite 161 Marietta, GA 30060");
		txtAreaStoreAddress.setFont(info.getTextFont());
		deliveryDetails.setFont(info.getPaymentFont());
		securePayment.setFont(info.getPaymentFont());
	}
	
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}

