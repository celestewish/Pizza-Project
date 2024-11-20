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
    
    private JButton btnCreate;
    
    private JComboBox<Integer> cobxCount;
    private JTextArea txtareaPizzaInfo;
    private JLabel lblTotalCostPizzaPlusCount;
    
    private boolean orderComplete = false;
    
    private static final Map<String, ToppingPlacement> placementMap = Utils.createEnumMap(ToppingPlacement.class);
    
    public ToppingsGUI(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlToppings);
        info.registerScreenName(Screen.TOPPINGS, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        
        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
        
        createUIComponents();
        
        addTotalCostField(lblCurTotal);
        
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
        addJComponent(cobxCount);
        
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
        
        Map<ToppingType, JCheckBox> checkBoxEnumMap = new HashMap<>();
        checkBoxEnumMap.put(ToppingType.BACON, chbxXBacon);
        checkBoxEnumMap.put(ToppingType.CHICKEN, chbxXChicken);
        checkBoxEnumMap.put(ToppingType.PEPPERONI, chbxXPepperoni);
        checkBoxEnumMap.put(ToppingType.GROUND_BEEF, chbxXGbeef);
        checkBoxEnumMap.put(ToppingType.SAUSAGE, chbxXSausage);
        checkBoxEnumMap.put(ToppingType.MUSHROOMS, chbxXMushrooms);
        checkBoxEnumMap.put(ToppingType.OLIVES, chbxXOlives);
        checkBoxEnumMap.put(ToppingType.ONIONS, chbxXOnions);
        checkBoxEnumMap.put(ToppingType.PEPPERS, chbxXPeppers);
        checkBoxEnumMap.put(ToppingType.SPINACH, chbxXSpinach);
        
        txtareaPizzaInfo.setText("");
        
        txtareaPizzaInfo.setFocusable(false);
        
        btnCreate.addActionListener(_ -> {
            if (showConfirmationDialogueGreen("Add Pizza to Order?", "Are you finished making your pizza?")) {
                info.addCurPizzaToOrder(cobxCount.getSelectedIndex() + 1);
                orderComplete = true;
                showScreen(Screen.MENU);
            }
        });
        
        cobxCount.addActionListener(_ -> {
            String output = "Total: " + info.formatter.format(info.getCurPizza().calcPrice() * (cobxCount.getSelectedIndex() + 1));
            lblTotalCostPizzaPlusCount.setText(output);
        });
        
        for (Map.Entry<ToppingType, JComboBox<?>> entry : comboBoxEnumMap.entrySet()) {
            ToppingType topping = entry.getKey(); // Get the enum
            JComboBox<?> comboBox = entry.getValue(); // Get the JComboBox
            
            comboBox.addActionListener(_ -> {
                System.out.println(info.getCurPizza().toString());
                if (placementMap.get((String) comboBox.getSelectedItem()) == ToppingPlacement.NONE) {
                    info.getCurPizza().removeTopping(topping);
                } else if (info.getCurPizza().getTopping(topping) == null) {
                    info.getCurPizza().addTopping(new Topping(
                            topping,
                            checkBoxEnumMap.get(topping).isSelected(),
                            placementMap.get((String) comboBox.getSelectedItem())));
                } else {
                    info.getCurPizza().getTopping(topping).setPlacement(placementMap.get((String) comboBox.getSelectedItem()));
                }
                txtareaPizzaInfo.setText(info.getCurPizza().toString());
                String output = "Total: " + info.formatter.format(info.getCurPizza().calcPrice() * (cobxCount.getSelectedIndex() + 1));
                lblTotalCostPizzaPlusCount.setText(output);
            });
        }
        
        for (Map.Entry<ToppingType, JCheckBox> entry : checkBoxEnumMap.entrySet()) {
            ToppingType topping = entry.getKey(); // Get the enum
            JCheckBox checkBox = entry.getValue(); // Get the JComboBox
            
            checkBox.addActionListener(_ -> {
                if (info.getCurPizza().getTopping(topping) == null) {
                    return;
                }
                info.getCurPizza().getTopping(topping).setExtra(checkBox.isSelected());
                txtareaPizzaInfo.setText(info.getCurPizza().toString());
                
                String output = "Total: " + info.formatter.format(info.getCurPizza().calcPrice() * (cobxCount.getSelectedIndex() + 1));
                lblTotalCostPizzaPlusCount.setText(output);
            });
        }
    }
    
    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        if (orderComplete)
            return true;
        else {
            boolean staying = showConfirmationDialogue("Abandon Pizza?", "Yes, I want to abandon my pizza", "No, keep me here", "Are you sure?");
            
            if (!staying) {
                info.setCurPizza(null);
            }
            
            return staying;
        }
    }
    
    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        return toScreen;
    }
    
    @Override
    public void onEnterScreen() {
        setUpUserAndOrderInfo(lblHiName, lblCurTotal);
        txtareaPizzaInfo.setText(info.getCurPizza().toString());
        orderComplete = false;
        String output = "Total: " + info.formatter.format(info.getCurPizza().calcPrice() * (cobxCount.getSelectedIndex() + 1));
        lblTotalCostPizzaPlusCount.setText(output);
    }
    
    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
