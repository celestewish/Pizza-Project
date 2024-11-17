import javax.swing.*;
import java.awt.*;

public class Payment_Receipt extends CardScreen{
	private JPanel pnlHome;
    private JButton btnHome;
	private JLabel lblOrderNumberField;
	private JLabel lblTotalField;
	
	public Payment_Receipt(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);
        btnHome.addActionListener(_ -> showScreen("StartScreen"));
		
    }
	
	public JPanel getScreenPanel () {
		return pnlHome;
	}
}
