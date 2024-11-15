import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CheckOut extends CardScreen{
    private JPanel panel1;
    private JButton homeButton;
    private JButton menuButton;
    private JButton locationsButton;
    private JTextField checkoutTextField;
    private JTextField yourInfoTextField;
    private JScrollBar scrollBar1;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField FirstNameDis;
    private JTextField LastNameDis;
    private JTextField emailTextField;
    private JTextField EmailDis;
    private JTextField phoneNumberTextField;

    public CheckOut(CardLayout screenLayoutController, JPanel screenContainer){
        super(screenLayoutController, screenContainer);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showScreen("StartScreen");
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showScreen("MenuGUI");
            }
        });
        locationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {showScreen("Locations");}
        });

    }



}
