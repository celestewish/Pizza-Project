import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The ToppingsGUI class provides a graphical interface for users to customize their pizza
 * by selecting toppings, their placements, and quantities. It integrates seamlessly with
 * the ordering system to update pizza details and total cost in real-time.
 */
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
    private JScrollPane scrollPane;
    
    private boolean orderComplete = false;
    
    private static final Map<String, ToppingPlacement> placementMap = Utils.createEnumMap(ToppingPlacement.class);

    /**
     * Constructor for the ToppingsGUI class.
     *
     * @param screenLayoutController The CardLayout controller for managing screen transitions.
     * @param screenContainer        The container holding all screens.
     * @param panelName              The unique name for this panel.
     */
    public ToppingsGUI(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlToppings);
        info.registerScreenName(Screen.TOPPINGS, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        
        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
        
        createUIComponents();
        
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
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
                if (info.getCurPizza() == null) {
                    showWarningDialogue(
                            "Something went wrong on our end. We are very sorry! Please try again.",
                            "Okay",
                            "An error occurred.");
                    showScreen(Screen.MENU);
                }
                Pizza pizza = info.getCurPizza().clone();
                
                info.getCurOrder().addItem(new MenuItemWithCount(pizza, cobxCount.getSelectedIndex() + 1));
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
                if (info.getCurPizza() == null) {
                    showScreen(Screen.MENU);
                }
                
                if (placementMap.get((String) comboBox.getSelectedItem()) == ToppingPlacement.NONE) {
                    info.getCurPizza().removeTopping(topping);
                }
                else if (info.getCurPizza().getTopping(topping) == null) {
                    info.getCurPizza().addTopping(new Topping(
                            topping,
                            checkBoxEnumMap.get(topping).isSelected(),
                            placementMap.get((String) comboBox.getSelectedItem())));
                }
                else {
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

    /**
     * Handles the logic when attempting to leave the screen.
     * If the pizza order is incomplete, shows a confirmation dialog.
     *
     * @param destinationScreen The screen to navigate to.
     * @return True if navigation is allowed, false otherwise.
     */
    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        if (orderComplete)
            return true;
        else {
            return info.isAttemptingLogout() && showConfirmationDialogue("Abandon Pizza?", "Yes, I want to abandon my pizza", "No, keep me here", "Are you sure?");
        }
    }

    /**
     * Handles the logic when attempting to enter the screen.
     *
     * @param toScreen The screen to navigate to.
     * @return Always returns the target screen.
     */
    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        return toScreen;
    }

    /**
     * Performs setup when the screen is entered.
     * Updates the pizza count combo box and user information display.
     */
    @Override
    public void onEnterScreen() {
        if (info.getCurPizza() == null) {
            showWarningDialogue(
                    "Something went wrong on our end. We are very sorry! Please try again.",
                    "Okay",
                    "An error occurred.");
            showScreen(Screen.MENU);
        }
        updatePizzaCountComboBox(cobxCount);
        
        setUpUserAndOrderInfo(lblHiName, lblCurTotal);
        txtareaPizzaInfo.setText(info.getCurPizza().toString());
        orderComplete = false;
        String output = "Total: " + info.formatter.format(info.getCurPizza().calcPrice() * (cobxCount.getSelectedIndex() + 1));
        lblTotalCostPizzaPlusCount.setText(output);
        
        info.setAttemptingLogout(false);
    }
    /**
     * Initializes custom UI components, such as logos.
     */

    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }

    /**
     * Updates the pizza count combo box based on remaining order capacity.
     *
     * @param comboBox The combo box to update.
     */
    private void updatePizzaCountComboBox(JComboBox<Integer> comboBox) {
        // Clear the existing items in the combo box
        comboBox.removeAllItems();
        
        // Calculate the remaining pizzas the user can order
        int pizzasLeft = 10 - info.getCurOrder().getNumberPizzasInOrder();
        
        // Repopulate the combo box with integers from 1 to pizzasLeft
        for (int i = 1; i <= pizzasLeft; i++) {
            comboBox.addItem(i);
        }
        
        // If there are no pizzas left, disable the combo box
        comboBox.setEnabled(pizzasLeft > 0);
    }
}
