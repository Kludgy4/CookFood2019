package main;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class PnlMain extends JPanel {
	
	ArrayList<JButton> buttons = new ArrayList<>();
	
	ArrayList<Ingredient> arrayRecipes = new ArrayList<>();
	
	public PnlMain() {
		setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));
	}
	
	/**
	 * Generates and saves a shopping list (populated with ingredients in alphabetical order) in an input location
	 * @param recipeArray The recipes whose ingredients should be used in the shopping list
	 */
	public void generateShoppingList(ArrayList<Recipe> recipeArray) {
		ArrayList<Ingredient> arrangedIngredients  = arrangeIngredients(recipeArray);
		File saveLocation = getSaveLocation("Text File", "txt");
		saveList(saveLocation, arrangedIngredients);
	}
	
	/**
	 * This method extracts all of the ingredients from input recipes and places them into an unsorted array.
	 * It then sorts all of these ingredients into alphabetical order by name, and then returns the sorted ingredient array
	 * @param recipeArray The array of recipes whose ingredients need to be ordered
	 * @return An array of input recipe ingredients, alphabetically arranged
	 */
	public ArrayList<Ingredient> arrangeIngredients(ArrayList<Recipe> recipeArray) {
		ArrayList<Ingredient> ingredientArray = new ArrayList<>();
		
		//Loads all of the recipe ingredients into an unsorted array
		for (int i = 0; i < recipeArray.size(); i++) {
			ArrayList<Ingredient> ingredients = recipeArray.get(i).ingredients;
			for (int j = 0; j < ingredients.size(); j++) {
				ingredientArray.add(ingredients.get(j));
			}
		}

		//Sorts the ingredients into alphabetical order and returns them
		for (int i = 0; i < ingredientArray.size(); i++) {
			//Find the lowest list element, or the first in the alphabet
			int lowestIndex = i;
			for (int j = i; j < ingredientArray.size(); j++) {
				if (ingredientArray.get(j).name.compareTo(ingredientArray.get(lowestIndex).name) < 0) {
					System.out.println("New lowest is " + ingredientArray.get(j).name);
					lowestIndex = j;
				}
			}
			//Move the lowest found list element to the sorted array
			System.out.println("Swapping " + ingredientArray.get(i).name + " with " + ingredientArray.get(lowestIndex).name);
			Ingredient temp = ingredientArray.get(i);
			ingredientArray.set(i, ingredientArray.get(lowestIndex));
			ingredientArray.set(lowestIndex, temp);
		}
		
		return ingredientArray;
	}
	
	/**
	 * Gets the location of a file that the user enters via a prompt
	 * @param fileExtensionDescription A description of the file extension that the file will be saved using
	 * @param fileExtension The file extension that the file will be saved using
	 * @return A File object representative of the save location
	 */
	public File getSaveLocation(String fileExtensionDescription, String fileExtension) {
		
		//Adjusts the settings of the file save location selector
		JFileChooser chooser = new JFileChooser();
		String saveDirectory = "";
	    chooser.transferFocus();
	    chooser.setDialogTitle("Save " + fileExtension);
	    
	    //Ensures the correct file extension is used
	    FileFilter filter = new FileNameExtensionFilter(fileExtensionDescription, fileExtension);
	    chooser.setAcceptAllFileFilterUsed(false);
	    chooser.setFileFilter(filter);
	    
	    //Determines the final determined save location
	    if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) { 
	    	saveDirectory = chooser.getSelectedFile().toString() + "." + fileExtension;
	    } else {
	    	saveDirectory = (getClass().getResource("../") + "CookFoodRecipes").substring(6);
	    	System.out.println("Defaulting list output location to recipe save folder: " + saveDirectory);
	    }
		
		return new File(saveDirectory);
	}
	
	/**
	 * Saves an array of ingredients into a text file (in a given location) as a "Shopping List"
	 * @param saveLocation
	 * @param arrangedIngredients
	 */
	public void saveList(File saveLocation, ArrayList<Ingredient> arrangedIngredients) {
		try {
			//Writes the shopping list header (Date and Title) to the provided file location
			FileWriter writer = new FileWriter(saveLocation);
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date dateobj = new Date();
			writer.append("----------------------------------------" + "\n");
			writer.append("Shopping List Generated " + df.format(dateobj) + "\n");
			writer.append("----------------------------------------" + "\n");
			
			//Writes the given ingredients to the shopping list in a syntactically correct manner
			for (Ingredient i : arrangedIngredients) {
				if (i.quantity > 1) {
					writer.append(i.quantity + " " + i.quantityType.getMultipleType() + " of " + i.name + "\n");
				} else {
					writer.append(i.quantity + " " + i.quantityType.getSingularType() + " of " + i.name + "\n");
				}
			}
			writer.append("----------------------------------------");
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("There was an error saving your shopping list");
			e.printStackTrace();
		}
	}
}
