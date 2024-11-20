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

    private boolean isContinuing = false;

    public PizzaGUI(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlCreatePizza);
        info.registerScreenName(Screen.CREATE_PIZZA, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());

        final PizzaSize[] mySize = new PizzaSize[1];
        final CrustType[] myCrust = new CrustType[1];
        final SauceOption[] mysauce = new SauceOption[1];

        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);

        btnContinue.addActionListener(_ -> {
            // Check if any option is null
            if ((mySize[0] == null) || (myCrust[0] == null) || (mysauce[0] == null)) {
                JOptionPane.showMessageDialog(null, "Please select a size, crust, and sauce.");
            } else {
                isContinuing = true;
                Pizza newPizza = new Pizza(mySize[0], myCrust[0], mysauce[0]);
                info.setCurPizza(newPizza);
                System.out.println(info.getCurPizza().toString());
                showScreen(Screen.TOPPINGS);
            }
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
                if (Objects.requireNonNull(cboxSize.getSelectedItem()).toString().equalsIgnoreCase("small")){
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
                if (Objects.requireNonNull(cboxSauce.getSelectedItem()).toString().equalsIgnoreCase("marinera")){
                    mysauce[0] = SauceOption.MARINARA;
                }
                else if (cboxSauce.getSelectedItem().toString().equalsIgnoreCase("alfredo")){
                    mysauce[0] = SauceOption.ALFREDO;
                }
            }
        });
    }



    @Override
    public boolean onAttemptLeaveScreen() {
        if(!isContinuing){
            return showConfirmationDialog("Abandon Pizza?", "Yes, I want to abandon my pizza", "No, keep me here", "Are you sure?");
        }
        return isContinuing;
    }

    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        return toScreen;
    }

    @Override
    public void onEnterScreen() {

    }

    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}