import javax.swing.*;
import java.awt.*;

public class Payment_Receipt extends CardScreen{
    private JPanel pnlPaymentReceipt;

    private JPanel Home;
    private JButton btnHome;
    private JLabel OrderPlace;
    private JLabel ThankYou;
    private JLabel OrderNumber;
    private JLabel OrderSummary;
    private JLabel ItemType;
    private JLabel ListIngredients;
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
    
    public Payment_Receipt(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlPaymentReceipt);
        info.registerScreenName(Screen.PAYMENT_RECEIPT, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());

        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
        
       // btnHome.addActionListener(_ -> showScreen(Screen.MENU));
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
        OrderSummary.setText(String.valueOf(info.getCurOrder()));
        TotalInfo.setText(String.valueOf(info.getCurOrder().calcTotalOrderCost()));
    }
    
    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
