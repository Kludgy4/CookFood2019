package cook.main.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;

import cook.CookSettings;
import cook.components.CookBox;
import cook.components.CookButton;
import cook.components.CookPanelList;
import cook.components.CookTextPane;
import cook.elements.Recipe;
import cook.elements.RecipeInterface;
import cook.main.frames.FrMain;

@SuppressWarnings("serial")
public class PnlRecipeList extends CookPanelList {

	ArrayList<CookTextPane> titles = new ArrayList<>();
	public ArrayList<Recipe> recipes;
	FrMain mainFrame;
	
	/**
	 * Constructs the Main Recipe-List Panel
	 */
	public PnlRecipeList(FrMain mainFrame) {
		this.mainFrame = mainFrame;
		createScrollableLayout();
		refreshRecipes();
	}
	
	/**
	 * Creates and adds specified components to the panel
	 */
	public void addComponents() {
	    for (int i = 0; i < recipes.size(); i++) {
		    //Adds each recipe to the panel
			CookTextPane recipeTitle = new CookTextPane(true, 0, i);
			recipeTitle.setText(recipes.get(i).title);
			recipeTitle.setEditable(false);
			recipeTitle.setHighlighter(null);
			recipeTitle.setAlignmentY(Component.CENTER_ALIGNMENT);
			recipeTitle.setBorder(BorderFactory.createMatteBorder(1,1,1,1,CookSettings.neutral3));
			
			layoutConstraints.gridx = 0;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.9;
			layoutConstraints.ipady = 75;
			
			titles.add(recipeTitle);
			listPanel.add(recipeTitle, layoutConstraints);
			
			//Adds a corresponding recipe checkbox to the panel
			CookBox recipeCheckBox = new CookBox(1, i, recipes.get(i));
			
			//Greys out boxes dependent on how many are selected
			recipeCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					disableButtons();
				}
			});
			
			layoutConstraints.gridx = 1;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.1;
			layoutConstraints.anchor = GridBagConstraints.CENTER;
			layoutConstraints.ipady = 75;
			
			checkboxes.add(recipeCheckBox);
			listPanel.add(recipeCheckBox, layoutConstraints);
		}
	}
	
	public void disableButtons() {
		ArrayList<CookButton> buttons = mainFrame.pnlInterface.buttons;
		switch (getSelectedCheckboxes(false).size()) {
			case 0:
				//Greys out buttons if no recipes are selected
				buttons.get(1).setEnabled(false); buttons.get(2).setEnabled(false); buttons.get(3).setEnabled(false);
				break;
			case 1:
				//Enables all buttons if one recipe is selected
				buttons.get(1).setEnabled(true); buttons.get(2).setEnabled(true); buttons.get(3).setEnabled(true);
				break;
			default:
				//Disables only the editing button if two or more recipes are selected
				buttons.get(1).setEnabled(true); buttons.get(2).setEnabled(false); buttons.get(3).setEnabled(true);
				break;
		}
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		//Resizes the button font and buttonIcon sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.04));
		for (JTextPane recipeTitle : titles) {
			recipeTitle.setFont(newFont);
		}
	}
	
	/**
	 * Refreshes the list of recipes saved in program memory
	 */
	public void refreshRecipes() {
		listPanel.removeAll();
		recipes = (new RecipeInterface()).retrieveRecipes();
		addComponents();
	}
}
