import javax.swing.*;
import java.awt.*;



public class CheckOut extends CardScreen {
    private JPanel pnlCheckOut;
    
    private JTextField firstNameDis;
    private JTextField lastNameDis;
    private JTextField textField3;
    private JTextField textField4;
    private JButton editButton;
    private JButton dealsButton;
    private JButton proceedToPaymentButton;
    private JButton btnHome;
    private JButton btnMenu;
    private JButton btnDeals;
    private JButton btnLocations;
    private JButton btnSignOut;
    private JButton btnCart;
    private JLabel lblHiName;
    private JLabel lblCurTotal;
    private JPanel pnlCartLogo;
    private JPanel pnlLogo;
    
    public CheckOut(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlCheckOut);
        info.registerScreenName(Screen.CHECK_OUT, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);

        //array to store customer name to display, index 0 is  first name,
        //index 1 is last name
        firstNameDis.setText(null);
        firstNameDis.setText(null);
        lastNameDis.setText(null);
        textField4.setText(null);
        textField3.setText(null);

        editButton.addActionListener((e) -> {
            String[] name = info.getName().split(" ");
            String phoneNumberInput = null;

            String firstNameInput = firstNameDis.getText();
            String lastNameInput = lastNameDis.getText();
            String emailInput = textField3.getText();
            try{
                phoneNumberInput = textField4.getText();

            }
            catch(NumberFormatException ex){
                JFrame frame = new JFrame("Error");
                frame.setSize(200,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(frame, "Invalid input! PLease enter a valid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(true);
            }

            if(name.length ==3){
                name[0] = firstNameInput;
                name[2] = lastNameInput;
            }
            else{
                name[0] = firstNameInput;
                name[1] = lastNameInput;
            }




            name[0] = firstNameInput;
            name[1] = lastNameInput;
            Boolean phoneAdd = info.addPhone(phoneNumberInput);
            info.setEmail(emailInput);
            info.setName(name[0] + " " + name[1]);

            if(name.length == 3){
                firstNameDis.setText(name[0]);
                lastNameDis.setText(name[2]);
            }
            else{
                firstNameDis.setText(name[0]);
                lastNameDis.setText(name[1]);
            }
            textField4.setText(info.getPhoneAtIndex0());
            textField3.setText(info.getEmail());
        });
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
        setUpUserAndOrderInfo(lblHiName, lblCurTotal);
    
    }
    
    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
