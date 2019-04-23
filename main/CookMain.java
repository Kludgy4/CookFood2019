package main;

import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main class for CookFood
 * Creates the application object
 */
public class CookMain {
	public static CookManager app;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		//app = new CookManager();
		
		ArrayList<Recipe> test = new ArrayList<>();
		Recipe test1 = new Recipe();
		Ingredient test11 = new Ingredient();
		test11.name = "Pasta";
		test11.quantity = 1;
		test11.quantityType = QuantityType.POUNDS;
		Ingredient test12 = new Ingredient();
		test12.name = "Tomatoes";
		test12.quantity = 5;
		test12.quantityType = QuantityType.WHOLE;
		Ingredient test13 = new Ingredient();
		test13.name = "Thyme";
		test13.quantity = 3;
		test13.quantityType = QuantityType.KILOGRAMS;
		test1.ingredients.add(test11);
		test1.ingredients.add(test12);
		test1.ingredients.add(test13);
		
		Recipe test2 = new Recipe();
		Ingredient test21 = new Ingredient();
		test21.name = "Carrot";
		test21.quantity = 42;
		test21.quantityType = QuantityType.SMIDGENS;
		Ingredient test22 = new Ingredient();
		test22.name = "Meat";
		test22.quantity = 35;
		test22.quantityType = QuantityType.DRAMS;
		Ingredient test23 = new Ingredient();
		test23.name = "Shrimp";
		test23.quantity = 4;
		test23.quantityType = QuantityType.LITRES;
		test2.ingredients.add(test21);
		test2.ingredients.add(test22);
		test2.ingredients.add(test23);
		
		test2.title = "Shrimp";
		test2.cookbook = "Shrimp Bonanza";
		test1.title = "Yeet";
		test1.cookbook = "Yeet Party";
		
		test.add(test1);
		test.add(test2);
		
		PnlMain foo = new PnlMain();
		foo.generateShoppingList(test);
	}
	
}
