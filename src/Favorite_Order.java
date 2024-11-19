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
    private JComboBox cboxCrust;
    private JCheckBox selectCheckBox;

    public Favorite_Order(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
        super(screenLayoutController, screenContainer, info, panelName);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    @Override
    public boolean onAttemptLeaveScreen(ProgramInfo info) {
        return false;
    }

    @Override
    public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
        return null;
    }

    @Override
    public void onEnterScreen(ProgramInfo info) {

    }
}
