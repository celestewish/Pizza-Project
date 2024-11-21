import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton btnCreateAccount;

    public Deals(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlDeals);
        info.registerScreenName(Screen.DEALS, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        //sets up the buttons seen at the top of the screen
        setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
        //button to create an account if needed
        btnCreateAccount.addActionListener(_ -> showScreen(Screen.SIGN_IN));
        btnOrdernow1.addActionListener(_ -> showScreen(Screen.SIGN_IN));
        btnOrdernow2.addActionListener(_ -> showScreen(Screen.SIGN_IN));
    }
    
    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        return true;
    }

    //if the user is logged in, they will be sent to a different screen
    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        if (info.isLoggedIn())
            return Screen.DEALS_USER;
        return toScreen;
    }
    
    @Override
    public void onEnterScreen() {
    
    }
    
    private void createUIComponents() {
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
