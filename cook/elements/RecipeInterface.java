package cook.elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import cook.CookSettings;

public class RecipeInterface {
	//TODO Remove RecipeInterface class from the system
	/**
	 * Retrieves all ingredients previously saved on the system
	 * @return An array of all previously saved ingredients as 'Recipe' objects
	 */
	public ArrayList<Recipe> retrieveRecipes() {
		try {
			ArrayList<File> recipeFiles = getFolderFiles();
			ArrayList<Recipe> recipes = new ArrayList<>();
			
			//Iterates over every retrieved recipe and turns them into recipe objects
			for (File file : recipeFiles) {
				try {
					//Primes the reader and reads general recipe informationE
					BufferedReader reader = new BufferedReader(new FileReader(file));
					Recipe recipe = new Recipe();
					String[] inputData = reader.readLine().split(",");
					recipe.title = inputData[0];
					recipe.cookbook = inputData[1];
					
					//Reads saved ingredients and their information into the recipe
					String input = reader.readLine();
					while (input != null) {
						inputData = input.split(",");
						recipe.ingredients.add(new Ingredient(
								inputData[0], 
								Integer.parseInt(inputData[1]), 
								QuantityType.valueOf(QuantityType.convertMultipleType(inputData[2].toUpperCase())
						)));
						input = reader.readLine();
					}
					recipe.fileName = file.getName();
					recipes.add(recipe);
					reader.close();
				} catch (FileNotFoundException e) {} catch (IOException e) {e.printStackTrace();}
			}
			return recipes;
		} catch (Exception e) {
			//Returns a blank array if there is an error opening saved ingredients
			System.out.println("There was an error opening your saved ingredients");
			return new ArrayList<Recipe>();
		}
	}
	
	/**
	 * Gets all of the files currently contained in the program recipe save folder
	 * @return An ArrayList of all the files contained in the save folder
	 */
	public ArrayList<File> getFolderFiles() {
		//Ensures that the file saving/opening location exists
		File file = new File(CookSettings.savePath);
		file.mkdirs();
		
		//Gets all of the files saved on the computer in the CookFood save folder
		ArrayList<File> files = new ArrayList<>();
		
		for (File fileEntry : file.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	files.add(fileEntry.getAbsoluteFile());
	        }
	    }
		return files;
	}
	
	/**
	 * Deletes a recipe from the system given its pnlTitle
	 * @param pnlTitle The pnlTitle of the recipe to be deleted
	 * @return A boolean confirming whether the file was successfully deleted or not
	 */
	public boolean deleteRecipe(String title) {
		ArrayList<File> files = getFolderFiles();
		System.out.println("Deleting " + title + " from " + files.toString());
		//Perform a linear search for the file to delete
		int i = 0;
		boolean foundIt = false;
		
		//Search for the given string until none left or found		
		while (!foundIt && i < files.size()) {
			String fileName = files.get(i).getName();
			if (fileName.substring(0, fileName.length()-4).equals(title)) {
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
