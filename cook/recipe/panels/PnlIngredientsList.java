package cook.recipe.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JTextPane;

import cook.components.CookBox;
import cook.components.CookPanelList;
import cook.components.CookTextPane;
import cook.elements.Ingredient;

@SuppressWarnings("serial")
public class PnlIngredientsList extends CookPanelList {

	ArrayList<JCheckBox> checkboxes = new ArrayList<>();
	ArrayList<JTextPane> titles = new ArrayList<>();
	ArrayList<Ingredient> ingredients = new ArrayList<>();
	
	public PnlIngredientsList() {
		//Sets 
		createScrollableLayout();
		addComponents();
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		//removeAll();
		//refreshComponents();
	}
	
	public void addComponents() {
	    for (int i = 0; i < ingredients.size(); i++) {
		    //Adds each ingredient name to the panel
			CookTextPane ingredientName = new CookTextPane(true, 0, i);
			ingredientName.setText(ingredients.get(i).name);
			ingredientName.setEditable(false);
			ingredientName.setHighlighter(null);
			
			layoutConstraints.gridx = 0;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.6;
			layoutConstraints.ipady = 75;
			
			titles.add(ingredientName);
			listPanel.add(ingredientName, layoutConstraints);
			
			
			//Adds each ingredient quantity to the panel
			CookTextPane ingredientQuantity = new CookTextPane(true, 1, i);
			ingredientQuantity.setText(ingredients.get(i).quantity + " " + ingredients.get(i).quantityType.getSingularType());
			ingredientQuantity.setEditable(false);
			ingredientQuantity.setHighlighter(null);
			
			layoutConstraints.gridx = 1;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.3;
			layoutConstraints.ipady = 75;
			
			listPanel.add(ingredientQuantity, layoutConstraints);
			
			
			//Adds a corresponding recipe checkbox to the panel
			CookBox recipeCheckBox = new CookBox(2, i);
			layoutConstraints.gridx = 2;
			layoutConstraints.weightx = 0.1;
			layoutConstraints.anchor = GridBagConstraints.EAST;
			layoutConstraints.ipady = 75;
			
			
			checkboxes.add(recipeCheckBox);
			listPanel.add(recipeCheckBox, layoutConstraints);
		}
	}

}
