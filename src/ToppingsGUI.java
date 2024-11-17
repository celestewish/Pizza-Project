import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToppingsGUI extends CardScreen {

    private JPanel panel1;
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


    public ToppingsGUI(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);

        btnHome.addActionListener(e -> showScreen("Deals"));

        btnDeals.addActionListener(e -> showScreen("Deals"));

        btnLocations.addActionListener(e -> showScreen("Locations"));

        btnMenu.addActionListener(e -> showScreen("Menu"));

        cartButton.addActionListener(e -> showScreen("Cart"));


    }

    public JPanel getScreenPanel(){
        return panel1;
    }
}
