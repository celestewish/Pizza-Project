import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
	private Image image;
	
	// Constructor to load an image from a file
	public ImagePanel(String imageFileName) {
		try {
			image = ImageIO.read(new File("src/main/resources/images/" + imageFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			// Draw the image to fit the panel size
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
