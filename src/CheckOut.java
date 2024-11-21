import javax.swing.*;
import java.awt.*;



public class CheckOut extends CardScreen {
    private JPanel pnlCheckOut;

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
    private JScrollPane showOrder;
    private JTextArea orderShown;
    private JLabel lblCheckName;
    private JLabel lblCheckEmail;
    private JLabel lblCheckPhone;

    public CheckOut(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlCheckOut);
        info.registerScreenName(Screen.CHECK_OUT, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);



        proceedToPaymentButton.addActionListener(_ -> showScreen(Screen.PAYMENT_INFO));


    }
    
    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        orderShown.setText(null);
        return true;
    }
    
    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        return toScreen;
    }
    
    @Override
    public void onEnterScreen() {
        setUpUserAndOrderInfo(lblHiName, lblCurTotal);
        setUpForCheckOut(lblCheckName, lblCheckEmail, lblCheckPhone);

        for(int i=0; i<info.getCurOrder().getItems().size(); i++){
            orderShown.append(info.getCurOrder().getItems().get(i).toString() + "\n");
            orderShown.setFont(info.getOptionsFont());
        }
    
    }
    
    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
