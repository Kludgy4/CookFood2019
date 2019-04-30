package main;

import static main.CookManager.frameSize;
import static main.CookManager.screenSize;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PnlRecipeList extends JPanel {

	ArrayList<JCheckBox> checkboxes = new ArrayList<>();
	ArrayList<Recipe> recipes = new ArrayList<>();
	
	/**
	 * Constructs a new recipe-list panel
	 */
	public PnlRecipeList() {
		setLayout(new BorderLayout());
		
		//Adds a "JScrollPane" to the frame that automatically manages scrollbar usage for all Components added to the frame
		GridBagLayout layout = new GridBagLayout();
		layout.preferredLayoutSize(this);
		JPanel recipeListPanel = new JPanel(layout);
		
		GridBagConstraints recipeListLayout = new GridBagConstraints();
		recipeListLayout.fill = GridBagConstraints.HORIZONTAL;
		
		JTextArea test1 = new JTextArea("Hello");
		recipeListLayout.fill = GridBagConstraints.HORIZONTAL;
		recipeListLayout.weightx = 0.5;
		recipeListLayout.gridx = 0;
		recipeListLayout.gridy = 0;
		recipeListPanel.add(test1, recipeListLayout);
		
		JTextArea test2 = new JTextArea("World");
		recipeListLayout.fill = GridBagConstraints.HORIZONTAL;
		recipeListLayout.weightx = 0.5;
		recipeListLayout.gridx = 1;
		recipeListLayout.gridy = 1;
		recipeListPanel.add(test2, recipeListLayout);
		
		JScrollPane scroller = new JScrollPane(recipeListPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroller, BorderLayout.CENTER);
		
	}
	
	/**
	 * Resizes all of the Components contained within this panel
	 * @param frameSize The size of the entire frame
	 * @param screenSize The size of the entire screen
	 */
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		
	}
	
	public void refreshRecipes() {
		removeAll();
		//TODO Once add complete, create refresh for new recipe files
		recipes = (new RecipeInterface()).retrieveRecipes();
		
		resizeElements(frameSize, screenSize);
		repaint();
	}
}
