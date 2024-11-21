import javax.swing.*;
import java.awt.*;


/**
 * The CheckOut class represents the checkout screen in the application.
 * It displays the user's order details, personal information, and allows navigation
 * to the payment information screen.
 */
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
    private JLabel lbllYourInfo;
    private JLabel lblName;
    private JLabel lblEmail;
    private JLabel lblPhone;

    /**
     * Constructor for the CheckOut screen.
     *
     * @param screenLayoutController The CardLayout controller for switching screens.
     * @param screenContainer        The container holding all screens.
     * @param panelName              The unique name for this panel.
     */
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

    /**
     * Clears the order details displayed when leaving the screen.
     *
     * @param destinationScreen The screen the user is navigating to.
     * @return True to allow navigation, false otherwise.
     */
    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        orderShown.setText(null);//clears the order shown in the checkout screen when leaving the screen
        return true;
    }

    /**
     * Handles logic for entering this screen
     *
     * @param toScreen The screen the user is navigating to.
     * @return The screen to navigate to.
     */
    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        return toScreen;
    }

    /**
     * Sets up the screen when entering it, including user info, order details, and fonts.
     */
    @Override
    public void onEnterScreen() {
        //sets up the name of the user on the top tight
        //sets up the cart total on the top right
        setUpUserAndOrderInfo(lblHiName, lblCurTotal);
        //sets up the name, email, and phone for the order
        setUpForCheckOut(lblCheckName, lblCheckEmail, lblCheckPhone);

        lblCheckOut.setFont(info.getCheckOutFont1());
        lbllYourInfo.setFont(info.getCheckOutFont1());
        lblName.setFont(info.getCheckOutFont2());
        lblEmail.setFont(info.getCheckOutFont2());
        lblPhone.setFont(info.getCheckOutFont2());


        //prints out the current order, if the order changes so will the JTextArea
        for(int i=0; i<info.getCurOrder().getItems().size(); i++){
            orderShown.append(info.getCurOrder().getItems().get(i).toString() + "\n");
            orderShown.setFont(info.getOptionsFont());
        }
    
    }
    /**
     * Initializes custom UI components, such as images for logos.
     */
    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
