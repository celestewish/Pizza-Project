import javax.swing.*;
import java.awt.*;

public abstract class CardScreen {
	private final CardLayout screenLayoutController;
	private final JPanel screenContainer;
	
	public CardScreen(CardLayout screenLayoutController, JPanel screenContainer) {
		this.screenLayoutController = screenLayoutController;
		this.screenContainer = screenContainer;
	}
	
	public void showScreen(String pnlName) {
		screenLayoutController.show(screenContainer, pnlName);
	}
}
