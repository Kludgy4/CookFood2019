package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

}
