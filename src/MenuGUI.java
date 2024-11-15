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

    public MenuGUI(CardLayout screenLayoutController, JPanel screenContainer) {
        super(screenLayoutController, screenContainer);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showScreen("");
            }
        });
    }
    public JPanel getScreenPanel(){
        return panel1;
    }
}
