import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class MenuGUI {
    JFrame frame;
    private JLabel statusLabel;
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLabel menuTitle = new JLabel();
        menuTitle.setText("Menu");
        frame.add(menuTitle);
        JButton viewOrder = new JButton("View Order");
        frame.add(viewOrder);
        JButton createPizza = new JButton("Create Your Own Pizza");
        frame.add(createPizza);
        createPizza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createPizza();
            }
        });
    }
    public static void createPizza() {
        JFrame frame = new JFrame();
        frame.setTitle("Create Your Own Pizza");
        JLabel menuTitle = new JLabel("Customize Your Pizza");
        frame.add(menuTitle);

        //crust sizes
        JLabel crustTitle = new JLabel("Crust");
        JButton thinCrust = new JButton("Thin Crust");
        frame.add(thinCrust);
        JButton thickCrust = new JButton("Thick Crust");
        frame.add(thickCrust);
        JButton deepDish = new JButton("Deep Dish");
        frame.add(deepDish);

        //pizza sizes
        JLabel sizeTitle = new JLabel("Size");
        frame.add(sizeTitle);
        JButton smallSizes = new JButton("Small");
        frame.add(smallSizes);
        JButton mediumSizes = new JButton("Medium");
        frame.add(mediumSizes);
        JButton largeSizes = new JButton("Large");
        frame.add(largeSizes);
        JButton exLargeSizes = new JButton("X-Large");
        frame.add(exLargeSizes);

        //pizza sauce
        JLabel sauceTitle = new JLabel("Sauce");
        frame.add(sauceTitle);
        JButton marinera = new JButton("Marinera");
        frame.add(marinera);
        JButton alfredo = new JButton("Alfredo");
        frame.add(alfredo);

        //cheese
        JLabel cheeseTitle = new JLabel("Cheese");
        frame.add(cheeseTitle);
        JButton regularCheese = new JButton("Regular");
    }
}
