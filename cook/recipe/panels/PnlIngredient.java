package cook.recipe.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cook.components.CookButton;
import cook.components.CookIcon;
import cook.components.CookTextPane;

@SuppressWarnings("serial")
public class PnlIngredient extends JPanel {
	
	PnlRecipe recipePanel;
	GridBagLayout layout;
	GridBagConstraints layoutConstraints;
	Insets buttonInsets;
	CookTextPane namePane, quantityPane;
	CookButton addButton, removeButton;
	
	public PnlIngredient(PnlRecipe recipePanel) {
		this.recipePanel = recipePanel;
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
	    
	    namePane = new CookTextPane(0, 0);
	    namePane.setText("Ingredient Name");
		
		quantityPane = new CookTextPane(0, 1);
		quantityPane.setText("Quantity");
		
		addButton = CookIcon.ADD.getCookButton("Add Ingredient");
		removeButton = CookIcon.REMOVE.getCookButton("Remove Ingredient");
		addElements();
	}
	
	public void addElements() {
		layoutConstraints.gridwidth = 2;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		add(namePane, layoutConstraints);
		
		layoutConstraints.gridwidth = 1;
		layoutConstraints.gridy = 1;
		add(quantityPane, layoutConstraints);
		
		layoutConstraints.gridx = 1;
		add(new JComboBox<>(), layoutConstraints);
		
		layoutConstraints.gridy = 2;
		layoutConstraints.gridx = 0;
		
		add(addButton, layoutConstraints);

		layoutConstraints.gridy = 3;
		layoutConstraints.gridx = 0;
		add(removeButton, layoutConstraints);
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		//Resizes the button font and buttonIcon sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.04));
		addButton.setFont(newFont);
		removeButton.setFont(newFont);
		
		//Resizes the button icons
		addButton.resizeIcon(frameSize, screenSize, 1);
		removeButton.resizeIcon(frameSize, screenSize, 1);
		
		//Resizes the spacing between the buttons
//		pnlBtn.setLayout(new GridLayout(4, 1, 0, (int)(frameSize.getHeight()*0.02)));
		
		//layoutConstraints.ipady = (int)(getHeight() / 1000);
		namePane.setFont(newFont);
		quantityPane.setFont(newFont);
	}
}