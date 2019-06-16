package cook.recipe.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import cook.CookSettings;
import cook.components.CookPanel;
import cook.recipe.frames.FrRecipe;

@SuppressWarnings("serial")
public class PnlRecipeInterface extends CookPanel {

	public PnlRecipeTitle pnlTitle;
	public PnlRecipeIngredient pnlRecipeIngredient;
	public PnlRecipeSubmit pnlRecipeSubmit;
	
	ArrayList<CookPanel> panels = new ArrayList<>();
	
	FrRecipe recipeFrame;
	
	/**
	 * Constructs the Recipe Interface Panel
	 */
	public PnlRecipeInterface(FrRecipe recipeFrame) {
		this.recipeFrame = recipeFrame;
		
		//Constructs onscreen elements
		setBackground(CookSettings.colourBackground);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		addFrames();
	}
	
	/**
	 * Constructs and adds sub-panels to the Recipe Interface panel
	 */
	public void addFrames() {
		//Constructs Panels for use and adds them to the screen
		pnlTitle = new PnlRecipeTitle();
		pnlRecipeIngredient = new PnlRecipeIngredient(recipeFrame);
		pnlRecipeSubmit = new PnlRecipeSubmit();
		
		panels.add(pnlTitle);
		panels.add(pnlRecipeIngredient);
		panels.add(pnlRecipeSubmit);
		
		//Adds the constructed panels to their respective positions on the screen
		add(pnlTitle, BorderLayout.NORTH);
		add(pnlRecipeIngredient, BorderLayout.CENTER);
		add(pnlRecipeSubmit, BorderLayout.SOUTH);
	}
	
	public void disableButtons() {
		switch (recipeFrame.pnlIngredientsList.getSelectedCheckboxes(false).size()) {
			case 0:
				pnlRecipeIngredient.deleteButton.setEnabled(false);
				break;
			default:
				pnlRecipeIngredient.deleteButton.setEnabled(true);
				break;
		}
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		pnlTitle.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*0.2)));
		pnlRecipeIngredient.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*0.6)));
		pnlRecipeSubmit.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*0.2)));
		
		for(CookPanel panel : panels) panel.resizeElements(frameSize, screenSize);
	}
}
