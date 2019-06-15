package cook.recipe.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cook.components.CookBox;
import cook.components.CookButton;
import cook.components.CookCombo;
import cook.components.CookIcon;
import cook.components.CookPanel;
import cook.components.CookTextPane;
import cook.elements.Ingredient;
import cook.elements.QuantityType;
import cook.recipe.frames.FrRecipe;

@SuppressWarnings("serial")
public class PnlRecipeIngredient extends CookPanel {
	
	Insets buttonInsets;
	
	CookTextPane namePane, quantityPane;
	CookButton addButton, deleteButton;
	CookCombo comboBox;
	
	public PnlRecipeIngredient() {
		buttonInsets = new Insets(0, 25, 0, 25);
		createLayout();
		addElements();
	}
	
	public void addElements() {
		//Creates text boxes
	    layoutConstraints.ipady = 10;
	    layoutConstraints.weightx = 0.5;
	    layoutConstraints.insets = buttonInsets;
	    
	    namePane = new CookTextPane(false, 0, 0);
	    quantityPane = new CookTextPane(false, 0, 1);
	    
	    namePane.setText("Ingredient Name");
		quantityPane.setText("Quantity");
		
		//Creates Buttons
		addButton = CookIcon.ADD.getCookButton("Add Ingredient");
		deleteButton = CookIcon.DELETE.getCookButton("Delete Ingredient(s)");
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrRecipe parent = ((PnlRecipeInterface)getParent()).parent;
				//TODO Add ingredient dependant on input ingredient
				parent.pnlIngredientsList.ingredients.add(new Ingredient(namePane.getText(), Integer.parseInt(quantityPane.getText()), (QuantityType) comboBox.getSelectedItem()));
				parent.pnlIngredientsList.addComponents();
				parent.redraw();
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrRecipe parent = ((PnlRecipeInterface)getParent()).parent;
				PnlIngredientsList list = parent.pnlIngredientsList;
				
				for (CookBox c : list.getSelectedCheckboxes()) {
					list.ingredients.remove(c.target);
				}
				list.addComponents();
				parent.redraw();
			}
		});
		
		comboBox = new CookCombo();
		
		refreshElements();
	}
	
	public void refreshElements() {
		//TODO Allow this to stay in the main addElements method rather then having to exist
		//Adds the constructed elements to their respective positions in the frame
		layoutConstraints.gridwidth = 2;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		add(namePane, layoutConstraints);
		
		layoutConstraints.gridwidth = 1;
		layoutConstraints.gridy = 1;
		add(quantityPane, layoutConstraints);
		
		layoutConstraints.gridx = 1;
		add(comboBox, layoutConstraints);
		
		layoutConstraints.gridy = 2;
		layoutConstraints.gridx = 0;
		
		add(addButton, layoutConstraints);

		layoutConstraints.gridy = 2;
		layoutConstraints.gridx = 1;
		add(deleteButton, layoutConstraints);
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		//
		buttonInsets = new Insets((int)(frameSize.height/50), 25, (int)(frameSize.height/50), 25);
		layoutConstraints.insets = buttonInsets;
		
		//Resizes the panel font sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.035));
		addButton.setFont(newFont);
		deleteButton.setFont(newFont);
		namePane.setFont(newFont);
		quantityPane.setFont(newFont);
		comboBox.setFont(newFont);
		
		//Resizes the button icons
		addButton.resizeIcon(frameSize, screenSize, 0.8);
		deleteButton.resizeIcon(frameSize, screenSize, 0.8);
		
		refreshElements();
	}
}