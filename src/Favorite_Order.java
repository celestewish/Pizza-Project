import javax.swing.*;
import java.awt.*;

public class Favorite_Order extends CardScreen{
    private JPanel pnlMenu;
    private JButton btnHome;
    private JButton btnMenu;
    private JButton btnDeals;
    private JButton btnLocations;
    private JButton btnSignOut;
    private JButton btnCart;
    private JLabel lblHiName;
    private JLabel lblCurTotal;
    private JButton returnButton;
    private JButton btnViewOrder;
    private JButton btnOrders;

    public Favorite_Order(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        return false;
    }

    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        return null;
    }

    @Override
    public void onEnterScreen() {

    }
}
