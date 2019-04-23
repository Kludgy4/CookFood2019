package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PnlRecipe extends JPanel {

	public PnlRecipe(Recipe recipeInfo) {
		saveRecipe(recipeInfo);
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
	
}
