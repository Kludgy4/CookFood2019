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

		app = new CookManager();
		ArrayList<Recipe> test = new ArrayList<>();
		Recipe test1 = new Recipe();
		Ingredient test11 = new Ingredient();
		test11.name = "Pasta";
		Ingredient test12 = new Ingredient();
		test12.name = "Tomatoes";
		Ingredient test13 = new Ingredient();
		test13.name = "Thyme";
		test1.ingredients.add(test11);
		test1.ingredients.add(test12);
		test1.ingredients.add(test13);
		
		Recipe test2 = new Recipe();
		Ingredient test21 = new Ingredient();
		test21.name = "Carrot";
		Ingredient test22 = new Ingredient();
		test22.name = "Meat";
		Ingredient test23 = new Ingredient();
		test23.name = "]";
		test2.ingredients.add(test21);
		test2.ingredients.add(test22);
		test2.ingredients.add(test23);
		
		test.add(test1);
		test.add(test2);
		
		for (Ingredient i : PnlMain.arrangeIngredients(test)) {
			System.out.print(i.name + ", ");
		}
		
	}
}
