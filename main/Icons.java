package main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public enum Icons {
	FILE("file"),
	EXPORT("export"),
	EXIT("exit"),
	OPEN("open"),
	SAVE("save"),
	TEMP("404");
	

	private final String location;

	private Icons(String location) {
		this.location = location;
	}
	
	/**
	 * 
	 * @return an Image
	 * The methods tries different possible variations of the file before resulting to a default image.
	 * This should always be used when attempting to get an image
	 */
	public BufferedImage getImage() {
		try {
			return ImageIO.read(getClass().getResource("../res/" + location + ".png"));
		}
		catch(Exception e1) {
			try {
				return ImageIO.read(getClass().getResource("../res/404.png"));
			}
			catch(Exception e2) {
				e2.printStackTrace();
				return null;
			}
		}
	}
}
