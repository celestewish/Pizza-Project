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

	//checks if the CVV is withing the set parameters, ie 322 and not 3205 nor 3d2
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
		System.out.println("Check CVV is: "+ checkCVVInput);

		return checkCVVInput;
	}

	//checks if the zip code is valid, ie has 5 digits and no special characters/letters
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

	//checks if the name does not have special characters or numbers
	public boolean checkName(){
		Pattern specialC = Pattern.compile("[!@#$%&*()_+=|<>?\\[\\]~-]");
		Pattern digitP = Pattern.compile("[0-9]");
		Matcher m = specialC.matcher(cardHoldName.getText());
		Matcher digitM = digitP.matcher(cardHoldName.getText());
		boolean checkNameBool = m.find() && digitM.find();
		System.out.println("Check name is: " + checkNameBool);
		return !checkNameBool;
	}



	//clears the input fields
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

	//sets up the screen when entering it, ie lblHiName will have the current users name.
	@Override
	public void onEnterScreen() {
		setUpUserAndOrderInfo(lblHiName, lblCurTotal);
		setUpForPaymentInfo(txtAreaTotal, txtAreaCustAddress);
		txtAreaTotal.setFont(info.getTotalFont());
		txtAreaCustAddress.setFont(info.getTextFont());
		txtAreaStoreAddress.setText("680 Arnston Rd, Suite 161 Marietta, GA 30060");
		txtAreaStoreAddress.setFont(info.getTextFont());
		deliveryDetails.setFont(info.getPaymentFont());
		securePayment.setFont(info.getPaymentFont());
	}

	//sets up the images found on the screen
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}

