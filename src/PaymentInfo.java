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
	private JTextField firstLastTextField;
	private JButton submitPaymentButton;
	private JTextField cardNumberTextField;
	private JTextField CVVTextField;
	private JTextField expDateTextField;
	private JTextField zipCodeTextField;
	private JLabel lblHiName;
	private JLabel lblCurTotal;
	private JPanel pnlCartLogo;
	private JPanel pnlLogo;
	
	public PaymentInfo(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName){
		super(screenLayoutController, screenContainer, info, panelName);
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
			String cardNameInput = firstLastTextField.getText();
			String cardNumberInput = null;
			String expDateInput = null;
			String zipCodeInput = null;
			int cvvInput = 0;
			//could not implement in time
            /*
            try{
                cardNumberInput = cardNumberTextField.getText();
                if(!cardNumberInput.matches("\\d+" )){
                    throw new IllegalArgumentException("Valid characters only");
                }

            }
            catch(IllegalArgumentException ex){
                JFrame frame = new JFrame("Error");
                frame.setSize(200,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a card number", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(true);
            }
            try{

                cvvInput = Integer.parseInt(CVVTextField.getText());
                if((!String.valueOf(cvvInput).matches("\\d+" )) || String.valueOf(cvvInput).length() <3 ){
                    throw new IllegalArgumentException("Too many characters or illegal characters");
                }

            }
            catch(IllegalArgumentException ex){
                JFrame frame = new JFrame("Error");
                frame.setSize(200,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(frame, "Invalid input! PLease enter the correct cvv", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(true);
            }

            try{
                expDateInput = expDateTextField.getText();
                if(!isValidDate(expDateInput)){
                    throw new IllegalArgumentException("Not a valid date.");
                }
            }
            catch(IllegalArgumentException ex){
                JFrame frame = new JFrame("Error");
                frame.setSize(200,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(frame, "Invalid input! PLease enter a correct date", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(true);
            }
            try{
                zipCodeInput = zipCodeTextField.getText();
                if((zipCodeInput.length() != 6) && !zipCodeInput.matches("\\d+")){
                   throw new IllegalArgumentException("Invalid zip code.");
               }
            }
            catch (IllegalArgumentException ex){
                JFrame frame = new JFrame("Error");
                frame.setSize(200,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(frame, "Invalid input! PLease enter a correct zip code.", "Error", JOptionPane.ERROR_MESSAGE);
            frame.setVisible(true);
            }
             */
			
			showScreen(Screen.PAYMENT_RECEIPT);
			
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
	
	@Override
	public boolean onAttemptLeaveScreen(ProgramInfo info) {
		return true;
	}
	
	@Override
	public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
		return toScreen;
	}
	
	@Override
	public void onEnterScreen(ProgramInfo info) {
	
	}
	
	private void createUIComponents() {
		pnlCartLogo = new ImagePanel("src/main/resources/images/cart.png");
		pnlLogo = new ImagePanel("src/main/resources/images/PizzaLogo.png");
	}
}

