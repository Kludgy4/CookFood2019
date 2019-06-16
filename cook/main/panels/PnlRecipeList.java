package cook.main.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import cook.CookSettings;
import cook.components.CookBox;
import cook.components.CookPanelList;
import cook.components.CookTextField;
import cook.elements.Recipe;
import cook.elements.RecipeInterface;
import cook.main.frames.FrMain;

@SuppressWarnings("serial")
public class PnlRecipeList extends CookPanelList {

	ArrayList<CookTextField> titles = new ArrayList<>();
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
			CookTextField recipeTitle = new CookTextField(true, 0, i);
			recipeTitle.setText(recipes.get(i).title);
			recipeTitle.setEditable(false);
			recipeTitle.setHighlighter(null);
			recipeTitle.setAlignmentY(Component.CENTER_ALIGNMENT);
			recipeTitle.setBorder(BorderFactory.createMatteBorder(1,1,1,1,CookSettings.neutral3));
			
			layoutConstraints.gridx = 0;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.9;
			
			titles.add(recipeTitle);
			listPanel.add(recipeTitle, layoutConstraints);
			
			//Adds a corresponding recipe checkbox to the panel
			CookBox recipeCheckBox = new CookBox(1, i, recipes.get(i));
			
			//Greys out boxes dependent on how many are selected
			recipeCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.pnlInterface.disableButtons();
				}
			});
			
			layoutConstraints.gridx = 1;
			layoutConstraints.gridy = i;
			layoutConstraints.weightx = 0.1;
			layoutConstraints.anchor = GridBagConstraints.CENTER;
			
			checkboxes.add(recipeCheckBox);
			listPanel.add(recipeCheckBox, layoutConstraints);
		}
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		//Resizes the button font and buttonIcon sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.04));
		
		for (JTextField recipeTitle : titles) {
			recipeTitle.setFont(newFont);
		}
		
		for (CookBox checkbox : checkboxes) {
			checkbox.setFont(newFont);
			checkbox.setPreferredSize(new Dimension((int)(frameSize.getHeight()*0.04), (int)(frameSize.getHeight()*0.04)));
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
