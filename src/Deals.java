import javax.swing.*;
import java.awt.*;

public class Deals extends CardScreen{
    private JPanel pnlDeals;
    
    private JButton btnHome;
    private JButton btnDeals;
    private JButton btnMenu;
    private JButton btnLocations;
    private JButton btnSignUp_SignIn;
    
    private JButton btnOrdernow1;
    private JButton btnOrdernow2;
    
    public Deals(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
        super(screenLayoutController, screenContainer, info, panelName);
        setScreenPanel(pnlDeals);
        info.registerScreenName(Screen.DEALS, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());

        setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
    }
    
    @Override
    public Screen onAttemptLeaveScreen(ProgramInfo info, Screen fromScreen) {
        return fromScreen;
    }
    
    @Override
    public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
        return toScreen;
    }
}
