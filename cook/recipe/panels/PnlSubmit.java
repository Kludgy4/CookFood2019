package cook.recipe.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import cook.components.CookButton;
import cook.components.CookIcon;

@SuppressWarnings("serial")
public class PnlSubmit extends JPanel {

	PnlRecipe recipePanel;
	GridBagLayout layout;
	GridBagConstraints layoutConstraints;
	Insets buttonInsets;
	CookButton submitButton;
	
	public PnlSubmit(PnlRecipe recipePanel) {
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
	    
	    submitButton = CookIcon.SUBMIT.getCookButton("Submit Recipe");
		
		addPanes();
	}

	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		//Resizes the button font and buttonIcon sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.04));
		submitButton.setFont(newFont);
		submitButton.resizeIcon(frameSize, screenSize, 0.8);
		//layoutConstraints.ipady = (int)(getHeight() / 5);
	}
	
	public void addPanes() {
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		add(submitButton, layoutConstraints);
	}
	
}
