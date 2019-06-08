package cook.recipe.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import cook.components.CookButton;
import cook.components.CookIcon;
import cook.components.CookPanel;

@SuppressWarnings("serial")
public class PnlRecipeSubmit extends CookPanel {

	CookButton submitButton;
	
	/**
	 * Creates the simple recipe submission panel containing one button
	 */
	public PnlRecipeSubmit() {
		createLayout();
		addComponents();
	}
	
	/**
	 * Constructs and adds the submit button to the panel
	 */
	public void addComponents() {
		submitButton = CookIcon.SUBMIT.getCookButton("Submit Recipe");
		layoutConstraints.insets = new Insets(0, 30, 0, 30);
		layoutConstraints.weightx = 0.5;
		layoutConstraints.ipady = 10;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		add(submitButton, layoutConstraints);
	}

	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		//Resizes the button font and icon sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.04));
		submitButton.setFont(newFont);
		submitButton.resizeIcon(frameSize, screenSize, 0.8);
	}
}
