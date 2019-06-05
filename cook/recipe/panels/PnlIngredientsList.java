package cook.recipe.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import cook.components.CookBox;
import cook.components.CookTextPane;
import cook.elements.Ingredient;

@SuppressWarnings("serial")
public class PnlIngredientsList extends JPanel {

	ArrayList<JCheckBox> checkboxes = new ArrayList<>();
	ArrayList<JTextPane> titles = new ArrayList<>();
	ArrayList<Ingredient> ingredients = new ArrayList<>();
	
	JPanel recipeListPanel;
	GridBagLayout layout;
	GridBagConstraints recipeListLayout;
	
	public PnlIngredientsList() {
		setLayout(new BorderLayout());
		
		//Creates the panel on which components will be drawn
		layout = new GridBagLayout();
		layout.preferredLayoutSize(this);
		recipeListPanel = new JPanel(layout);
		
		//Creates the layout manager that is used to manage component creation
		recipeListLayout = new GridBagConstraints();
		recipeListLayout.fill = GridBagConstraints.HORIZONTAL;
		
		//Adds a "JScrollPane" to the frame that automatically manages scrollbar usage for all Components added to the frame
		JScrollPane scroller = new JScrollPane(recipeListPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.getVerticalScrollBar().setUnitIncrement(25);
		add(scroller, BorderLayout.CENTER);
		
		addComponents();
	}
	
	/**
	 * Resizes all of the Components contained within this panel
	 * @param frameSize The size of the entire frame
	 * @param screenSize The size of the entire screen
	 */
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		//removeAll();
		//refreshComponents();
	}
	
	public void addComponents() {
		//ingredients.add(new Ingredient("Test", 3, QuantityType.KILOGRAMS));
	    for (int i = 0; i < ingredients.size(); i++) {
		    //Adds each ingredient name to the panel
			CookTextPane ingredientName = new CookTextPane(true, 0, i);
			ingredientName.setText(ingredients.get(i).name);
			ingredientName.setEditable(false);
			ingredientName.setHighlighter(null);
			
			recipeListLayout.gridx = 0;
			recipeListLayout.gridy = i;
			recipeListLayout.weightx = 0.6;
			recipeListLayout.ipady = 75;
			
			titles.add(ingredientName);
			recipeListPanel.add(ingredientName, recipeListLayout);
			
			
			//Adds each ingredient quantity to the panel
			CookTextPane ingredientQuantity = new CookTextPane(true, 1, i);
			ingredientQuantity.setText(ingredients.get(i).quantity + " " + ingredients.get(i).quantityType.getSingularType());
			ingredientQuantity.setEditable(false);
			ingredientQuantity.setHighlighter(null);
			
			recipeListLayout.gridx = 1;
			recipeListLayout.gridy = i;
			recipeListLayout.weightx = 0.3;
			recipeListLayout.ipady = 75;
			
			recipeListPanel.add(ingredientQuantity, recipeListLayout);
			
			
			//Adds a corresponding recipe checkbox to the panel
			CookBox recipeCheckBox = new CookBox(2, i);
			recipeListLayout.gridx = 2;
			recipeListLayout.weightx = 0.1;
			recipeListLayout.anchor = GridBagConstraints.EAST;
			recipeListLayout.ipady = 75;
			
			
			checkboxes.add(recipeCheckBox);
			recipeListPanel.add(recipeCheckBox, recipeListLayout);
		}
	}

}
