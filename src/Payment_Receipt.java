import javax.swing.*;
import java.awt.*;

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
        info.clearCurOrder();
        return true;
    }
    
    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        return toScreen;
    }
    
    @Override
    public void onEnterScreen() {
        setUpUserAndOrderInfo(lblHiName, lblCurTotal);
        for(int i=0; i<info.getCurOrder().getItems().size(); i++){
            orderShown.append(info.getCurOrder().getItems().get(i).toString() + "\n");
            orderShown.setFont(info.getOptionsFont());
        }
        /*Font textFont = new Font("Times New Roman", Font.BOLD, 24);
        Font optionsFont = new Font("Arial", Font.PLAIN, 20);
        if (info.getCurPizza() != null) {
            Pizza myPizza = info.getCurPizza();
            ItemType.setText("Custom Pizza");
            ListIngredients.setText(myPizza.toString());
        }

         */
        TotalInfo.setText(String.valueOf(info.getCurOrder().calcTotalOrderCost()));
    }
    
    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
