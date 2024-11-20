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

	public PaymentInfo(CardLayout screenLayoutController, JPanel screenContainer, String panelName){
		super(screenLayoutController, screenContainer, panelName);
		setScreenPanel(pnlPaymentInfo);
		info.registerScreenName(Screen.PAYMENT_INFO, this);
		screenContainer.add(this.getScreenPanel(), this.getPanelName());
		
		setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
		
		//submits payment
		submitPaymentButton.addActionListener((e) -> {
			String[] name = info.getName().split(" ");
			String[] custAddress = info.getAddress().split(" ");
			streetName.setText(custAddress[0] + " "  + custAddress[1] + " " + custAddress[2]);
			cityStateZip.setText(custAddress[3] + " " + custAddress[4] + " " + custAddress[5]);
			String cardNameInput = cardHoldName.getText();
			String cardNumberInput = null;
			String expDateInput = null;
			String zipCodeInput = null;
			int cvvInput = 0;

			
			showScreen(Screen.PAYMENT_RECEIPT);
			
		});
		submitPaymentButton.addActionListener(ActionListener_ -> showScreen(Screen.CHECK_OUT));
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
	
	}
	
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("cart.png");
		pnlLogo = new ImagePanel("PizzaLogo.png");
	}
}

