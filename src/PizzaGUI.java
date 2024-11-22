import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the graphical user interface (GUI) for creating a pizza.
 * This screen allows users to select the crust type, pizza size, and sauce option,
 * and proceed to customize their pizza further. It also handles user interactions
 * and displays the total cost of the pizza being created.
 */
public class PizzaGUI extends CardScreen {
    private JPanel pnlCreatePizza;
    
    private JPanel pnlLogo;
    private JPanel pnlCartLogo;
    
    private JButton btnHome;
    private JButton btnContinue;
    private JButton btnMenu;
    private JButton btnDeals;
    private JButton btnLocations;
    private JButton btnSignOut;
    private JButton btnCart;
    
    private JLabel lblHiName;
    private JLabel lblCurTotal;

    private JComboBox<String> cobxCrust;
    private JComboBox<String> cobxSize;
    private JComboBox<String> cobxSauce;
    
    private JPanel imgCrust;
    private JPanel imgSize;
    private JPanel imgSauce;
    
    private JLabel lblTotalCost;

    /**
     * Constructs the PizzaGUI screen, sets up UI components, registers the screen,
     * and defines the actions for user interactions such as selecting crust, size, and sauce.
     *
     * @param screenLayoutController The CardLayout used for screen transitions
     * @param screenContainer The container holding all the screens
     * @param panelName The name of the panel for this screen
     */
    public PizzaGUI(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlCreatePizza);
        info.registerScreenName(Screen.CREATE_PIZZA, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        
        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
        
        addJComponent(cobxCrust);
        addJComponent(cobxSauce);
        addJComponent(cobxSize);
        addJComponent(lblTotalCost);
        
        addTotalCostField(lblCurTotal);
        
        lblTotalCost.setText("");
        
        cobxCrust.addItem("Make a selection");
        Utils.populateComboBox(cobxCrust, CrustType.class);
        Map<String, CrustType> crustTypeMap = Utils.createEnumMap(CrustType.class);
        
        cobxSize.addItem("Make a selection");
        Utils.populateComboBox(cobxSize, PizzaSize.class);
        Map<String, PizzaSize> sizeMap = Utils.createEnumMap(PizzaSize.class);
        
        cobxSauce.addItem("Make a selection");
        Utils.populateComboBox(cobxSauce, SauceOption.class);
        Map<String, SauceOption> sauceOptionMap = Utils.createEnumMap(SauceOption.class);
        
        setFontForJCompsOfAType(info.getComboBoxFont(), JComboBox.class);
        
        btnContinue.addActionListener(_ -> {
            if (cobxCrust.getSelectedIndex() == 0 || cobxSize.getSelectedIndex() == 0 || cobxSauce.getSelectedIndex() == 0) {
                showInfoDialogue("Please make all selections!", "Okay", "");
            }
            else if (showConfirmationDialogueGreen("Proceed?", "Are you ready to proceed with your pizza?")) {
                showScreen(Screen.TOPPINGS);
            }
        });
        
        cobxCrust.addActionListener(_ -> {
            if (cobxCrust.getSelectedIndex() != 0 && cobxSize.getSelectedIndex() != 0 && cobxSauce.getSelectedIndex() != 0) {
                SauceOption sauceOption = sauceOptionMap.get((String) cobxSauce.getSelectedItem());
                PizzaSize size = sizeMap.get((String) cobxSize.getSelectedItem());
                
                CrustType crustType = crustTypeMap.get((String) cobxCrust.getSelectedItem());
                
                if (info.getCurPizza() == null) {
                    Pizza pizza = new Pizza(size, crustType, sauceOption);
                    info.setCurPizza(pizza);
                }
                else {
                    info.getCurPizza().setCrust(crustType);
                }
                lblTotalCost.setText(info.getCurPizza().toString());
            }
        });
        
        cobxSize.addActionListener(_ -> {
            if (cobxCrust.getSelectedIndex() != 0 && cobxSize.getSelectedIndex() != 0 && cobxSauce.getSelectedIndex() != 0) {
                SauceOption sauceOption = sauceOptionMap.get((String) cobxSauce.getSelectedItem());
                CrustType crustType = crustTypeMap.get((String) cobxCrust.getSelectedItem());
                
                PizzaSize size = sizeMap.get((String) cobxSize.getSelectedItem());
                
                if (info.getCurPizza() == null) {
                    Pizza pizza = new Pizza(size, crustType, sauceOption);
                    info.setCurPizza(pizza);
                }
                else {
                    info.getCurPizza().setSize(size);
                }
                lblTotalCost.setText(info.getCurPizza().toString());
            }
        });
        
        cobxSauce.addActionListener(_ -> {
            if (cobxCrust.getSelectedIndex() != 0 && cobxSize.getSelectedIndex() != 0 && cobxSauce.getSelectedIndex() != 0) {
                CrustType crustType = crustTypeMap.get((String) cobxCrust.getSelectedItem());
                PizzaSize size = sizeMap.get((String) cobxSize.getSelectedItem());
                
                SauceOption sauceOption = sauceOptionMap.get((String) cobxSauce.getSelectedItem());
                
                if (info.getCurPizza() == null) {
                    Pizza pizza = new Pizza(size, crustType, sauceOption);
                    info.setCurPizza(pizza);
                }
                else {
                    info.getCurPizza().setSauce(sauceOption);
                }
                lblTotalCost.setText(info.getCurPizza().toString());
            }
        });
    }

    /**
     * Determines if the user can leave the current screen.
     * If leaving to the toppings screen, allows the transition.
     * Otherwise, asks for confirmation to abandon the current pizza creation.
     *
     * @param destinationScreen The screen the user is attempting to navigate to
     * @return True if the user can leave the screen, otherwise false
     */
    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        if (destinationScreen.equals(Screen.TOPPINGS)) {
            return true;
        }
        else {
	        return info.isAttemptingLogout() && showConfirmationDialogue("Abandon Pizza?", "Yes, I want to abandon my pizza", "No, keep me here", "Are you sure?");
        }
    }

    /**
     * Defines the behavior when attempting to enter this screen.
     *
     * @param toScreen The screen being navigated to
     * @return The screen to enter
     */
    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        return toScreen;
    }

    /**
     * Defines actions to perform when entering this screen, including resetting the screen
     * and clearing the pizza information.
     */
    @Override
    public void onEnterScreen() {
        setUpUserAndOrderInfo(lblHiName, lblCurTotal);
        resetScreen();
        if (info.getCurPizza() != null)
            info.setCurPizza(null);
        lblTotalCost.setText("");
        info.setAttemptingLogout(false);
    }
    /**
     * Initializes UI components like the logo and images for crust, size, and sauce.
     */

    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
        
        imgCrust = new ImagePanel("crust.jpg");
        imgSauce = new ImagePanel("sauce.jpg");
        imgSize = new ImagePanel("size.jpg");
    }
}