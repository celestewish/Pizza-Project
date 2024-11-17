import javax.swing.*;
import java.awt.*;

public class PaymentScreen extends CardScreen {
    private JPanel pnlPaymentScreen;
    private JButton HomeBtn;
    private JButton menuButton;
    private JButton DealsBTN;
    private JButton LocationsBTN;
    private JLabel nameShown;
    private JLabel streetName;
    private JLabel cityStateZip;
    private JTextField firstLastTextField;
    private JTextField cardNumberTextField;
    private JTextField CVVTextField;
    private JTextField expDateTextField;
    private JTextField zipCodeTextField;
    private JButton submitPaymentButton;

    public PaymentScreen(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info){
        super(screenLayoutController, screenContainer, info);

        String[] name = info.getName().split(" ");
        String[] custAddress = info.getAddress().split(" ");



        nameShown.setText(name[0]);
        streetName.setText(custAddress[0] + " "  + custAddress[1] + " " + custAddress[2]);
        cityStateZip.setText(custAddress[3] + " " + custAddress[4] + " " + custAddress[5]);

        HomeBtn.addActionListener(e -> showScreen("StartScreen"));
        menuButton.addActionListener(e -> showScreen("Menu"));
        DealsBTN.addActionListener(e -> showScreen("Deals"));
        LocationsBTN.addActionListener(e -> showScreen("Location"));
        submitPaymentButton.addActionListener((e) -> {
            String cardNameInput = firstLastTextField.getText();
            String cardNumberInput = null;
            String expDateInput = null;
            String zipCodeInput = null;
            int cvvInput = 0;
            try{
                cardNumberInput = cardNumberTextField.getText();
                if(!cardNumberInput.matches("\\d+" )){
                    throw new IllegalArgumentException("Valid characters only");
                }

            }
            catch(IllegalArgumentException ex){
                JFrame frame = new JFrame("Error");
                frame.setSize(200,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(frame, "Invalid input! PLease enter a card number", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(true);
            }
            try{

                cvvInput = Integer.parseInt(CVVTextField.getText());
                if((!String.valueOf(cvvInput).matches("\\d+" )) || String.valueOf(cvvInput).length() <3 ){
                    throw new IllegalArgumentException("Too many characters or illegal characters");
                }

            }
            catch(IllegalArgumentException ex){
                JFrame frame = new JFrame("Error");
                frame.setSize(200,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(frame, "Invalid input! PLease enter the correct cvv", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(true);
            }

            try{
                expDateInput = expDateTextField.getText();
                if(!isValidDate(expDateInput)){
                    throw new IllegalArgumentException("Not a valid date.");
                }
            }
            catch(IllegalArgumentException ex){
                JFrame frame = new JFrame("Error");
                frame.setSize(200,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(frame, "Invalid input! PLease enter a correct date", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(true);
            }
            try{
                zipCodeInput = zipCodeTextField.getText();
                if((zipCodeInput.length() != 6) && !zipCodeInput.matches("\\d+")){
                    throw new IllegalArgumentException("Invalid zip code.");
                }
            }
            catch (IllegalArgumentException ex){
                JFrame frame = new JFrame("Error");
                frame.setSize(200,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(frame, "Invalid input! PLease enter a correct zip code.", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(true);
            }

            showScreen("Payment_Receipt");

        });
    }

    public boolean isValidDate(String date){
        String dateRegex = "^(0[1-9]|1[0-2])/\\d{2}$";

        if(!date.matches(dateRegex)){
            return false;
        }

        String[] parts = date.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);

        if(month<1 || month>12){
            return false;
        }

        return year >= 0 && year <= 99;
    }

    public JPanel getScreenPanel(){return pnlPaymentScreen;}

}
