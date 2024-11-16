import javax.swing.*;
import java.awt.*;

public class PizzaGUI extends CardScreen{
    private JPanel panel1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JCheckBox yesCheeseCheckBox;
    private JButton btnDeals;
    private JButton btnLocations;
    private JButton btnHome;
    private JButton btnMenu;
    private JButton cartButton;

    public PizzaGUI(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);
    }
    public JPanel getScreenPanel(){
        return panel1;
    }
}
