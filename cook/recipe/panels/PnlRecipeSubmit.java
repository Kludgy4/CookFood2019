package cook.recipe.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cook.CookSettings;
import cook.components.CookButton;
import cook.components.CookIcon;
import cook.components.CookPanel;
import cook.elements.Ingredient;
import cook.elements.Recipe;
import cook.main.panels.PnlRecipeList;
import cook.recipe.frames.FrRecipe;

@SuppressWarnings("serial")
public class PnlRecipeSubmit extends CookPanel {

	CookButton submitButton;
	
	/**
	 * Creates the simple recipe submission panel containing one button
	 */
	public PnlRecipeSubmit() {
		createLayout();
		addComponents();
	}
	
	/**
	 * Constructs and adds the submit button to the panel
	 */
	public void addComponents() {
		submitButton = CookIcon.SUBMIT.getCookButton("Submit Recipe");
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Submit");
				FrRecipe parent = ((PnlRecipeInterface)getParent()).parent;
				PnlRecipeList recipes = parent.mainFrame.pnlRecipeList;
				
				PnlRecipeTitle recipeInformation = parent.pnlRecipeInterface.pnlTitle;
				
				saveRecipe(new Recipe(recipeInformation.titlePane.getText(), recipeInformation.cookbookPane.getText(), parent.pnlIngredientsList.ingredients));
				
				recipes.refreshRecipes();
				parent.mainFrame.redraw();
				
				//TODO Add ingredient dependant on input ingredient
				//Add recipe to the other window and dispose of this one
				parent.dispose();
			}
		});
		
		layoutConstraints.insets = new Insets(0, 30, 0, 30);
		layoutConstraints.weightx = 0.5;
		layoutConstraints.ipady = 10;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		add(submitButton, layoutConstraints);
	}

	/**
	 * Saves a given Recipe object permanently into system memory in an internal program folder
	 * @param recipe The Recipe object to be saved
	 */
	public void saveRecipe(Recipe recipe) {
		//Reserves/Creates the relevant directory and file for recipe output
		File file = new File(CookSettings.savePath);
		file.mkdirs();
		file = new File(CookSettings.savePath + "/" + recipe.title + ".ckf");
		
		//Writes recipe information to the given file
		try {
			if (file.exists()) {
				//TODO make saving number for duplicate names work
				int duplicateNumber = 1;
				file = new File(CookSettings.savePath + "/" + recipe.title + " (" + duplicateNumber + ")" + ".ckf");
				while (file.exists()) {
					duplicateNumber++;
					file = new File(CookSettings.savePath + "/" + recipe.title + " (" + duplicateNumber + ")" + ".ckf");
				}
			}
			
			FileWriter writer = new FileWriter(file);
			writer.append(recipe.title + "," + recipe.cookbook + "\n");
			for (Ingredient i : recipe.ingredients) {
				writer.append(i.name + "," + i.quantity + "," + i.quantityType.getMultipleType() + "\n");
			}
			
			recipe.fileName = file.getName();
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("There was an error saving your recipe");
			e.printStackTrace();
		}
	}

	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		//Resizes the button font and icon sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.04));
		submitButton.setFont(newFont);
		submitButton.resizeIcon(frameSize, screenSize, 0.8);
	}
}
