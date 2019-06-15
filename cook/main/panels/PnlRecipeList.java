package cook.main.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JTextPane;

import cook.components.CookBox;
import cook.components.CookPanelList;
import cook.components.CookTextPane;
import cook.elements.Recipe;
import cook.elements.RecipeInterface;

@SuppressWarnings("serial")
public class PnlRecipeList extends CookPanelList {

	ArrayList<CookBox> checkboxes = new ArrayList<>();
	ArrayList<CookTextPane> titles = new ArrayList<>();
	public ArrayList<Recipe> recipes;
	
	/**
	 * Constructs the Main Recipe-List Panel
	 */
	public PnlRecipeList() {
		createScrollableLayout();
		refreshRecipes();
	}
	
	/**
	 * Creates and adds specified components to the panel
	 */
	public void addComponents() {
	    for (int i = 0; i < recipes.size(); i++) {
		    //Adds each recipe to the panel
			CookTextPane recipeTitle = new CookTextPane(true, 0, i);
			recipeTitle.setText(recipes.get(i).title);
			recipeTitle.setEditable(false);
			recipeTitle.setHighlighter(null);
			recipeTitle.setAlignmentY(Component.CENTER_ALIGNMENT);
			
			layoutConstraints.gridx = 0;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.9;
			layoutConstraints.ipady = 75;
			
			titles.add(recipeTitle);
			listPanel.add(recipeTitle, layoutConstraints);
			
			//Adds a corresponding recipe checkbox to the panel
			CookBox recipeCheckBox = new CookBox(1, i, recipes.get(i));
			
			layoutConstraints.gridx = 1;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.1;
			layoutConstraints.anchor = GridBagConstraints.CENTER;
			layoutConstraints.ipady = 75;
			
			checkboxes.add(recipeCheckBox);
			listPanel.add(recipeCheckBox, layoutConstraints);
		}
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		//Resizes the button font and buttonIcon sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.04));
		for (JTextPane recipeTitle : titles) {
			recipeTitle.setFont(newFont);
		}
	}
	
	/**
	 * Refreshes the list of recipes saved in program memory
	 */
	public void refreshRecipes() {
		listPanel.removeAll();
		recipes = (new RecipeInterface()).retrieveRecipes();
		addComponents();
	}
	
	
}
