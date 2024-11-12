package com.example.pizzaproject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	private BufferedImage image;
	
	// Constructor with image path parameter
	public ImagePanel(String imagePath) {
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this); // Scale to fit panel
		}
	}
}
