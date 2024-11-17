import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment_Receipt extends CardScreen{


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

    public Payment_Receipt(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);
        btnHome.addActionListener(e -> showScreen("StartScreen"));
    }

    public JPanel getScreenPanel(){return Home;}

}
