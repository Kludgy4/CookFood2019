package cook.recipe.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import cook.components.CookPanel;
import cook.components.CookTextField;

@SuppressWarnings("serial")
public class PnlRecipeTitle extends CookPanel {
	
	Insets buttonInsets;
	public CookTextField titlePane, cookbookPane;
	
	public PnlRecipeTitle() {
		buttonInsets = new Insets(0, 25, 0, 25);
		createLayout();
		addTextPanels();
	}
	
	public void addTextPanels() {
		//Constructs the text boxes
	    layoutConstraints.ipady = 10;
	    layoutConstraints.weightx = 0.5;
	    layoutConstraints.insets = buttonInsets;
	    
	    titlePane = new CookTextField(false, "Recipe Title", 0, 0);
		cookbookPane = new CookTextField(false, "Cookbook", 1, 0);
		
		//Adds the text boxes to the panel
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		add(titlePane, layoutConstraints);
		layoutConstraints.gridx = 1;
		add(cookbookPane, layoutConstraints);
	}

	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		Font font = new Font("Arial", Font.PLAIN, (int)(frameSize.getHeight()*0.04));
		titlePane.changeFont(font);
		cookbookPane.changeFont(font);
	}
}
