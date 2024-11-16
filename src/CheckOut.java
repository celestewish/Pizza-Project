import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CheckOut extends CardScreen {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField firstNameDis;
    private JTextField lastNameDis;
    private JTextField textField3;
    private JTextField textField4;
    private JButton editButton;
    private JButton dealsButton;
    private JButton proceedToPaymentButton;
    private JLabel welcome;
    private JLabel userName;
    private JLabel FirstName;


    public CheckOut(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);

        //array to store customer name to display, index 0 is  first name,
        //index 1 is last name
        String[] name = info.getName().split(" ");
        firstNameDis.setText(name[0]);
        lastNameDis.setText(name[1]);
        textField4.setText(info.getPhoneAtIndex0());
        textField3.setText(info.getEmail());
        userName.setText(name[0]);

        editButton.addActionListener((_) -> {
            String phoneNumberInput = null;

            String firstNameInput = firstNameDis.getText();
            String lastNameInput = lastNameDis.getText();
            String emailInput = textField3.getText();
            try{
                phoneNumberInput = textField4.getText();

            }
            catch(NumberFormatException ex){
                JFrame frame = new JFrame("Error");
                frame.setSize(400,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JOptionPane.showMessageDialog(frame, "Invalid input! PLease enter a valid phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                frame.setVisible(true);
            }




            name[0] = firstNameInput;
            name[1] = lastNameInput;
            Boolean phoneAdd = info.addPhone(phoneNumberInput);
            info.setEmail(emailInput);
            info.setName(name[0] + " " + name[1]);

            firstNameDis.setText(name[0]);
            lastNameDis.setText(name[1]);
            textField4.setText(info.getPhoneAtIndex0());
            textField3.setText(info.getEmail());

        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }


    
    public JPanel getScreenPanel() {return panel1;}


}
