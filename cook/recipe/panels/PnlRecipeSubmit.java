package cook.recipe.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;

import cook.CookSettings;
import cook.components.CookButton;
import cook.components.CookIcon;
import cook.components.CookPanel;
import cook.components.CookTextField;
import cook.elements.Ingredient;
import cook.elements.Recipe;
import cook.recipe.frames.FrRecipe;

@SuppressWarnings("serial")
public class PnlRecipeSubmit extends CookPanel {

	CookButton submitButton;
	public boolean updating;
	public String updatingTitle;
	
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
		
		//Adds a listener to the button that checks if input recipe information is sufficient, and if it is, submits it to the main interface for saving
		//It then closes the recipe creation screen
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrRecipe recipeFrame = ((PnlRecipeInterface)getParent()).recipeFrame;
				
				PnlRecipeTitle recipeInformation = recipeFrame.pnlRecipeInterface.pnlTitle;
				
				//Finalises input recipe information and checks for sufficiency
				if (!recipeInformation.titleField.isEmpty() && !recipeInformation.cookbookField.isEmpty()) {
					saveRecipe(new Recipe(recipeInformation.titleField.getText(), recipeInformation.cookbookField.getText(), recipeFrame.pnlIngredientsList.ingredients));
					recipeFrame.mainFrame.pnlRecipeList.refreshRecipes();
					recipeFrame.mainFrame.redraw();
					
					//Add recipe to the other window and dispose of this one
					recipeFrame.dispose();
				} else {
					//Makes the text boxes with insuffficient data in them red for user feedback
					for (CookTextField textField : recipeInformation.textFields) {
						if (textField.isEmpty()) {
							textField.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, CookSettings.colourError));
						} else {
							textField.setBorder(BorderFactory.createEmptyBorder());
						}
					}
				}
			}
		});
		
		//Adds the submit button to the panel
		layoutConstraints.insets = new Insets(0, 30, 0, 30);
		layoutConstraints.weightx = 0.5;
		layoutConstraints.ipady = 10;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		add(submitButton, layoutConstraints);
	}

	/**
	 * Saves a given Recipe object permanently into system memory in an internal program folder
	 * NOTICE: Required SDD project method - Use of sequential file to store information
	 * @param recipe The Recipe object to be saved
	 */
	public void saveRecipe(Recipe recipe) {
		//Reserves/Creates the relevant directory and file for recipe output
		File file = new File(CookSettings.savePath);
		file.mkdirs();
		file = new File(CookSettings.savePath + "/" + recipe.title + ".ckf");
		
		//Writes recipe information to the given file
		try {
			if (!updating || !recipe.title.equals(updatingTitle)) {
				if (file.exists()) {
						int duplicateNumber = 1;
						file = new File(CookSettings.savePath + "/" + recipe.title + " (" + duplicateNumber + ")" + ".ckf");
						while (file.exists()) {
							duplicateNumber++;
							file = new File(CookSettings.savePath + "/" + recipe.title + " (" + duplicateNumber + ")" + ".ckf");
						}
						recipe.title = recipe.title + "(" + duplicateNumber + ")";
				}
			}
			
			FileWriter writer = new FileWriter(file);
			writer.append(recipe.title + "," + recipe.cookbook + System.lineSeparator());
			for (Ingredient i : recipe.ingredients) {
				writer.append(i.name + "," + i.quantity + "," + i.quantityType.getMultipleType() + System.lineSeparator());
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
		submitButton.resizeIcon(frameSize, screenSize, 0.75);
	}
}
