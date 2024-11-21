import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * A custom JPanel subclass that is used to display an image on the panel.
 * This class loads and paints an image from a file to be shown within the panel.
 */
public class ImagePanel extends JPanel {
	private Image image;  // The image to be displayed

	/**
	 * Constructs an ImagePanel that loads an image from the specified file.
	 * The image is loaded from the resources folder using the provided file name.
	 *
	 * @param imageFileName The name of the image file to be loaded.
	 *                       The image file should be located in the "src/main/resources/images/" directory.
	 */
	public ImagePanel(String imageFileName) {
		try {
			// Reads the image file from the specified path and loads it into the 'image' variable
			image = ImageIO.read(new File("src/main/resources/images/" + imageFileName));
		} catch (IOException e) {
			// Print the stack trace if an error occurs while loading the image
			e.printStackTrace();
		}
	}

	/**
	 * Paints the component by drawing the loaded image to fit the size of the panel.
	 * This method is automatically called by the Swing framework when the panel is rendered.
	 *
	 * @param g The Graphics context to use for drawing the image.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);  // Call the superclass's paintComponent method
		if (image != null) {
			// Draw the image to fit the size of the panel
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}