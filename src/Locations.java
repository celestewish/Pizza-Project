import javax.swing.*;
import java.awt.*;

public class Locations extends CardScreen{
    private JPanel pnlLocations;
    private JButton homeButton;
    private JButton menuButton;
    private JButton locationsButton;
    private JPanel Bottom;
    private JButton dealsButton;

    public Locations(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);

        homeButton.addActionListener(e -> showScreen("StartScreen"));
        menuButton.addActionListener(e -> showScreen("MenuGUI"));
        dealsButton.addActionListener(e -> showScreen("Deals"));
        locationsButton.addActionListener(e -> showScreen("Locations"));
    }
    
    public JPanel getScreenPanel() {
        return pnlLocations;
    }
}
