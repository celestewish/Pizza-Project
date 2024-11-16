import javax.swing.*;
import java.awt.*;

public class PaymentScreen extends CardScreen {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel nameShown;

    public PaymentScreen(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info){
        super(screenLayoutController, screenContainer, info);
        String[] name = info.getName().split(" ");
        nameShown.setText(name[0]);
    }
}
