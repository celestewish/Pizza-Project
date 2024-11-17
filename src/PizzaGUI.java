import javax.swing.*;
import java.awt.*;

public class PizzaGUI extends CardScreen {
    private JPanel pizzaPanel;
    
    private JButton btnHome;
    private JButton btnMenu;
    private JButton btnDeals;
    private JButton btnLocations;
    private JButton btnSignOut;
    private JButton viewCartButton;
    private JButton btnContinue;
    
    private JLabel lblHiCustomerName;
    
    private JComboBox cboxCrust;
    private JComboBox cboxSize;
    private JComboBox cboxSauce;
    private JCheckBox checkCheese;
    
    public PizzaGUI(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);
        
        btnHome.addActionListener(_ -> {
            showScreen("Menu");
        });

        btnContinue.addActionListener(_ -> {
            showScreen("ToppingsGUI");
        });
    }
    
    public JPanel getScreenPanel(){
        return pizzaPanel;
    }
}
