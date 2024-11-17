import javax.swing.*;
import java.awt.*;

public class Payment_Processing extends CardScreen{

    private JButton btnHome;
    private JButton btnMenu;
    private JButton btnDeals;
    private JButton btnLocations;
    private JButton btnSignIn_SignUp;
    private JPanel Checkout;
    private JTextField txtFname;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JTextField txtLname;
    private JCheckBox savePaymentCheckBox;
    private JTextField txtMname;

    public Payment_Processing(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);
    }
}
