import javax.swing.*;
import java.awt.*;

public class Payment_Receipt extends CardScreen{


    private JPanel Home;
    private JLabel Thanks;
    private JLabel OrderNumber;
    private JButton HomeBTN;
    private static int orderNumber = 10000;

    public Payment_Receipt(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);

        Thanks.setText("Thank you for placing your order, an email confirmation will be sent to " + info.getEmail());
        OrderNumber.setText("Order Number: " + orderID());
        HomeBTN.addActionListener(e -> showScreen("StartScreen"));
    }

    public synchronized int orderID(){
        int orderID = orderNumber;
        orderNumber++;
        return orderNumber;
    }


    public JPanel getScreenPanel(){
        return Home;
    }
}
