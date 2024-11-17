import javax.swing.*;
import java.awt.*;

public class Deals extends CardScreen{
    private JPanel pnlDeals;
    private JButton btnHome;
    private JButton btnDeals;
    private JButton btnMenu;
    private JButton btnLocations;
    private JButton btnSignIn;
    private JButton btnOrdernow1;
    private JButton btnOrdernow2;

    public Deals(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);

        btnHome.addActionListener(e -> showScreen("StartScreen"));

        btnDeals.addActionListener(e -> showScreen("Deals"));

        btnMenu.addActionListener(e -> showScreen("Menu"));

        btnLocations.addActionListener(e -> showScreen("Locations"));

        btnSignIn.addActionListener(e -> showScreen("SignIn"));
    }

    public JPanel getScreenPanel() {
        return pnlDeals;
    }
}
