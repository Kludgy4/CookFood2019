package main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public enum Icon {
	ADD("add"),
	EDIT("edit"),
	GENERATE("generate"),
	REMOVE("remove"),
	SUBMIT("generate"),
	TEMP("404");

	private final String location;

	private Icon(String location) {
		this.location = location;
	}
	
	/**
	 * The method tries to open an buttonIcon, and if it can't, it defaults to a 404
	 *  @return The relevant buttonIcon to the selected enum
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
