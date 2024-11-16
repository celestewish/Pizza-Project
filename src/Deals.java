import javax.swing.*;
import java.awt.*;

public class Deals extends CardScreen{
    private JPanel panel1;
    private JButton btnHome;
    private JButton btnDeals;
    private JButton btnMenu;
    private JButton btnLocations;
    private JButton btnSignIn;

    public Deals(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer);

        btnHome.addActionListener(e -> showScreen("MenuGUI"));

        btnDeals.addActionListener(e -> showScreen("Deals"));

        btnMenu.addActionListener(e -> showScreen("MenuGUI"));

        btnLocations.addActionListener(e -> showScreen("Locations"));

        btnSignIn.addActionListener(e -> showScreen("SignIn"));
    }
}
