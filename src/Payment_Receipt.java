import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment_Receipt extends CardScreen{


    private JPanel Home;
    private JButton btnBack;

    public Payment_Receipt(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);
        btnBack.addActionListener(e -> showScreen("StartScreen"));
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
