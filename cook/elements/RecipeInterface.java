package cook.elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeInterface {
	
	/**
	 * Retrieves all recipes previously saved on the system
	 * @return An array of all previously saved recipes as 'Recipe' objects
	 */
	public ArrayList<Recipe> retrieveRecipes() {
		try {
			ArrayList<File> recipeFiles = getFolderFiles();
			ArrayList<Recipe> recipes = new ArrayList<>();
			
			//Iterates over every retrieved recipe and turns them into recipe objects
			for (File f : recipeFiles) {
				try {
					//Primes the reader and reads general recipe informationE
					BufferedReader reader = new BufferedReader(new FileReader(f));
					Recipe recipe = new Recipe();
					String[] inputData = reader.readLine().split(",");
					recipe.title = inputData[0];
					recipe.cookbook = inputData[1];
					
					//Reads saved ingredients and their information into the recipe
					String input = reader.readLine();
					while (input != null) {
						inputData = input.split(",");
						recipe.ingredients.add(new Ingredient(inputData[0], Integer.parseInt(inputData[1]), QuantityType.valueOf(inputData[2].toUpperCase())));
						input = reader.readLine();
					}
					recipes.add(recipe);
					reader.close();
				} catch (FileNotFoundException e) {} catch (IOException e) {e.printStackTrace();}
			}
			return recipes;
		} catch (Exception e) {
			//Returns a blank array if there is an error opening saved recipes
			System.out.println("There was an error opening your saved recipes");
			return new ArrayList<Recipe>();
		}
	}
	
	/**
	 * Gets all of the
	 * @return
	 */
	public ArrayList<File> getFolderFiles() {
		//Ensures that the file saving/opening location exists
		File file = new File((getClass().getResource("../../") + "CookFoodRecipes").substring(6));
		file.mkdirs();
		
		//Gets all of the files saved on the computer in the CookFood save folder
		ArrayList<File> files = new ArrayList<>();
		
		for (File fileEntry : (new File((getClass().getResource("../../") + "CookFoodRecipes").substring(6))).listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	files.add(fileEntry.getAbsoluteFile());
	        }
	    }
		return files;
	}
	
	/**
	 * Deletes a recipe from the system given its title
	 * @param title The title of the recipe to be deleted
	 * @return A boolean confirming whether the file was successfully deleted or not
	 */
	public boolean deleteRecipe(String title) {
		ArrayList<File> files = getFolderFiles();
		
		//Perform a linear search for the file to delete
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
