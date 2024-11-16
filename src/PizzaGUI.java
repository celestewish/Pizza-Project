import javax.swing.*;
import java.awt.*;

public class PizzaGUI extends CardScreen{
    private JPanel panel1;
    private JTextArea createYourOwnTextArea;
    private JTextArea customizeYourPizzaTextArea;
    private JTextArea crustTextArea;
    private JComboBox comboBox1;
    private JTextArea sizeTextArea;
    private JComboBox comboBox2;
    private JTextArea sauceTextArea;
    private JComboBox comboBox3;
    private JTextArea cheeseTextArea;
    private JCheckBox yesCheeseCheckBox;

    public PizzaGUI(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer);
    }
    public JPanel getScreenPanel(){
        return panel1;
    }
}
