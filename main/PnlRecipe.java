package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PnlRecipe extends JPanel {

	public PnlRecipe() {
		
	}
	
	public PnlRecipe(Recipe recipeInfo) {
		
	}

	public void saveRecipe(Recipe recipe) {
		//Reserves/Creates the relevant directory and file for recipe output
		File file = new File((getClass().getResource("../") + "CookFoodRecipes").substring(6));
		file.mkdirs();
		file = new File((getClass().getResource("../") + "CookFoodRecipes/" + recipe.title + ".ckf").substring(6));
		
		//Writes recipe information to the given file
		try {
			FileWriter writer = new FileWriter(file);
			writer.append(recipe.title + "," + recipe.cookbook + "\n");
			for (Ingredient i : recipe.ingredients) {
				writer.append(i.name + "," + i.quantity + "," + i.quantityType.getMultipleType() + "\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("There was an error saving your recipe");
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Recipe> retrieveRecipes() {
		BufferedReader reader = null;
		ArrayList<File> recipeFiles = getFolderFiles();
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
	
	public ArrayList<File> getFolderFiles() {
		ArrayList<File> files = new ArrayList<>();
		for (File fileEntry : (new File((getClass().getResource("../") + "CookFoodRecipes").substring(6))).listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	files.add(fileEntry.getAbsoluteFile());
	        }
	    }
		return files;
	}
	
	public boolean deleteRecipe(String title) {
		ArrayList<File> files = getFolderFiles();
		int i = 0;
		boolean foundIt = false;
		
		//Search for the given string until none left or found		
		while (!foundIt && i < files.size()) {
			if (files.get(i).getName() == title) {
				foundIt = true;
			} else {
				i++;
			}
		}
		
		//If the file was found, then delete it from the system
		if (foundIt) {
			files.get(i).delete();
			return true;
		} else {
			return false;
		}
	}
	
}
