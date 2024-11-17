import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

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
        final pizzaSize[] mySize = new pizzaSize[1];
        final crustType[] myCrust = new crustType[1];
        final boolean[] sauce = new boolean[1];
        btnHome.addActionListener(_ -> {
            showScreen("Menu");
        });

        btnContinue.addActionListener(_ -> {
            Pizza myPizza = new Pizza(mySize[0], myCrust[0], sauce[0]);
            info.setCurPizza(myPizza);
            showScreen("ToppingsGUI");
        });
        cboxCrust.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboxCrust.getSelectedItem().toString().equalsIgnoreCase("deep dish")){
                    myCrust[0] = crustType.DEEP_DISH;
                }
                else if (cboxCrust.getSelectedItem().toString().equalsIgnoreCase("thin")){
                    myCrust[0] = crustType.THIN_CRUST;
                }
                else if (cboxCrust.getSelectedItem().toString().equalsIgnoreCase("thick")){
                    myCrust[0] = crustType.THICK_CRUST;
                }
            }
        });
        cboxSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboxSize.getSelectedItem().toString().equalsIgnoreCase("small")){
                    mySize[0] = pizzaSize.SMALL;
                }
                else if (cboxSize.getSelectedItem().toString().equalsIgnoreCase("medium")){
                    mySize[0] = pizzaSize.MEDIUM;
                }
                else if (cboxSize.getSelectedItem().toString().equalsIgnoreCase("large")){
                    mySize[0] = pizzaSize.LARGE;
                }
                else if (cboxSize.getSelectedItem().toString().equalsIgnoreCase("xlarge")){
                    mySize[0] = pizzaSize.XL;
                }
            }
        });

        cboxSauce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboxSauce.getSelectedItem().toString().equalsIgnoreCase("marinera")){
                    sauce[0] = true;
                }
                else if (cboxSauce.getSelectedItem().toString().equalsIgnoreCase("alfredo")){
                    sauce[0] = false;
                }
            }
        });
    }
    
    public JPanel getScreenPanel(){
        return pizzaPanel;
    }
}
