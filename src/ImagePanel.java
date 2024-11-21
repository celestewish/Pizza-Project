import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Custom JPanel subclass that displays an image
public class ImagePanel extends JPanel {
	private Image image;  // The image to be displayed

	// Constructor to load an image from a file specified by the image file name
	public ImagePanel(String imageFileName) {
		try {
			// Reads the image file from the specified path and loads it into the 'image' variable
			image = ImageIO.read(new File("src/main/resources/images/" + imageFileName));
		} catch (IOException e) {
			// Print the stack trace if an error occurs while loading the image
			e.printStackTrace();
		}
	}

	// Overridden method to paint the component (draw the image)
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);  // Call the superclass's paintComponent method
		if (image != null) {
			// Draw the image to fit the size of the panel
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}