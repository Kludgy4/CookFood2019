package cook.recipe.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import cook.components.CookTextPane;

@SuppressWarnings("serial")
public class PnlRecipeTitle extends JPanel {
	
	PnlRecipe recipePanel;
	GridBagLayout layout;
	GridBagConstraints layoutConstraints;
	Insets buttonInsets;
	CookTextPane titlePane, cookbookPane;
	
	public PnlRecipeTitle(PnlRecipe recipePanel) {
		buttonInsets = new Insets(25, 25, 25, 25);
		
		//Creates the layout manager that is used to manage component creation
		layoutConstraints = new GridBagConstraints();
		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		//Creates the panel on which components will be drawn
		layout = new GridBagLayout();
		layout.preferredLayoutSize(this);
		//layout.setConstraints(this, layoutConstraints);
		setLayout(layout);
		
		this.recipePanel = recipePanel;
		setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
		
		//Add text boxes
	    layoutConstraints.ipady = 10;
	    layoutConstraints.weightx = 0.5;
	    layoutConstraints.insets = buttonInsets;
	    
	    titlePane = new CookTextPane(0, 0);
	    titlePane.setText("Recipe Title");
		
		cookbookPane = new CookTextPane(1, 0);
		cookbookPane.setText("Cookbook");
		
		addPanes();
	}

	public void resizeElements(Font font) {
		removeAll();
		layoutConstraints.ipady = (int)(getHeight() / 5);
		titlePane.setFont(font);
		cookbookPane.setFont(font);
		addPanes();
	}
	
	public void addPanes() {
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		add(titlePane, layoutConstraints);
		layoutConstraints.gridx = 1;
		add(cookbookPane, layoutConstraints);
	}
	
}
