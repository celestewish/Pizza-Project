import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuGUI extends CardScreen{
    JFrame frame;
    private JLabel statusLabel;
    private JPanel panel1;
    private JButton homeButton;
    private JButton dealsButton;
    private JButton menuButton;
    private JButton locationsButton;
    private JButton carryOutXMinutesButton;
    private JButton viewOrderButton;
    private JTextArea createYourOwnPizzaTextArea;
    private JTextArea $PriceTextArea;
    private JButton createButton;
    private JTextArea wingsTextArea;
    private JComboBox Add;
    private JTextArea $PriceTextArea1;
    private JTextArea drinksTextArea;
    private JTextArea $PriceTextArea2;
    private JComboBox TypeDrinks;
    private JComboBox DrinkSize;
    private JComboBox comboBox1;
    private JTextArea garlicBreadTextArea;
    private JTextArea $PriceTextArea3;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    public MenuGUI(CardLayout screenLayoutController, JPanel screenContainer) {
        super(screenLayoutController, screenContainer);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLabel menuTitle = new JLabel();
        menuTitle.setText("Menu");
        frame.add(menuTitle);
        JButton viewOrder = new JButton("View Order");
        frame.add(viewOrder);
        JButton createPizza = new JButton("Create Your Own Pizza");
        frame.add(createPizza);
        createPizza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createPizza();
            }
        });
    }
    public static void createPizza() {
        JFrame frame = new JFrame();
        frame.setTitle("Create Your Own Pizza");
        JLabel menuTitle = new JLabel("Customize Your Pizza");
        frame.add(menuTitle);

        //crust sizes
        JLabel crustTitle = new JLabel("Crust");
        JButton thinCrust = new JButton("Thin Crust");
        frame.add(thinCrust);
        JButton thickCrust = new JButton("Thick Crust");
        frame.add(thickCrust);
        JButton deepDish = new JButton("Deep Dish");
        frame.add(deepDish);

        //pizza sizes
        JLabel sizeTitle = new JLabel("Size");
        frame.add(sizeTitle);
        JButton smallSizes = new JButton("Small");
        frame.add(smallSizes);
        JButton mediumSizes = new JButton("Medium");
        frame.add(mediumSizes);
        JButton largeSizes = new JButton("Large");
        frame.add(largeSizes);
        JButton exLargeSizes = new JButton("X-Large");
        frame.add(exLargeSizes);

        //pizza sauce
        JLabel sauceTitle = new JLabel("Sauce");
        frame.add(sauceTitle);
        JButton marinera = new JButton("Marinera");
        frame.add(marinera);
        JButton alfredo = new JButton("Alfredo");
        frame.add(alfredo);

        //cheese
        JLabel cheeseTitle = new JLabel("Cheese");
        frame.add(cheeseTitle);
        JButton regularCheese = new JButton("Regular");

        //toppings
        JLabel toppingsTitle = new JLabel("Meats");
        //meats
        JMenuBar meatBar = new JMenuBar();
        frame.setJMenuBar(meatBar);
        JMenu meatMenu = new JMenu("Meats");
        meatBar.add(meatMenu);
        JMenu pepperoni = new JMenu("Pepperoni");
        meatMenu.add(pepperoni);
        JMenu bacon = new JMenu("Bacon");
        meatMenu.add(bacon);
        JMenu chicken = new JMenu("Chicken");
        meatMenu.add(chicken);
        JMenu groundBeef = new JMenu("Ground Beef");
        meatMenu.add(groundBeef);

        //meat submenus
        //pepperoni
        JMenuItem leftPepperoni = new JMenuItem("Left");
        pepperoni.add(leftPepperoni);
        JMenuItem rightPepperoni = new JMenuItem("Right");
        pepperoni.add(rightPepperoni);
        JMenuItem fullPepperoni = new JMenuItem("Full");
        pepperoni.add(fullPepperoni);
        JCheckBoxMenuItem extraPepperoni = new JCheckBoxMenuItem("Extra");
        pepperoni.add(extraPepperoni);

        //bacon
        JMenuItem leftBacon = new JMenuItem("Left");
        bacon.add(leftBacon);
        JMenuItem rightBacon = new JMenuItem("Right");
        bacon.add(rightBacon);
        JMenuItem fullBacon = new JMenuItem("Full");
        bacon.add(fullBacon);
        JCheckBoxMenuItem extraBacon = new JCheckBoxMenuItem("Extra");
        bacon.add(extraBacon);

        //chicken
        JMenuItem leftChicken = new JMenuItem("Left");
        chicken.add(leftChicken);
        JMenuItem rightChicken = new JMenuItem("Right");
        chicken.add(rightChicken);
        JMenuItem fullChicken = new JMenuItem("Full");
        chicken.add(fullChicken);
        JCheckBoxMenuItem extraChicken = new JCheckBoxMenuItem("Extra");
        chicken.add(extraChicken);

        //ground beef
        JMenuItem leftBeef = new JMenuItem("Left");
        bacon.add(leftBeef);
        JMenuItem rightBeef = new JMenuItem("Right");
        bacon.add(rightBeef);
        JMenuItem fullBeef = new JMenuItem("Full");
        bacon.add(fullBeef);
        JCheckBoxMenuItem extraBeef = new JCheckBoxMenuItem("Extra");
        bacon.add(extraBeef);

        //vegetables
        JMenuBar vegetablesBar = new JMenuBar();
        frame.setJMenuBar(vegetablesBar);
        JMenu vegetablesMenu = new JMenu("Vegetables");
        vegetablesBar.add(vegetablesMenu);
        JMenu spinach = new JMenu("Spinach");
        vegetablesMenu.add(spinach);
        JMenu onions = new JMenu("Onions");
        vegetablesMenu.add(onions);
        JMenu olives = new JMenu("Olives");
        vegetablesMenu.add(olives);
        JMenu peppers = new JMenu("Peppers");
        vegetablesMenu.add(peppers);

        //vegetable submenus
        //spinach
        JMenuItem leftSpinach = new JMenuItem("Left");
        spinach.add(leftSpinach);
        JMenuItem rightSpinach = new JMenuItem("Right");
        spinach.add(rightSpinach);
        JMenuItem fullSpinach = new JMenuItem("Full");
        spinach.add(fullSpinach);
        JCheckBoxMenuItem extraSpinach = new JCheckBoxMenuItem("Extra");
        spinach.add(extraSpinach);

        //onions
        JMenuItem leftOnions = new JMenuItem("Left");
        onions.add(leftOnions);
        JMenuItem rightOnions = new JMenuItem("Right");
        onions.add(rightOnions);
        JMenuItem fullOnions = new JMenuItem("Full");
        onions.add(fullOnions);
        JCheckBoxMenuItem extraOnions = new JCheckBoxMenuItem("Extra");
        onions.add(extraOnions);

        //olives
        JMenuItem leftOlives = new JMenuItem("Left");
        olives.add(leftOlives);
        JMenuItem rightOlives = new JMenuItem("Right");
        olives.add(rightOlives);
        JMenuItem fullOlives = new JMenuItem("Full");
        olives.add(fullOlives);
        JCheckBoxMenuItem extraOlives = new JCheckBoxMenuItem("Extra");
        olives.add(extraOlives);

        //peppers
        JMenuItem leftPeppers = new JMenuItem("Left");
        peppers.add(leftPeppers);
        JMenuItem rightPeppers = new JMenuItem("Right");
        peppers.add(rightPeppers);
        JMenuItem fullPeppers = new JMenuItem("Full");
        peppers.add(fullPeppers);
        JCheckBoxMenuItem extraPeppers = new JCheckBoxMenuItem("Extra");
        peppers.add(extraPeppers);
    }
}
