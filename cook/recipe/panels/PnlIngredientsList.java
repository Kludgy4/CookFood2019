package cook.recipe.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import cook.components.CookBox;
import cook.components.CookPanelList;
import cook.components.CookTextField;
import cook.elements.Ingredient;
import cook.recipe.frames.FrRecipe;

@SuppressWarnings("serial")
public class PnlIngredientsList extends CookPanelList {

	//NOTICE: Required SDD project structure - Use of an array of records
	ArrayList<CookTextField> names = new ArrayList<>();
	ArrayList<CookTextField> quantities = new ArrayList<>();
	public ArrayList<Ingredient> ingredients = new ArrayList<>();
	
	FrRecipe recipeFrame;
	
	/**
	 * Constructs the main Ingredients-List Panel
	 */
	public PnlIngredientsList(FrRecipe recipeFrame) {
		this.recipeFrame = recipeFrame;
		createScrollableLayout();
		addComponents();
	}
	
	/**
	 * Creates and adds specified components to the panel
	 */
	public void addComponents() {
		removeAll();
		createScrollableLayout();
	    for (int i = 0; i < ingredients.size(); i++) {
	    	Ingredient ingredient = ingredients.get(i);
	    	
		    //Adds each ingredient name to the panel
			CookTextField ingredientName = new CookTextField(true, 0, i);
			ingredientName.setText(ingredient.name);
			ingredientName.setEditable(false);
			ingredientName.setHighlighter(null);
			
			layoutConstraints.gridx = 0;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.6;
			
			names.add(ingredientName);
			listPanel.add(ingredientName, layoutConstraints);
			
			//Adds each ingredient quantity to the panel
			CookTextField ingredientQuantity = new CookTextField(true, 1, i);
			
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
			
			//Greys out boxes dependent on how many are selected
			recipeCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					recipeFrame.pnlRecipeInterface.disableButtons();
				}
			});
			
			layoutConstraints.gridx = 2;
			layoutConstraints.weightx = 0.1;
			layoutConstraints.anchor = GridBagConstraints.EAST;
			
			checkboxes.add(recipeCheckBox);
			listPanel.add(recipeCheckBox, layoutConstraints);
		}
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.025));
		
		//Resizes each displayed name by setting their font
		for (CookTextField name : names) {
			name.setFont(newFont);
		}
		
		//Resizes each quantity TextField by setting their font
		for (CookTextField quantity : quantities) {
			quantity.setFont(newFont);
		}
		
		//Resizes each checkbox by setting their font and PreferredSize
		for (CookBox checkbox : checkboxes) {
			checkbox.setFont(newFont);
			checkbox.setPreferredSize(new Dimension((int)(frameSize.getHeight()*0.04), (int)(frameSize.getHeight()*0.04)));
		}
	}

}
