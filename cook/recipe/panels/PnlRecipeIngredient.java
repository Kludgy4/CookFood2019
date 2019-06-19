package cook.recipe.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.text.PlainDocument;

import cook.CookSettings;
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
	
	public CookTextField nameField, quantityField;
	ArrayList<CookTextField> textFields = new ArrayList<>();
	public CookButton addButton, deleteButton;
	public CookCombo comboBox;
	
	FrRecipe recipeFrame;
	
	/**
	 * Creates the panel in which ingredients are created and added to a recipe whilst being created
	 */
	public PnlRecipeIngredient(FrRecipe recipeFrame) {
		this.recipeFrame = recipeFrame; 
		buttonInsets = new Insets(0, 25, 0, 25);
		createLayout();
		addComponents();
	}
	
	/**
	 * Creates and adds specified components to the panel
	 */
	public void addComponents() {
		//Creates text boxes
	    layoutConstraints.ipady = 10;
	    layoutConstraints.weightx = 0.5;
	    layoutConstraints.insets = buttonInsets;
	    
	    nameField = new CookTextField(false, "Ingredient Name", 0, 0);
	    quantityField = new CookTextField(false, "Quantity", 0, 1);
	    
	    PlainDocument doc = (PlainDocument) quantityField.getDocument();
	    IntegerFilter intFilter = new IntegerFilter("Quantity");
	    doc.setDocumentFilter(intFilter);
	    
	    quantityField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if (!quantityField.getText().equals("")) {
					addButton.setEnabled(true);
				} else {
					addButton.setEnabled(false);
				}
			}
			public void keyPressed(KeyEvent e) {}
		});
	    
	    textFields.add(nameField);
	    textFields.add(quantityField);
	    
		//Creates Buttons
		addButton = CookIcon.ADD.getCookButton("Add Ingredient");
		deleteButton = CookIcon.DELETE.getCookButton("Delete Ingredient(s)");
		
		addButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		//Adds relevant functionality to each of the buttons
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!nameField.isEmpty() && !quantityField.isEmpty()) {
					//Saves the input ingredient to the system memory
					PnlIngredientsList list = recipeFrame.pnlIngredientsList;
					
					list.ingredients.add(new Ingredient(nameField.getText(), Integer.parseInt(quantityField.getText()), (QuantityType) comboBox.getSelectedItem()));
					list.addComponents();
					
					//Sets the ingredient input text areas to contain their placeholder text
					nameField.setPlaceholder();
					quantityField.setPlaceholder();
					recipeFrame.redraw();
				} else {
					//Makes the text boxes with insuffficient data in them red for user feedback
					for (CookTextField textField : textFields) {
						if (textField.isEmpty()) {
							textField.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, CookSettings.colourError));
						} else {
							textField.setBorder(BorderFactory.createEmptyBorder());
						}
					}
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Deletes selected ingredients from system memory
				PnlIngredientsList list = recipeFrame.pnlIngredientsList;
				
				for (CookBox c : list.getSelectedCheckboxes(true)) {
					list.ingredients.remove(c.target);
				}
				deleteButton.setEnabled(false);
				list.addComponents();
				recipeFrame.redraw();
			}
		});
		
		//The combo box that allows selection of ingredient QuantityType
		comboBox = new CookCombo();
		
		nameField.setToolTipText("Set ingredient name here");
		quantityField.setToolTipText("Set ingredient quantity here");
		comboBox.setToolTipText("Select quantity type here");
		
		refreshElements();
	}
	
	public void refreshElements() {
		//Adds the constructed elements to their respective positions in the frame
		layoutConstraints.gridwidth = 2;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		add(nameField, layoutConstraints);
		
		layoutConstraints.gridwidth = 1;
		layoutConstraints.gridy = 1;
		add(quantityField, layoutConstraints);
		
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
		buttonInsets = new Insets((int)(frameSize.height/50), 25, (int)(frameSize.height/50), 25);
		layoutConstraints.insets = buttonInsets;
		
		//Resizes the panel font sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.035));
		addButton.setFont(newFont);
		deleteButton.setFont(newFont);
		nameField.changeFont(newFont);
		quantityField.changeFont(newFont);
		comboBox.setFont(newFont);
		
		//Resizes the button icons
		addButton.resizeIcon(frameSize, screenSize, 0.8);
		deleteButton.resizeIcon(frameSize, screenSize, 0.8);
		
		refreshElements();
	}
}