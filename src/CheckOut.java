import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CheckOut extends CardScreen {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField yourInfoTextField;
    private JTextField firstNameDis;
    private JTextField lastNameDis;
    private JTextField textField3;
    private JTextField textField4;
    private JButton editButton;


    private ProgramInfo info = new ProgramInfo();


    public CheckOut(CardLayout screenLayoutController, JPanel screenContainer, ProgramInfo info) {
        super(screenLayoutController, screenContainer, info);

        //array to store customer name to display, index 0 is  first name,
        //index 1 is last name
        String[] name = info.getName().split(" ");
        firstNameDis.setText(name[0]);
        lastNameDis.setText(name[1]);
        textField3.setText(info.getPhoneAtIndex0());
        textField4.setText(info.getEmail());

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String firstNameInput = firstNameDis.getText();
                String lastNameInput = lastNameDis.getText();
                String phoneNumberInput = textField3.getText();
                String emailInput = textField4.getText();

                name[0] = firstNameInput;
                name[1] = lastNameInput;
                Boolean phoneAdd = info.addPhone(phoneNumberInput);
            }
        });
    }
    
    public JPanel getScreenPanel() {return panel1;}


}
