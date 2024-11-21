import javax.swing.*;
import java.awt.*;



public class CheckOut extends CardScreen {
    private JPanel pnlCheckOut;
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
    private JScrollPane showOrder;
    private JTextArea orderShown;
    private JLabel lblCheckName;
    private JLabel lblCheckEmail;
    private JLabel lblCheckPhone;
    private JLabel lblCheckOut;

    public CheckOut(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlCheckOut);
        info.registerScreenName(Screen.CHECK_OUT, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        //sets up the buttons ont the top of the screen
        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);


        //proceeds to the receipt
        proceedToPaymentButton.addActionListener(_ -> showScreen(Screen.PAYMENT_INFO));


    }
    
    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        orderShown.setText(null);//clears the order shown in the checkout screen when leaving the screen
        return true;
    }
    
    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        return toScreen;
    }
    
    @Override
    public void onEnterScreen() {
        //sets up the name of the user on the top tight
        //sets up the cart total on the top right
        setUpUserAndOrderInfo(lblHiName, lblCurTotal);
        //sets up the name, email, and phone for the order
        setUpForCheckOut(lblCheckName, lblCheckEmail, lblCheckPhone);

        lblCheckOut.setFont(info.getPaymentFont());

        //prints out the current order, if the order changes so will the JTextArea
        for(int i=0; i<info.getCurOrder().getItems().size(); i++){
            orderShown.append(info.getCurOrder().getItems().get(i).toString() + "\n");
            orderShown.setFont(info.getOptionsFont());
        }
    
    }
    //creates the images seen on the screen
    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
