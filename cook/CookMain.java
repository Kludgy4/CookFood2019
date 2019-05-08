package cook;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cook.main.frames.FrMain;
import cook.recipe.frames.FrRecipe;

/**
 * Main class for CookFood
 * Creates the application object
 */
public class CookMain {
	public static FrMain app;
	public static FrRecipe recipe;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		//Creates the application window ready for event-driven processing
		app = new FrMain();
	}
}

