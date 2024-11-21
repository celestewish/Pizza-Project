import javax.swing.*;
import java.awt.*;

/**
 * The Payment_Receipt class represents the checkout screen in the application.
 * It displays the user's order details and order confirmation with total. It navigates
 * back to the home screen.
 */

public class Payment_Receipt extends CardScreen{
    private JPanel pnlPaymentReceipt;

    private JPanel Home;
    private JButton btnHome;
    private JLabel OrderPlace;
    private JLabel ThankYou;
    private JLabel OrderSummary;
    private JLabel Total;
    private JLabel PaymentMethod;
    private JLabel TotalInfo;
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

    /**
     * Constructor for the CheckOut screen.
     *
     * @param screenLayoutController The CardLayout controller for switching screens.
     * @param screenContainer        The container holding all screens.
     * @param panelName              The unique name for this panel.
     */

    public Payment_Receipt(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlPaymentReceipt);
        info.registerScreenName(Screen.PAYMENT_RECEIPT, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());

        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);

    }
    
    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        info.clearCurOrder();
        return true;
    }
    
    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        return toScreen;
    }

    /**
     * Sets up the screen when entering it, including user info, order details, and fonts.
     */
    
    @Override
    public void onEnterScreen() {
        setUpUserAndOrderInfo(lblHiName, lblCurTotal);
        for(int i=0; i<info.getCurOrder().getItems().size(); i++){
            orderShown.append(info.getCurOrder().getItems().get(i).toString() + "\n");
            orderShown.setFont(info.getOptionsFont());
        }

        TotalInfo.setText(String.valueOf(info.getCurOrder().calcTotalOrderCost()));
    }

    /**
     * Initializes custom UI components, such as images for logos.
     */
    
    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
