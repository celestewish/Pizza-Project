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
        dealsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showScreen("Deals");
            }
        });
    }


}
