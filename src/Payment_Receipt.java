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
    private JLabel ListIngred;
    private JLabel Total;
    private JLabel PaymentMethod;
    private JLabel TotalInfo;
    private JLabel Carry;
    private JLabel StorePhone;
    private JLabel StoreAddress;
    private JLabel EstTime;
    
    public Payment_Receipt(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
        super(screenLayoutController, screenContainer, info, panelName);
        setScreenPanel(pnlPaymentReceipt);
        info.registerScreenName(Screen.PAYMENT_RECEIPT, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        
        btnHome.addActionListener(_ -> showScreen(Screen.MENU));
    }
    
    @Override
    public Screen onAttemptLeaveScreen(ProgramInfo info, Screen fromScreen) {
        return fromScreen;
    }
    
    @Override
    public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
        return toScreen;
    }
}
