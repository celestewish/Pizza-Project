import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PizzaGUI extends CardScreen {
    private JPanel pnlCreatePizza;
    
    private JButton btnHome;
    private JButton btnContinue;
    private JButton btnMenu;
    private JButton btnDeals;
    private JButton btnLocations;
    private JButton btnSignOut;
    private JButton btnCart;
    
    private JComboBox<?> cboxCrust;
    private JComboBox<?> cboxSize;
    private JComboBox<?> cboxSauce;
    private JPanel pnlLogo;
    private JPanel pnlCartLogo;
    private JLabel lblHiName;
    private JLabel lblCurTotal;
    
    public PizzaGUI(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
        super(screenLayoutController, screenContainer, info, panelName);
        setScreenPanel(pnlCreatePizza);
        info.registerScreenName(Screen.CREATE_PIZZA, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        
        final PizzaSize[] mySize = new PizzaSize[1];
        final CrustType[] myCrust = new CrustType[1];
        final boolean[] sauce = new boolean[1];

        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);

        btnContinue.addActionListener(_ -> {
            Pizza newPizza = new Pizza(mySize[0], myCrust[0], sauce[0]);
            info.setCurPizza(newPizza);
            showScreen(Screen.TOPPINGS);
        });

        cboxCrust.addActionListener(_ -> {
            if (Objects.requireNonNull(cboxCrust.getSelectedItem()).toString().equalsIgnoreCase("deep dish")){
                myCrust[0] = CrustType.DEEP_DISH;
            }
            else if (cboxCrust.getSelectedItem().toString().equalsIgnoreCase("thin")){
                myCrust[0] = CrustType.THIN_CRUST;
            }
            else if (cboxCrust.getSelectedItem().toString().equalsIgnoreCase("thick")){
                myCrust[0] = CrustType.THICK_CRUST;
            }
        });
        
        cboxSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboxSize.getSelectedItem().toString().equalsIgnoreCase("small")){
                    mySize[0] = PizzaSize.SMALL;
                }
                else if (cboxSize.getSelectedItem().toString().equalsIgnoreCase("medium")){
                    mySize[0] = PizzaSize.MEDIUM;
                }
                else if (cboxSize.getSelectedItem().toString().equalsIgnoreCase("large")){
                    mySize[0] = PizzaSize.LARGE;
                }
                else if (cboxSize.getSelectedItem().toString().equalsIgnoreCase("xlarge")){
                    mySize[0] = PizzaSize.XL;
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
    
    
    @Override
    public boolean onAttemptLeaveScreen(ProgramInfo info) {
        return showConfirmationDialog("Abandon Pizza?", "Yes, I want to abandon my pizza", "No, keep me here");
    }
    
    @Override
    public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
        return toScreen;
    }
    
    @Override
    public void onEnterScreen(ProgramInfo info) {
    
    }
    
    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
