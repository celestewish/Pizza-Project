import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The PaymentInfo class represents the payment information screen in the application.
 * It handles user input validation for payment details and navigation between screens.
 */

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
	private JTextField cardNumberInput;
	private JTextField CVV;
	private JTextField expDateInput;
	private JTextField zipCodeInput;
	private JButton btnMakePayment;
	private JTextArea txtAreaTotal;
	private JLabel deliveryDetails;
	private JLabel securePayment;
	private JButton btnReturn;
	
	/**
	 * Constructor for the PaymentInfo screen.
	 *
	 * @param screenLayoutController The CardLayout controller for switching screens.
	 * @param screenContainer        The container holding all screens.
	 * @param panelName              The unique name for this panel.
	 */
	public PaymentInfo(CardLayout screenLayoutController, JPanel screenContainer, String panelName){
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlPaymentInfo);
		info.registerScreenName(Screen.PAYMENT_INFO, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);

		txtAreaCustAddress.setFocusable(false);
		txtAreaTotal.setFocusable(false);
		txtAreaStoreAddress.setFocusable(false);
		
		btnReturn.addActionListener(_ -> showScreen(Screen.RETURN));
		
		//submits payment
		btnMakePayment.addActionListener(_ -> {
			if(!checkName()){
				//shows a popup if the format for the name is not correct
				showInfoDialogue("Customer name must only contain letters. Please avoid any special characters or numbers", "Ok", "Incorrect Format");
			}
			else if(!checkCardNumber()){
				//shows a popup if the format for the card number is not correct
				showInfoDialogue("Card Number can only contain numbers. Please avoid special characters and letters. Card number must be in xxxx xxxx xxxx xxxx format", "Ok", "Incorrect format");
			}
			else if(!checkCVV()){
				//shows a popup if the format for the CVV is not correct
				showInfoDialogue("CVV can only contain numbers. Please avoid letters and special characters", "Ok", "Incorrect format");
			}
			else if(!isValidDate(expDateInput.getText())){
				//shows a popup if the expiration date is incorrect
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
	/**
	 * Validates the expiration date format and range.
	 *
	 * @param date The expiration date input by the user.
	 * @return True if the date is valid, false otherwise.
	 */
	public boolean isValidDate(String date){
		boolean checkMonth;
		boolean checkYear;
		String dateRegex = "^(0[1-9]|1[0-2])/\\d{2}$";
		//makes sure that the date enter does not contain letters or special characters
		if(!date.matches(dateRegex)){
			return false;
		}

		//to make sure it's within the time range
		String[] parts = date.split("/");
		int month = Integer.parseInt(parts[0]);
		int year = Integer.parseInt(parts[1]);

		//checks if the date is within the set months and the current year to 2099
        checkMonth = month >= 1 && month <= 12;
        checkYear = year >= 24 && year <= 99;

		//checks if the card did not expire in the current month
		if(year ==24 && month <=11){
			checkMonth = false;
			checkYear = false;
		}

		return checkMonth && checkYear;
	}

	/**
	 * Validates the credit card number format.
	 *
	 * @return True if the card number is valid, false otherwise.
	 */
	public boolean checkCardNumber(){
		boolean checkCard = true;
		for(char c :cardNumberInput.getText().toCharArray()){
			if(!Character.isDigit(c) && c!=' '){
				checkCard = false;
				break;
			}
		}
		if(cardNumberInput.getText().length() != 19){
			checkCard = false;
		}

		return checkCard;
	}

	/**
	 * Validates the CVV format.
	 *
	 * @return True if the CVV is valid, false otherwise.
	 */
	public boolean checkCVV(){
		boolean checkCVVInput = true;
		Pattern digitP = Pattern.compile("[0-9]");
		Matcher checkDigit =digitP.matcher(CVV.getText());

		if(!checkDigit.find()){
			checkCVVInput = false;
		}

		if(CVV.getText().length() !=3){
			checkCVVInput = false;
		}

		return checkCVVInput;
	}

	/**
	 * Validates the zip code format.
	 *
	 * @return True if the zip code is valid, false otherwise.
	 */
	public boolean checkZipCode(){
		boolean checkZip;
		Pattern digitP = Pattern.compile("[0-9]");
		Matcher checkDigit = digitP.matcher(zipCodeInput.getText());
		checkZip = checkDigit.find();

		if(zipCodeInput.getText().length() !=5){
			checkZip = false;
		}

		return checkZip;
	}

	/**
	 * Validates the cardholder name format.
	 *
	 * @return True if the name is valid, false otherwise.
	 */
	public boolean checkName(){
		Pattern specialC = Pattern.compile("[!@#$%&*()_+=|<>?\\[\\]~-]");
		Pattern digitP = Pattern.compile("[0-9]");
		Matcher m = specialC.matcher(cardHoldName.getText());
		Matcher digitM = digitP.matcher(cardHoldName.getText());
		boolean checkNameBool = m.find() && digitM.find();
		return !checkNameBool;
	}



	/**
	 * Clears all input fields when attempting to leave the screen.
	 *
	 * @param destinationScreen The screen the user is navigating to.
	 * @return True to allow navigation, false otherwise.
	 */
	@Override
	public boolean onAttemptLeaveScreen(Screen destinationScreen) {
		cardNumberInput.setText(null);
		CVV.setText(null);
		expDateInput.setText(null);
		zipCodeInput.setText(null);
		cardHoldName.setText(null);
		return true;
	}

	/**
	 * Sets up the screen when entering it.
	 */
	@Override
	public Screen onAttemptEnterScreen(Screen toScreen) {

		return toScreen;
	}

	/**
	 * Sets up the screen when entering it.
     */
	@Override
	public void onEnterScreen() {
		setUpUserAndOrderInfo(lblHiName, lblCurTotal);
		setUpForPaymentInfo(txtAreaTotal, txtAreaCustAddress);
		txtAreaCustAddress.setFont(info.getTextFont());
		txtAreaStoreAddress.setText("680 Arnston Rd, Suite 161 Marietta, GA 30060");
		txtAreaStoreAddress.setFont(info.getTextFont());
		deliveryDetails.setFont(info.getPaymentFont());
		securePayment.setFont(info.getPaymentFont());
	}

	/**
	 * Sets up the images found on the screen.
	 */
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}

