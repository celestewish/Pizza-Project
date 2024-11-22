import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Deals class represents the deals screen in the application.
 * It displays promotional deals and allows users to navigate to other screens,
 * including the sign-in screen or user-specific deals screen if logged in.
 */
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

    /**
     * Constructor for the Deals screen.
     *
     * @param screenLayoutController The CardLayout controller for managing screen transitions.
     * @param screenContainer        The container holding all screens.
     * @param panelName              The unique name for this panel.
     */
    public Deals(CardLayout screenLayoutController, JPanel screenContainer, String panelName) {
        super(screenLayoutController, screenContainer, panelName);
        setScreenPanel(pnlDeals);
        info.registerScreenName(Screen.DEALS, this);
        screenContainer.add(this.getScreenPanel(), this.getPanelName());
        //sets up the buttons seen at the top of the screen
        setUpNavBar_LoggedOut(btnHome, btnMenu, btnDeals, btnLocations, btnSignUp_SignIn);
        //button to create an account if needed
        btnCreateAccount.addActionListener(_ -> showScreen(Screen.SIGN_UP));
        btnOrdernow1.addActionListener(_ -> showScreen(Screen.SIGN_IN));
        btnOrdernow2.addActionListener(_ -> showScreen(Screen.SIGN_IN));
    }

    /**
     * Allows the user to leave the current screen.
     *
     * @param destinationScreen The screen the user is navigating to.
     * @return Always returns true to allow navigation.
     */
    @Override
    public boolean onAttemptLeaveScreen(Screen destinationScreen) {
        return true;
    }

    /**
     * Redirects the user to a different deals screen if they are logged in.
     *
     * @param toScreen The screen the user is attempting to navigate to.
     * @return The user-specific deals screen if logged in; otherwise, the requested screen.
     */
    @Override
    public Screen onAttemptEnterScreen(Screen toScreen) {
        if (info.isLoggedIn())
            return Screen.DEALS_USER;
        return toScreen;
    }

    /**
     * Handles logic for setting up the screen when entering it.
     * This method is currently empty but can be extended for additional setup tasks.
     */
    @Override
    public void onEnterScreen() {
    
    }

    /**
     * Initializes custom UI components, such as the logo panel.
     */
    private void createUIComponents() {
        pnlLogo = new ImagePanel("PizzaLogo.png");
    }
}
