package main;

import static main.CookManager.frameSize;
import static main.CookManager.screenSize;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class PnlRecipeList extends JPanel {

	ArrayList<JCheckBox> checkboxes = new ArrayList<>();
	ArrayList<JTextPane> titles = new ArrayList<>();
	ArrayList<Recipe> recipes;
	
	JPanel recipeListPanel;
	GridBagLayout layout;
	GridBagConstraints recipeListLayout;
	
	/**
	 * Constructs a new recipe-list panel
	 */
	public PnlRecipeList() {
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
		//Resizes the button font and buttonIcon sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.04));
		for (JTextPane recipeTitle : titles) {
			recipeTitle.setFont(newFont);
		}
	}
	
	public void refreshRecipes() {
		recipeListPanel.removeAll();
		recipes = (new RecipeInterface()).retrieveRecipes();
		addComponents();
		resizeElements(frameSize, screenSize);
		repaint();
	}
	
	public void addComponents() {
		StyleContext context = new StyleContext();
	    StyledDocument document = new DefaultStyledDocument(context);
	    StyleConstants.setAlignment(context.getStyle(StyleContext.DEFAULT_STYLE), StyleConstants.ALIGN_CENTER);
		
	    for (int i = 0; i < recipes.size(); i++) {
			//Adds each recipe title to the panel
			JTextPane recipeTitle = new JTextPane(document);
			recipeTitle.setText(recipes.get(i).title);
			recipeTitle.setEditable(false);
			recipeTitle.setHighlighter(null);
			
			recipeListLayout.gridx = 0;
			recipeListLayout.gridy = i;
			recipeListLayout.weightx = 0.9;
			recipeListLayout.ipady = 75;
			
			titles.add(recipeTitle);
			recipeListPanel.add(recipeTitle, recipeListLayout);
			
			//Adds a corresponding recipe checkbox to the panel
			JCheckBox recipeCheckBox = new JCheckBox();
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
