import javax.swing.*;
import java.awt.*;

public class Location extends CardScreen{
    private JPanel pnlLocation;
    private JButton homeButton;
    private JButton menuButton;
    private JButton locationsButton;
    private JPanel Bottom;
    private JButton dealsButton;

    public Location(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);

        homeButton.addActionListener(e -> {
            showScreen("StartScreen");
        });
        menuButton.addActionListener(e -> {
            showScreen("Menu");
        });
        dealsButton.addActionListener(e -> {
            showScreen("Deals");
        });
        locationsButton.addActionListener(e -> {
            showScreen("Location");
        });
    }
    
    public JPanel getScreenPanel() {
        return pnlLocation;
    }
}
