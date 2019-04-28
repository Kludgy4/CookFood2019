package main;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class CookButton extends JButton  {
	
	BufferedImage buttonIcon;
	
	public CookButton(Icon icon) {
		this.buttonIcon = icon.getImage();
	}
	
	public void resizeIcon(Dimension dimension, double widthPercentage, double heightPercentage){
	    //Calculates the desired width of the button
		int newWidth = (int)(buttonIcon.getWidth()*widthPercentage);
	    int newHeight = (int)(buttonIcon.getHeight()*heightPercentage);
		
	    //Create a new resized image in the "resizedImg" variable
	    BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(buttonIcon, 0, 0, newWidth, newHeight, null);
	    g2.dispose();
	    
	    //Sets the new icon image to be the resized image
	    setIcon(new ImageIcon(resizedImg));
	}
}
