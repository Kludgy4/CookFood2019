package cook;

import java.awt.Color;

public class CookSettings {
	public static Color 
		colBlack = new Color(0xFFFFFF),
		colRed = new Color(0x912019),
		colGreenTrans = new Color(0x77a7e6a9, true);
	
	public static final String
		savePath = CookSettings.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "CookFoodRecipes";
}
