package main;

import static main.CookManager.frameSize;
import static main.CookManager.screenSize;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PnlRecipeList extends JPanel {

	ArrayList<JCheckBox> checkboxes = new ArrayList<>();
	ArrayList<Recipe> recipes = new ArrayList<>();
	
	public PnlRecipeList() {
		setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.BLACK));
		setLayout(new BorderLayout());
		
		//Adds a "JScrollPane", that automatically manages scrollbar usage within its contained frame
		GridBagLayout layout = new GridBagLayout();
		layout.preferredLayoutSize(this);
		JPanel recipeListPanel = new JPanel(layout);
		
		GridBagConstraints recipeListLayout = new GridBagConstraints();
		recipeListLayout.fill = GridBagConstraints.HORIZONTAL;
		
		JTextArea test1 = new JTextArea("Hello");
		test1.setSize(1000, 500);
		recipeListLayout.fill = GridBagConstraints.HORIZONTAL;
		recipeListLayout.weightx = 0.5;
		recipeListLayout.gridx = 0;
		recipeListLayout.gridy = 0;
		recipeListPanel.add(test1, recipeListLayout);
		
		JTextArea test2 = new JTextArea("World");
		test2.setSize(1000, 500);
		recipeListLayout.fill = GridBagConstraints.HORIZONTAL;
		recipeListLayout.weightx = 0.5;
		recipeListLayout.gridx = 1;
		recipeListLayout.gridy = 1;
		recipeListPanel.add(test2, recipeListLayout);
		
		JScrollPane scroller = new JScrollPane(recipeListPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroller, BorderLayout.CENTER);
		
		//Other stuff
		resizeElements(frameSize, screenSize);
		
		repaint();
	}
	
	public void refreshRecipes() {
		removeAll();
		//TODO Once add complete, create refresh for new recipe files
		recipes = (new RecipeInterface()).retrieveRecipes();
		
		resizeElements(frameSize, screenSize);
		repaint();
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
	
	}
}
