package cook.recipe.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import cook.components.CookBox;
import cook.components.CookTextPane;
import cook.elements.Recipe;
import cook.elements.RecipeInterface;

@SuppressWarnings("serial")
public class PnlIngredientsList extends JPanel {

	ArrayList<JCheckBox> checkboxes = new ArrayList<>();
	ArrayList<JTextPane> titles = new ArrayList<>();
	ArrayList<Recipe> recipes;
	
	JPanel recipeListPanel;
	GridBagLayout layout;
	GridBagConstraints recipeListLayout;
	
	public PnlIngredientsList() {
		setLayout(new BorderLayout());
		
		//Creates the panel on which components will be drawn
		layout = new GridBagLayout();
		layout.preferredLayoutSize(this);
		recipeListPanel = new JPanel(layout);
		
		//Creates the layout manager that is used to manage component creation
		recipeListLayout = new GridBagConstraints();
		recipeListLayout.fill = GridBagConstraints.HORIZONTAL;
		
		//Adds a "JScrollPane" to the frame that automatically manages scrollbar usage for all Components added to the frame
		JScrollPane scroller = new JScrollPane(recipeListPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.getVerticalScrollBar().setUnitIncrement(25);
		add(scroller, BorderLayout.CENTER);
		
		recipes = (new RecipeInterface()).retrieveRecipes();
		addComponents();
	}
	
	/**
	 * Resizes all of the Components contained within this panel
	 * @param frameSize The size of the entire frame
	 * @param screenSize The size of the entire screen
	 */
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		
	}
	
	public void addComponents() {
	    for (int i = 0; i < recipes.size(); i++) {
	    	StyleContext context = new StyleContext();
		    StyledDocument document = new DefaultStyledDocument(context);
		    StyleConstants.setAlignment(context.getStyle(StyleContext.DEFAULT_STYLE), StyleConstants.ALIGN_CENTER);
			
		    //Adds each recipe title to the panel
			CookTextPane recipeTitle = new CookTextPane(document, 0, i);
			recipeTitle.setText(recipes.get(i).title);
			recipeTitle.setEditable(false);
			recipeTitle.setHighlighter(null);
			recipeTitle.setAlignmentY(Component.CENTER_ALIGNMENT);
			
			recipeListLayout.gridx = 0;
			recipeListLayout.gridy = i;
			recipeListLayout.weightx = 0.9;
			recipeListLayout.ipady = 75;
			
			titles.add(recipeTitle);
			recipeListPanel.add(recipeTitle, recipeListLayout);
			
			//Adds a corresponding recipe checkbox to the panel
			CookBox recipeCheckBox = new CookBox(1, i);
			recipeListLayout.gridx = 1;
			recipeListLayout.gridy = i;
			recipeListLayout.weightx = 0.1;
			recipeListLayout.anchor = GridBagConstraints.EAST;
			recipeListLayout.ipady = 75;
			checkboxes.add(recipeCheckBox);
			recipeListPanel.add(recipeCheckBox, recipeListLayout);
		}
	}

}
