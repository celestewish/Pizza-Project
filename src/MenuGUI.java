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
    private JButton addButton;
    private JButton addButton1;
    private JScrollBar scrollBar1;
    private JTextArea chocolateLavaCakeTextArea;
    private JTextArea $PriceTextArea4;
    private JComboBox comboBox4;
    private JButton addButton2;
    private JTextArea garlicKnotsTextArea;
    private JTextArea $PriceTextArea5;
    private JComboBox comboBox5;
    private JButton addButton3;
    private JButton addButton4;

    public MenuGUI(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);
        createButton.addActionListener(_ -> showScreen("PizzaGUI"));
        homeButton.addActionListener(_ -> showScreen("StartScreen"));
        addButton.addActionListener(_ -> {
            Drink drink = (Drink) Add.getSelectedItem();
        });
        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dessert dessert = (Dessert) Add.getSelectedItem();
            }
        });
        addButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Side wings = (Side) Add.getSelectedItem();
            }
        });
        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Side garlicBread = (Side) Add.getSelectedItem();
            }
        });
        addButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Side garlicKnots = (Side) Add.getSelectedItem();
            }
        });
    }
    public JPanel getScreenPanel(){
        return panel1;
    }
}
