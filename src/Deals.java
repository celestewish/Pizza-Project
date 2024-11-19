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
    private JPanel pnlLogo;
    
    public Deals(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info, String panelName) {
        super(screenLayoutController, screenContainer, info, panelName);
        setScreenPanel(pnlDeals);
        info.registerScreenName(Screen.DEALS, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());

        setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
    }
    
    @Override
    public boolean onAttemptLeaveScreen(ProgramInfo info) {
        return true;
    }
    
    @Override
    public Screen onAttemptEnterScreen(ProgramInfo info, Screen toScreen) {
        if (info.isLoggedIn())
            return Screen.DEALS_USER;
        return toScreen;
    }
    
    @Override
    public void onEnterScreen(ProgramInfo info) {
    
    }
    
    private void createUIComponents() {
        pnlLogo = new ImagePanel("src/main/resources/images/PizzaLogo.png");
    }
}
