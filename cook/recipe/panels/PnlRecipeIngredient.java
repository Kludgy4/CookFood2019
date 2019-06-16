package cook.recipe.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.PlainDocument;

import cook.components.CookBox;
import cook.components.CookButton;
import cook.components.CookCombo;
import cook.components.CookIcon;
import cook.components.CookPanel;
import cook.components.CookTextField;
import cook.components.IntegerFilter;
import cook.elements.Ingredient;
import cook.elements.QuantityType;
import cook.recipe.frames.FrRecipe;

@SuppressWarnings("serial")
public class PnlRecipeIngredient extends CookPanel {
	
	Insets buttonInsets;
	
	public CookTextField namePane, quantityPane;
	public CookButton addButton, deleteButton;
	public CookCombo comboBox;
	
	FrRecipe recipeFrame;
	
	public PnlRecipeIngredient(FrRecipe recipeFrame) {
		this.recipeFrame = recipeFrame; 
		buttonInsets = new Insets(0, 25, 0, 25);
		createLayout();
		addElements();
	}
	
	public void addElements() {
		//Creates text boxes
	    layoutConstraints.ipady = 10;
	    layoutConstraints.weightx = 0.5;
	    layoutConstraints.insets = buttonInsets;
	    
	    namePane = new CookTextField(false, "Ingredient Name", 0, 0);
	    quantityPane = new CookTextField(false, "Quantity", 0, 1);
	    
	    PlainDocument doc = (PlainDocument) quantityPane.getDocument();
	    doc.setDocumentFilter(new IntegerFilter("Quantity"));
	    
		//Creates Buttons
		addButton = CookIcon.ADD.getCookButton("Add Ingredient");
		deleteButton = CookIcon.DELETE.getCookButton("Delete Ingredient(s)");
		
		addButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrRecipe recipeFrame = ((PnlRecipeInterface)getParent()).recipeFrame;
				//TODO Add ingredient dependant on input ingredient
				recipeFrame.pnlIngredientsList.ingredients.add(new Ingredient(namePane.getText(), Integer.parseInt(quantityPane.getText()), (QuantityType) comboBox.getSelectedItem()));
				recipeFrame.pnlIngredientsList.addComponents();
				recipeFrame.redraw();
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrRecipe recipeFrame = ((PnlRecipeInterface)getParent()).recipeFrame;
				PnlIngredientsList list = recipeFrame.pnlIngredientsList;
				
				for (CookBox c : list.getSelectedCheckboxes(true)) {
					list.ingredients.remove(c.target);
				}
				deleteButton.setEnabled(false);
				list.addComponents();
				recipeFrame.redraw();
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
		namePane.changeFont(newFont);
		quantityPane.changeFont(newFont);
		comboBox.setFont(newFont);
		
		//Resizes the button icons
		addButton.resizeIcon(frameSize, screenSize, 0.8);
		deleteButton.resizeIcon(frameSize, screenSize, 0.8);
		
		refreshElements();
	}
}