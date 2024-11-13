import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Home h = new Home();
			h.setContentPane(h.getPanelHome());
			h.setTitle("Mom and Pop's Shop");
			h.setSize(1200, 800);
			h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			h.setLocationRelativeTo(null);
			h.setVisible(true);
		});
	}
}
