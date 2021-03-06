package cook.recipe.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

import cook.components.CookPanel;
import cook.components.CookTextField;

@SuppressWarnings("serial")
public class PnlRecipeTitle extends CookPanel {
	
	Insets buttonInsets;
	public CookTextField titleField, cookbookField;
	//NOTICE: Required SDD project structure - Use of an array of records
	public ArrayList<CookTextField> textFields = new ArrayList<>();
	
	public PnlRecipeTitle() {
		buttonInsets = new Insets(0, 25, 0, 25);
		createLayout();
		addComponents();
	}
	
	/**
	 * Creates and adds specified components to the panel
	 */
	public void addComponents() {
		//Constructs the text boxes
	    layoutConstraints.ipady = 10;
	    layoutConstraints.weightx = 0.5;
	    layoutConstraints.insets = buttonInsets;
	    
	    titleField = new CookTextField(false, "Recipe Title", 0, 0);
		cookbookField = new CookTextField(false, "Cookbook", 1, 0);
		
		titleField.setToolTipText("Set recipe title here");
		cookbookField.setToolTipText("Set cookbook title here");
		
		//Adds the text boxes to the panel
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		textFields.add(titleField);
		add(titleField, layoutConstraints);
		
		layoutConstraints.gridx = 1;
		textFields.add(cookbookField);
		add(cookbookField, layoutConstraints);
	}

	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		Font font = new Font("Arial", Font.PLAIN, (int)(frameSize.getHeight()*0.04));
		titleField.changeFont(font);
		cookbookField.changeFont(font);
	}
}
