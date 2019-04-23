package main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public enum Icons {
	EXIT("exit"),
	FILE("file"),
	GENERATE("generate"),
	OPEN("open"),
	SAVE("save"),
	TEMP("404");

	private final String location;

	private Icons(String location) {
		this.location = location;
	}
	
	/**
	 * The method tries to open an image, and if it can't, it defaults to a 404
	 *  @return The relevant image to the selected enum
	 */
	public BufferedImage getImage() {
		try {
			return ImageIO.read(getClass().getResource("../res/" + location + ".png"));
		} catch(Exception e) {
			System.out.println(location + " picture not found");
			try {
				return ImageIO.read(getClass().getResource("../res/404.png"));
			} catch(Exception e1) {
				System.out.println("There was an error loading the 404 picture...");
				e1.printStackTrace();
				return null;
			}
		}
	}
}
