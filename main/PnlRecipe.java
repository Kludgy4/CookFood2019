package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PnlRecipe extends JPanel {

	public PnlRecipe(Recipe recipeInfo) {
		saveRecipe(recipeInfo);
	}
	
	public PnlRecipe() {
		
	}

	public void saveRecipe(Recipe recipeInfo) {
		//Reserves the relevant directory and file for recipe output
		File file = new File((getClass().getResource("../") + "CookFoodRecipes").substring(6));
		System.out.println("Exporting to " + file);
		file.mkdirs();
		file = new File((getClass().getResource("../") + "CookFoodRecipes/" + recipeInfo.title + ".ckf").substring(6));
		try {
			FileWriter writer = new FileWriter(file);
			writer.append(recipeInfo.title + "," + recipeInfo.cookbook + "\n");
			for (Ingredient i : recipeInfo.ingredients) {
				System.out.println(i.quantity);
				writer.append(i.name + "," + i.quantity + "," + i.quantityType.getType() + "\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	public ArrayList<Recipe> retrieveRecipes() {
		BufferedReader reader = null;
		ArrayList<File> recipeFiles = getFolderFiles(new File((getClass().getResource("../") + "CookFoodRecipes").substring(6)));
		ArrayList<Recipe> recipes = new ArrayList<>();
		for (File f : recipeFiles) {
			try {
				reader = new BufferedReader(new FileReader(f));
				Recipe recipe = new Recipe();
				String[] inputData = reader.readLine().split(",");
				recipe.title = inputData[0];
				recipe.cookbook = inputData[1];
				
				String input = reader.readLine();
				while (input != null) {
					inputData = input.split(",");
					recipe.ingredients.add(new Ingredient(inputData[0], Integer.parseInt(inputData[1]), QuantityType.valueOf(inputData[2].toUpperCase())));
					input = reader.readLine();
				}
				recipes.add(recipe);
			} catch (FileNotFoundException e) {} catch (IOException e) {e.printStackTrace();}
			
		}
		return recipes;
	}
	
	public static ArrayList<File> getFolderFiles(File folder) {
		ArrayList<File> files = new ArrayList<>();
		for (File fileEntry : folder.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	files.add(fileEntry.getAbsoluteFile());
	        }
	    }
		return files;
	}
	
}
