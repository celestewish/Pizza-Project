import javax.swing.*;
import java.awt.*;


public class CheckOut extends CardScreen {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    
    public CheckOut(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);
    }
    
    public JPanel getScreenPanel() {return panel1;}
}
