package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeInterface {
	
	public RecipeInterface() {
		
	}
	
	public ArrayList<Recipe> retrieveRecipes() {
		try {
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
		} catch (Exception e) {
			return new ArrayList<Recipe>();
		}
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
