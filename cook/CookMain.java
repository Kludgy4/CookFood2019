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
	//NOTICE: Required SDD project structure - Modular, with use of a large array of objects and parameters used
	public static FrMain app;
	public static FrRecipe recipe;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		//Creates the application window ready for event-driven processing
		recipe = new FrRecipe();
		recipe.dispose();
		app = new FrMain();
	}
}

