import javax.swing.*;
import java.awt.*;

public class ToppingsGUI extends CardScreen{


    private JButton btnDeals;
    private JButton btnLocations;
    private JButton btnHome;
    private JButton btnMenu;
    private JButton cartButton;
    private JComboBox comboBox1;
    private JCheckBox extraCheckBox;

    public ToppingsGUI(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);
    }
}
