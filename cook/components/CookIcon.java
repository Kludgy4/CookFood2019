package cook.components;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public enum CookIcon {
	ADD("add"),
	DELETE("delete"),
	EDIT("edit"),
	GENERATE("generate"),
	SUBMIT("generate"),
	TEMP("404");

	private final String location;

	private CookIcon(String location) {
		this.location = location;
	}
	
	/**
	 * The method tries to open an buttonIcon, and if it can't, it defaults to a 404
	 *  @return The relevant buttonIcon to the selected enum
	 */
	public BufferedImage getImage() {
		try {
			return ImageIO.read(getClass().getResource("/cook/resources/" + location + ".png"));
		} catch(Exception e) {
			System.out.println(location + " picture not found");
			try {
				return ImageIO.read(getClass().getResource("/cook/resources/404.png"));
			} catch(Exception e1) {
				System.out.println("There was an error loading the 404 picture...");
				e1.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * This method automates the creation of a CookButton to be added to the UI
	 * @param buttonText The text to be displayed on the button
	 * @return A new created CookButton object
	 */
	public CookButton getCookButton(String buttonText) {
		//Creates and sets basic CookButton properties
		CookButton button = new CookButton(this);
		button.setText(buttonText);
		button.setIcon(new ImageIcon(this.getImage()));
		
		//Sets the properties of each CookButton
		button.setAlignmentX(0f);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		return button;
	}
}
