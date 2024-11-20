import javax.swing.*;
import java.awt.*;
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
    
    private static Map<String, ToppingPlacement> placementMap = Utils.createEnumMap(ToppingPlacement.class);
    
    public ToppingsGUI(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlToppings);
        info.registerScreenName(Screen.TOPPINGS, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        
        setUpNavBar_LoggedIn(btnHome, btnMenu, btnDeals, btnLocations, btnSignOut, btnCart);
        
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
        
        createUIComponents();
//        //adds the toppings
//        comboBox1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                pepperoni[0] = new Topping("pepperoni", 3f, false, Objects.requireNonNull(comboBox1.getSelectedItem()).toString());
//            }
//        });
//        comboBox2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                sausage[0] = new Topping("sausage", 3f, false, Objects.requireNonNull(comboBox2.getSelectedItem()).toString());
//            }
//        });
//        comboBox3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                bacon[0] = new Topping("bacon", 3f, false, Objects.requireNonNull(comboBox3.getSelectedItem()).toString());
//            }
//        });
//        comboBox4.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                chicken[0] =  new Topping("chicken", 3f, false, Objects.requireNonNull(comboBox4.getSelectedItem()).toString());
//            }
//        });
//        comboBox5.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                groundBeef[0] =  new Topping("groundBeef", 3f, false, Objects.requireNonNull(comboBox5.getSelectedItem()).toString());
//            }
//        });
//        comboBox6.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                spinach[0] =  new Topping("spinach", 3f, false, Objects.requireNonNull(comboBox6.getSelectedItem()).toString());
//            }
//        });
//        comboBox7.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                onions[0] = new Topping("onions", 3f, false, Objects.requireNonNull(comboBox7.getSelectedItem()).toString());
//            }
//        });
//        comboBox8.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                olives[0] =  new Topping("olives", 3f, false, Objects.requireNonNull(comboBox8.getSelectedItem()).toString());
//            }
//        });
//        comboBox9.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                peppers[0] = new Topping("peppers", 3f, false, Objects.requireNonNull(comboBox9.getSelectedItem()).toString());
//            }
//        });
//        comboBox10.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                mushrooms[0] = new Topping("mushrooms", 3f, false, Objects.requireNonNull(comboBox10.getSelectedItem()).toString());
//            }
//        });
//        //adds toppings, adds them to database, and then returns to menu
//        returnButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Pizza myPizza = info.getCurPizza();
//                myPizza.addTopping(pepperoni[0]);
//                myPizza.addTopping(sausage[0]);
//                myPizza.addTopping(bacon[0]);
//                myPizza.addTopping(chicken[0]);
//                myPizza.addTopping(groundBeef[0]);
//                myPizza.addTopping(spinach[0]);
//                myPizza.addTopping(onions[0]);
//                myPizza.addTopping(olives[0]);
//                myPizza.addTopping(peppers[0]);
//                myPizza.addTopping(mushrooms[0]);
//                info.setCurPizza(Objects.requireNonNull(myPizza));
//                showScreen(Screen.MENU);
//            }
//        });
//
//        //checks for extras
//        extraCheckBox1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (extraCheckBox1.isSelected()) {
//                    pepperoni[0] = new Topping("pepperoni", 6f, true, Objects.requireNonNull(comboBox1.getSelectedItem()).toString());
//                }
//                else{
//                    pepperoni[0] = new Topping("pepperoni", 6f, false, Objects.requireNonNull(comboBox1.getSelectedItem()).toString());
//                }
//            }
//        });
//        extraCheckBox2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (extraCheckBox2.isSelected()) {
//                    sausage[0] = new Topping("sausage", 6f, true, Objects.requireNonNull(comboBox2.getSelectedItem()).toString());
//                }
//                else{
//                    sausage[0] = new Topping("sausage", 6f, false, Objects.requireNonNull(comboBox2.getSelectedItem()).toString());
//                }
//            }
//        });
//        extraCheckBox3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (extraCheckBox3.isSelected()) {
//                    bacon[0] = new Topping("bacon", 6f, true, Objects.requireNonNull(comboBox3.getSelectedItem()).toString());
//                }
//                else{
//                    bacon[0] = new Topping("bacon", 6f, false, Objects.requireNonNull(comboBox3.getSelectedItem()).toString());
//                }
//            }
//        });
//        extraCheckBox4.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (extraCheckBox4.isSelected()) {
//                    chicken[0] =  new Topping("chicken", 6f, true, Objects.requireNonNull(comboBox4.getSelectedItem()).toString());
//                }
//                else{
//                    chicken[0] =  new Topping("chicken", 6f, false, Objects.requireNonNull(comboBox4.getSelectedItem()).toString());
//                }
//            }
//        });
//        extraCheckBox5.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (extraCheckBox5.isSelected()) {
//                    groundBeef[0] =  new Topping("groundBeef", 6f, true, Objects.requireNonNull(comboBox5.getSelectedItem()).toString());
//                }
//                else{
//                    groundBeef[0] =  new Topping("groundBeef", 6f, false, Objects.requireNonNull(comboBox5.getSelectedItem()).toString());
//                }
//            }
//        });
//        extraCheckBox6.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (extraCheckBox6.isSelected()) {
//                    spinach[0] =  new Topping("spinach", 6f, true, Objects.requireNonNull(comboBox6.getSelectedItem()).toString());
//                }
//                else{
//                    spinach[0] =  new Topping("spinach", 6f, false, Objects.requireNonNull(comboBox6.getSelectedItem()).toString());
//                }
//            }
//        });
//        extraCheckBox7.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (extraCheckBox7.isSelected()) {
//                    onions[0] = new Topping("onions", 6f, true, Objects.requireNonNull(comboBox7.getSelectedItem()).toString());
//                }
//                else{
//                    onions[0] = new Topping("onions", 6f, false, Objects.requireNonNull(comboBox7.getSelectedItem()).toString());
//                }
//            }
//        });
//        extraCheckBox8.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (extraCheckBox8.isSelected()) {
//                    olives[0] =  new Topping("olives", 6f, true, Objects.requireNonNull(comboBox8.getSelectedItem()).toString());
//                }
//                else{
//                    olives[0] =  new Topping("olives", 6f, false, Objects.requireNonNull(comboBox8.getSelectedItem()).toString());
//                }
//            }
//        });
//        extraCheckBox9.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (extraCheckBox9.isSelected()) {
//                    peppers[0] = new Topping("peppers", 6f, true, Objects.requireNonNull(comboBox9.getSelectedItem()).toString());
//                }
//                else{
//                    peppers[0] = new Topping("peppers", 6f, false, Objects.requireNonNull(comboBox9.getSelectedItem()).toString());
//                }
//            }
//        });
//        extraCheckBox10.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (extraCheckBox10.isSelected()) {
//                    mushrooms[0] = new Topping("mushrooms", 6f, true, Objects.requireNonNull(comboBox10.getSelectedItem()).toString());
//                }
//                else{
//                    mushrooms[0] = new Topping("mushrooms", 6f, false, Objects.requireNonNull(comboBox10.getSelectedItem()).toString());
//                }
//            }
//        });
//        updateTotalButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                float totalCost = 0;
//                if (pepperoni[0] != null) {
//                    totalCost += pepperoni[0].getBasePrice();
//                }
//                if (sausage[0] != null) {
//                    totalCost += sausage[0].getBasePrice();
//                }
//                if (bacon[0] != null) {
//                    totalCost += bacon[0].getBasePrice();
//                }
//                if (chicken[0] != null) {
//                    totalCost += chicken[0].getBasePrice();
//                }
//                if (groundBeef[0] != null) {
//                    totalCost += groundBeef[0].getBasePrice();
//                }
//                if (spinach[0] != null) {
//                    totalCost += spinach[0].getBasePrice();
//                }
//                if (onions[0] != null) {
//                    totalCost += onions[0].getBasePrice();
//                }
//                if (olives[0] != null) {
//                    totalCost += olives[0].getBasePrice();
//                }
//                if (peppers[0] != null) {
//                    totalCost += peppers[0].getBasePrice();
//                }
//                if (mushrooms[0] != null) {
//                    totalCost += mushrooms[0].getBasePrice();
//                }
//                totalLabel.setText("Total Cost: " + String.valueOf(totalCost));
//            }
//        });
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
    }
    
    private void createUIComponents() {
        pnlCartLogo = new ImagePanel("cart.png");
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
