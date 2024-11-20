import javax.swing.*;
import java.awt.*;
import java.util.Map;

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

    public PizzaGUI(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlCreatePizza);
        info.registerScreenName(Screen.CREATE_PIZZA, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        
        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);

        
        addJComponent(cobxCrust);
        addJComponent(cobxSauce);
        addJComponent(cobxSize);
        
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
            else {
                SauceOption sauceOption = sauceOptionMap.get((String) cobxSauce.getSelectedItem());
                CrustType crustType = crustTypeMap.get((String) cobxCrust.getSelectedItem());
                PizzaSize size = sizeMap.get((String) cobxSize.getSelectedItem());
                if (showConfirmationDialogueGreen("Proceed?", "Are you ready to proceed with your pizza?")) {
                    Pizza pizza = new Pizza(size, crustType, sauceOption);
                    info.setCurPizza(pizza);
                    
                    showScreen(Screen.TOPPINGS);
                }
            }
        });
    }
    
    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        if (destinationScreen.equals(Screen.TOPPINGS))
            return true;
        else
            return showConfirmationDialogue("Abandon Pizza?", "Yes, I want to abandon my pizza", "No, keep me here", "Are you sure?");
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
        
        imgCrust = new ImagePanel("crust.jpg");
        imgSauce = new ImagePanel("sauce.jpg");
        imgSize = new ImagePanel("size.jpg");
    }
}