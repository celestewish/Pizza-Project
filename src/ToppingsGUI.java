import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ToppingsGUI extends CardScreen {
    private JPanel pnlToppings;
    
    private JPanel pnlLogo;
    private JPanel pnlCartLogo;
    
    private JButton btnHome;
    private JButton btnMenu;
    private JButton btnDeals;
    private JButton btnLocations;
    private JButton btnSignOut;
    private JButton btnCart;
    
    private JLabel lblHiName;
    private JLabel lblCurTotal;
    
    private JComboBox<String> cobxPepperoni;
    private JCheckBox chbxXPepperoni;
    
    private JComboBox<String> cobxSausage;
    private JCheckBox chbxXSausage;
    
    private JComboBox<String> cobxBacon;
    private JCheckBox chbxXBacon;
    
    private JComboBox<String> cobxChicken;
    private JCheckBox chbxXChicken;
    
    private JComboBox<String> cobxGbeef;
    private JCheckBox chbxXGbeef;
    
    private JComboBox<String> cobxSpinach;
    private JCheckBox chbxXSpinach;
    
    private JComboBox<String> cobxOnions;
    private JCheckBox chbxXOnions;
    
    private JComboBox<String> cobxOlives;
    private JCheckBox chbxXOlives;
    
    private JComboBox<String> cobxPeppers;
    private JCheckBox chbxXPeppers;
    
    private JComboBox<String> cobxMushrooms;
    private JCheckBox chbxXMushrooms;
    
    private JButton btnContinue;
    private JTextArea txtAreaPizzaInfo;
    
    private static Map<String, ToppingPlacement> placementMap = Utils.createEnumMap(ToppingPlacement.class);
    
    public ToppingsGUI(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlToppings);
        info.registerScreenName(Screen.TOPPINGS, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        
        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
        
        createUIComponents();
        
        addJComponent(cobxBacon);
        addJComponent(chbxXBacon);
        addJComponent(cobxPepperoni);
        addJComponent(chbxXPepperoni);
        addJComponent(cobxSausage);
        addJComponent(chbxXSausage);
        addJComponent(cobxChicken);
        addJComponent(chbxXChicken);
        addJComponent(cobxGbeef);
        addJComponent(chbxXGbeef);
        addJComponent(cobxSpinach);
        addJComponent(chbxXSpinach);
        addJComponent(cobxOnions);
        addJComponent(chbxXOnions);
        addJComponent(cobxOlives);
        addJComponent(chbxXOlives);
        addJComponent(cobxPeppers);
        addJComponent(chbxXPeppers);
        addJComponent(cobxMushrooms);
        addJComponent(chbxXMushrooms);
        
        setFontForJCompsOfAType(info.getComboBoxFont(), JComboBox.class);
        setFontForJCompsOfAType(info.getCheckBoxFont(), JCheckBox.class);
        
        Utils.populateComboBox(cobxMushrooms, ToppingPlacement.class);
        Utils.populateComboBox(cobxBacon, ToppingPlacement.class);
        Utils.populateComboBox(cobxChicken, ToppingPlacement.class);
        Utils.populateComboBox(cobxPepperoni, ToppingPlacement.class);
        Utils.populateComboBox(cobxSausage, ToppingPlacement.class);
        Utils.populateComboBox(cobxGbeef, ToppingPlacement.class);
        Utils.populateComboBox(cobxSpinach, ToppingPlacement.class);
        Utils.populateComboBox(cobxOnions, ToppingPlacement.class);
        Utils.populateComboBox(cobxOlives, ToppingPlacement.class);
        Utils.populateComboBox(cobxPeppers, ToppingPlacement.class);
        
        Map<ToppingType, JComboBox<?>> comboBoxEnumMap = new HashMap<>();
        comboBoxEnumMap.put(ToppingType.BACON, cobxBacon);
        comboBoxEnumMap.put(ToppingType.CHICKEN, cobxChicken);
        comboBoxEnumMap.put(ToppingType.PEPPERONI, cobxPepperoni);
        comboBoxEnumMap.put(ToppingType.GROUND_BEEF, cobxGbeef);
        comboBoxEnumMap.put(ToppingType.SAUSAGE, cobxSausage);
        comboBoxEnumMap.put(ToppingType.MUSHROOMS, cobxMushrooms);
        comboBoxEnumMap.put(ToppingType.OLIVES, cobxOlives);
        comboBoxEnumMap.put(ToppingType.ONIONS, cobxOnions);
        comboBoxEnumMap.put(ToppingType.PEPPERS, cobxPeppers);
        comboBoxEnumMap.put(ToppingType.SPINACH, cobxSpinach);
        
        txtAreaPizzaInfo.setText("");
        
        btnContinue.addActionListener(_ -> {
            // Loop through each entry in the map
            for (Map.Entry<ToppingType, JComboBox<?>> entry : comboBoxEnumMap.entrySet()) {
                ToppingType topping = entry.getKey(); // Get the enum
                JComboBox<?> comboBox = entry.getValue(); // Get the JComboBox
                
                if (comboBox.getSelectedItem() != ToppingPlacement.NONE) {
                    info.getCurPizza().addTopping(new Topping());
                }
            }
        });
    }
    
    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        return true;
    }
    
    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        return toScreen;
    }
    
    @Override
    public void onEnterScreen() {
        setUpUserAndOrderInfo(lblHiName, lblCurTotal);
        txtAreaPizzaInfo.setText(info.getCurPizza().toString());
    }
    
    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
