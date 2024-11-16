import javax.swing.*;
import java.awt.*;

public class PaymentScreen extends CardScreen {
    private JPanel panel1;
    private JButton HomeBtn;
    private JButton menuButton;
    private JButton DealsBTN;
    private JButton LocationsBTN;
    private JLabel nameShown;
    private JLabel streetName;
    private JLabel cityStateZip;
    private JTextField firstLastTextField;
    private JTextField a1234123412341234TextField;
    private JTextField a123TextField;
    private JTextField a0101TextField;
    private JTextField a123456TextField;
    private JButton submitPaymentButton;

    public PaymentScreen(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info){
        super(screenLayoutController, screenContainer, info);
        String[] name = info.getName().split(" ");
        String[] custAddress = info.getAddress().split(" ");
        nameShown.setText(name[0]);
        streetName.setText(custAddress[0] + " "  + custAddress[1] + " " + custAddress[2]);
        cityStateZip.setText(custAddress[3] + " " + custAddress[4] + " " + custAddress[5]);

        HomeBtn.addActionListener(e -> showScreen("StartScreen"));
        menuButton.addActionListener(e -> showScreen("MenuGUI"));
        DealsBTN.addActionListener(e -> showScreen("Deals"));
        LocationsBTN.addActionListener(e -> showScreen("Locations"));
    }
}
