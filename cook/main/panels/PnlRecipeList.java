package cook.main.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import cook.CookSettings;
import cook.components.CookBox;
import cook.components.CookPanelList;
import cook.components.CookTextField;
import cook.elements.Ingredient;
import cook.elements.QuantityType;
import cook.elements.Recipe;
import cook.elements.RecipeInterface;
import cook.main.frames.FrMain;

@SuppressWarnings("serial")
public class PnlRecipeList extends CookPanelList {

	ArrayList<CookTextField> titles = new ArrayList<>();
	public ArrayList<Recipe> recipes;
	FrMain mainFrame;
	
	/**
	 * Constructs the Main Recipe-List Panel
	 */
	public PnlRecipeList(FrMain mainFrame) {
		this.mainFrame = mainFrame;
		createScrollableLayout();
		refreshRecipes();
	}
	
	/**
	 * Creates and adds specified components to the panel
	 */
	public void addComponents() {
	    for (int i = 0; i < recipes.size(); i++) {
		    //Adds each recipe to the panel
			CookTextField recipeTitle = new CookTextField(true, 0, i);
			recipeTitle.setText(recipes.get(i).title);
			recipeTitle.setEditable(false);
			recipeTitle.setHighlighter(null);
			recipeTitle.setAlignmentY(Component.CENTER_ALIGNMENT);
			recipeTitle.setBorder(BorderFactory.createMatteBorder(1,1,1,1,CookSettings.neutral3));
			
			layoutConstraints.gridx = 0;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.9;
			
			titles.add(recipeTitle);
			listPanel.add(recipeTitle, layoutConstraints);
			
			//Adds a corresponding recipe checkbox to the panel
			CookBox recipeCheckBox = new CookBox(1, i, recipes.get(i));
			
			//Greys out boxes dependent on how many are selected
			recipeCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.pnlInterface.disableButtons();
				}
			});
			
			layoutConstraints.gridx = 1;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.1;
			layoutConstraints.anchor = GridBagConstraints.CENTER;
			
			checkboxes.add(recipeCheckBox);
			listPanel.add(recipeCheckBox, layoutConstraints);
		}
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		//Resizes the button font and buttonIcon sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.04));
		
		//Resizes each recipeTitle by setting their font
		for (CookTextField recipeTitle : titles) {
			recipeTitle.setFont(newFont);
		}
		
		//Resizes each checkbox by setting their font and PreferredSize
		for (CookBox checkbox : checkboxes) {
			checkbox.setFont(newFont);
			checkbox.setPreferredSize(new Dimension((int)(frameSize.getHeight()*0.04), (int)(frameSize.getHeight()*0.04)));
		}
	}
	
	/**
	 * Refreshes the list of recipes saved in program memory
	 */
	public void refreshRecipes() {
		listPanel.removeAll();
		recipes = retrieveRecipes();
		addComponents();
	}
	
	/**
	 * Retrieves all ingredients previously saved on the system
	 * @return An array of all previously saved ingredients as 'Recipe' objects
	 */
	public ArrayList<Recipe> retrieveRecipes() {
		try {
			ArrayList<File> recipeFiles = (new RecipeInterface()).getFolderFiles();
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
}
