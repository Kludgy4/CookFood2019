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
	
	public void resizeIcon(Dimension frameSize, Dimension screenSize, double scalePercentage){

		int newWidth, newHeight;
		double imageAspect = buttonIcon.getWidth()/buttonIcon.getHeight();
		
		//Determines the new button icon dimensions
		newHeight = (int)(buttonIcon.getHeight()*(frameSize.getHeight()/screenSize.getHeight())*scalePercentage);
	    newWidth = (int) (newHeight*(1/imageAspect));
		
	    //Create a new resized image in the "resizedImg" variable
	    if (newWidth > 0 && newHeight > 0) {
	    	BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TRANSLUCENT);
		    Graphics2D g2 = resizedImg.createGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(buttonIcon, 0, 0, newWidth, newHeight, null);
		    g2.dispose();
		    //Sets the new icon image to be the resized image
		    setIcon(new ImageIcon(resizedImg));
	    } else {
	    	setIcon(new ImageIcon(buttonIcon));
	    }
	}
}
