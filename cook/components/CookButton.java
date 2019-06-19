package cook.components;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Extends the functionality of the default JButton to hold and automatically scale, an image/icon that is added to it
 */
@SuppressWarnings("serial")
public class CookButton extends JButton  {
	
	BufferedImage buttonIcon;
	
	/**
	 * A Button class with added automatic icon handling capability built-in
	 * @param cookIcon The icon to be added to the button
	 */
	public CookButton(CookIcon cookIcon) {
		this.buttonIcon = cookIcon.getImage();
	}
	
	/**
	 * Resizes the icon contained inside the button to match the size of its containing window. 
	 * Allows the button to be scaled up or down from the automatic sizing
	 * @param frameSize The size of the entire frame
	 * @param screenSize The size of the entire screen
	 * @param scalePercentage The percentage to scale the button size with
	 */
	public void resizeIcon(Dimension frameSize, Dimension screenSize, double scalePercentage){
		int newWidth, newHeight;
		double imageAspect = buttonIcon.getWidth()/buttonIcon.getHeight();
		
		//Determines the new button icon dimensions, while maintaining the original image aspect ratio
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
