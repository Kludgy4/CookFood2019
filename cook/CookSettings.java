package cook;

import java.awt.Color;

public class CookSettings {
	public static Color 
		colourBackground = new Color(0x666566),
		colourSuccess = new Color(0x34B234),
		colourWarning = new Color(0xE8B130),
		colourError = new Color(0xC91D1D);

	public static Color 
		neutral1 = new Color(0xF2F2F3),
		neutral2 = new Color(0xC8CDD0),
		neutral3 = new Color(0x415058),
		neutral4 = new Color(0x1F292E);
	
	//This is where all CookFood files will be saved on the system
	public static final String
		savePath = CookSettings.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "CookFoodRecipes";
}
