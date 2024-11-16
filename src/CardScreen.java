import javax.swing.*;
import java.awt.*;

public abstract class CardScreen {
	private final CardLayout screenLayoutController;
	private final JPanel screenContainer;
	private final ProgramInfo info;
	
	public CardScreen(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
		this.screenLayoutController = screenLayoutController;
		this.screenContainer = screenContainer;
		this.info = info;
	}
	
	public void showScreen(String pnlName) {
		screenLayoutController.show(screenContainer, pnlName);
	}
	
	public boolean isTextEmpty (JTextField t, String message, String title, int optionPaneType) {
		if (t.getText().isBlank()) {
			JOptionPane.showMessageDialog(
					null,
					message,
					title,
					optionPaneType);
			return true;
		}
		return  false;
	}
	
	public boolean isFTextEmpty (JFormattedTextField t, String message, String title, int optionPaneType) {
		if (t.getText().isBlank()) {
			JOptionPane.showMessageDialog(
					null,
					message,
					title,
					optionPaneType);
			return true;
		}
		return  false;
	}
	
	public boolean isComboBoxUnselected (JComboBox t, String message, String title, int optionPaneType) {
		if (t.getSelectedItem() == "...") {
			JOptionPane.showMessageDialog(
					null,
					message,
					title,
					optionPaneType);
			return true;
		}
		return  false;
	}
}
