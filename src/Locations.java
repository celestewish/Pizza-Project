import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Locations extends CardScreen{
    private JPanel Top;
    private JButton homeButton;
    private JButton menuButton;
    private JButton locationsButton;
    private JPanel Bottom;
    private JTextField momSAndPopTextField;
    private JTextField a680ArnstonRdSuiteTextField;
    private JTextField phone7705551212TextField;
    private JTextField operatingHoursTextField;
    private JTextField monThur9am11pmTextField;
    private JTextField friSat11am12amTextField;
    private JTextField mariettaGA30060TextField;
    private JButton dealsButton;

    public Locations(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);

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
            public void actionPerformed(ActionEvent actionEvent) {
                showScreen("Locations");
            }
        });
    }


}
