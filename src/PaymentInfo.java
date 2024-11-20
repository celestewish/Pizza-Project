import javax.swing.*;
import java.awt.*;

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
			if(!checkPayment()){
				showInfoDialogue("Payment format is incorrect", "Ok", "Incorrect Format");
			}
			else{
				showScreen(Screen.PAYMENT_RECEIPT);
			}

		});

	}
	
	public boolean isValidDate(String date){
		String dateRegex = "^(0[1-9]|1[0-2])/\\d{2}$";
		
		if(!date.matches(dateRegex)){
			return false;
		}
		
		String[] parts = date.split("/");
		int month = Integer.parseInt(parts[0]);
		int year = Integer.parseInt(parts[1]);
		
		if(month<1 || month>12){
			return false;
		}
		
		return year >= 0 && year <= 99;
	}

	public boolean checkCardNumber(){
		boolean checkCard = true;
		for(char c :cardNumberInput.getText().toCharArray()){
			if(!Character.isDigit(c) || c!=' '){
				checkCard = false;
				break;
			}
		}
		System.out.println(cardNumberInput.getText().length());
		if(cardNumberInput.getText().length() != 19){
			checkCard = false;
		}

		System.out.println(checkCard);

		return checkCard;
	}

	public boolean checkCVV(){
		boolean checkCVVInput = true;
		for(char c : CVV.getText().toCharArray()){
			if(!Character.isDigit(c)){
				checkCVVInput = false;
				break;
			}
		}

		if(CVV.getText().length() !=3){
			checkCVVInput = false;
		}
		System.out.println(checkCVVInput);

		return checkCVVInput;
	}

	public boolean checkZipCode(){
		boolean checkZip = true;
		for(char c : zipCodeInput.getText().toCharArray()){
			if(!Character.isDigit(c)){
				checkZip = false;
				break;
			}
		}

		if (zipCodeInput.getText().length()!=6) {
			checkZip = false;

		}
		System.out.println(checkZip);

		return checkZip;
	}


	public boolean checkPayment(){
		return checkCardNumber() && isValidDate(expDateInput.getText()) && checkCVV() && checkZipCode();
	}
	
	@Override
	public boolean onAttemptLeaveScreen(Screen destinationScreen) {
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

