import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;

public class ToppingsGUI extends CardScreen {

    private JPanel toppingsPanel;
    private JButton btnDeals;
    private JButton btnLocations;
    private JButton btnHome;
    private JButton btnMenu;
    private JButton cartButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JComboBox comboBox7;
    private JComboBox comboBox8;
    private JComboBox comboBox9;
    private JComboBox comboBox10;

    private JCheckBox extraCheckBox1;
    private JCheckBox extraCheckBox2;
    private JCheckBox extraCheckBox3;
    private JCheckBox extraCheckBox4;
    private JCheckBox extraCheckBox5;
    private JCheckBox extraCheckBox6;
    private JCheckBox extraCheckBox7;
    private JCheckBox extraCheckBox8;
    private JCheckBox extraCheckBox9;
    private JCheckBox extraCheckBox10;
    private JLabel totalLabel;
    private JButton returnButton;


    public ToppingsGUI(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);
        final Topping[] pepperoni = new Topping[1];
        final Topping[] sausage = new Topping[1];
        final Topping[] bacon = new Topping[1];
        final Topping[] chicken = new Topping[1];
        final Topping[] groundBeef = new Topping[1];
        final Topping[] spinach = new Topping[1];
        final Topping[] onions = new Topping[1];
        final Topping[] olives = new Topping[1];
        final Topping[] peppers = new Topping[1];
        final Topping[] mushrooms = new Topping[1];
        LinkedList<Topping> toppings = new LinkedList<>();

        btnHome.addActionListener(e -> showScreen("Deals"));

        btnDeals.addActionListener(e -> showScreen("Deals"));

        btnLocations.addActionListener(e -> showScreen("Location"));

        btnMenu.addActionListener(e -> showScreen("Menu"));

        cartButton.addActionListener(e -> showScreen("Cart"));


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pepperoni[0] = new Topping("pepperoni", 3f, false, Objects.requireNonNull(comboBox1.getSelectedItem()).toString());

            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sausage[0] = new Topping("sausage", 3f, false, Objects.requireNonNull(comboBox2.getSelectedItem()).toString());
            }
        });
        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bacon[0] = new Topping("bacon", 3f, false, Objects.requireNonNull(comboBox3.getSelectedItem()).toString());
            }
        });
        comboBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chicken[0] =  new Topping("chicken", 3f, false, Objects.requireNonNull(comboBox4.getSelectedItem()).toString());
            }
        });
        comboBox5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groundBeef[0] =  new Topping("groundBeef", 3f, false, Objects.requireNonNull(comboBox5.getSelectedItem()).toString());
            }
        });
        comboBox6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinach[0] =  new Topping("spinach", 3f, false, Objects.requireNonNull(comboBox6.getSelectedItem()).toString());
            }
        });
        comboBox7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onions[0] = new Topping("onions", 3f, false, Objects.requireNonNull(comboBox7.getSelectedItem()).toString());
            }
        });
        comboBox8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                olives[0] =  new Topping("olives", 3f, false, Objects.requireNonNull(comboBox8.getSelectedItem()).toString());
            }
        });
        comboBox9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                peppers[0] = new Topping("peppers", 3f, false, Objects.requireNonNull(comboBox9.getSelectedItem()).toString());
            }
        });
        comboBox10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mushrooms[0] = new Topping("mushrooms", 3f, false, Objects.requireNonNull(comboBox10.getSelectedItem()).toString());
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pizza myPizza = info.getCurPizza();
                myPizza.addTopping(pepperoni[0]);
                myPizza.addTopping(sausage[0]);
                myPizza.addTopping(bacon[0]);
                myPizza.addTopping(chicken[0]);
                myPizza.addTopping(groundBeef[0]);
                myPizza.addTopping(spinach[0]);
                myPizza.addTopping(onions[0]);
                myPizza.addTopping(olives[0]);
                myPizza.addTopping(peppers[0]);
                myPizza.addTopping(mushrooms[0]);
                info.setCurPizza(Objects.requireNonNull(myPizza));
                showScreen("Menu");
            }
        });
    }

    public JPanel getScreenPanel(){
        return toppingsPanel;
    }
}
