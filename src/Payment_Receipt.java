import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment_Receipt extends CardScreen{


    private JPanel Home;
    private JButton btnHome;

    public Payment_Receipt(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);
        btnHome.addActionListener(e -> showScreen("StartScreen"));
    }
}
