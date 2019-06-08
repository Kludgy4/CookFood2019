package cook.recipe.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import cook.components.CookBox;
import cook.components.CookPanelList;
import cook.components.CookTextPane;
import cook.elements.Ingredient;

@SuppressWarnings("serial")
public class PnlIngredientsList extends CookPanelList {

	ArrayList<CookTextPane> names = new ArrayList<>();
	ArrayList<CookTextPane> quantities = new ArrayList<>();
	ArrayList<CookBox> checkboxes = new ArrayList<>();
	
	ArrayList<Ingredient> ingredients = new ArrayList<>();
	
	public PnlIngredientsList() {
		createScrollableLayout();
		addComponents();
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.025));
		
		layoutConstraints.ipady = (int)(frameSize.getHeight()*0.01);
		for (CookTextPane n : names) {
			n.setFont(newFont);
		}
		
		for (CookTextPane q : quantities) {
			q.setFont(newFont);
		}
		
		for (CookBox c : checkboxes) {
			c.setFont(newFont);
			c.setPreferredSize(new Dimension((int)(frameSize.getHeight()*0.04), (int)(frameSize.getHeight()*0.04)));
		}
	}
	
	public void addComponents() {
		removeAll();
		createScrollableLayout();
	    for (int i = 0; i < ingredients.size(); i++) {
	    	Ingredient ingredient = ingredients.get(i);
	    	
		    //Adds each ingredient name to the panel
			CookTextPane ingredientName = new CookTextPane(true, 0, i);
			ingredientName.setText(ingredient.name);
			ingredientName.setEditable(false);
			ingredientName.setHighlighter(null);
			
			layoutConstraints.gridx = 0;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.6;
			
			names.add(ingredientName);
			listPanel.add(ingredientName, layoutConstraints);
			
			//Adds each ingredient quantity to the panel
			CookTextPane ingredientQuantity = new CookTextPane(true, 1, i);
			
			if (ingredient.quantity == 1) {
				ingredientQuantity.setText(ingredient.quantity + " " + ingredient.quantityType.getSingularType());
			} else {
				ingredientQuantity.setText(ingredient.quantity + " " + ingredient.quantityType.getMultipleType());
			}
			ingredientQuantity.setEditable(false);
			ingredientQuantity.setHighlighter(null);
			
			layoutConstraints.gridx = 1;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.3;
			
			quantities.add(ingredientQuantity);
			listPanel.add(ingredientQuantity, layoutConstraints);
			
			//Adds a corresponding recipe checkbox to the panel
			CookBox recipeCheckBox = new CookBox(2, i, ingredient);
			layoutConstraints.gridx = 2;
			layoutConstraints.weightx = 0.1;
			layoutConstraints.anchor = GridBagConstraints.EAST;
			
			checkboxes.add(recipeCheckBox);
			listPanel.add(recipeCheckBox, layoutConstraints);
		}
	}

}
