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
}
