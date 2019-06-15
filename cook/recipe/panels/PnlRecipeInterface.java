package cook.recipe.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import cook.components.CookPanel;
import cook.elements.Recipe;
import cook.recipe.frames.FrRecipe;

@SuppressWarnings("serial")
public class PnlRecipeInterface extends CookPanel {

	PnlRecipeTitle pnlTitle;
	PnlRecipeIngredient pnlRecipeIngredient;
	PnlRecipeSubmit pnlRecipeSubmit;
	
	ArrayList<CookPanel> panels = new ArrayList<>();
	
	FrRecipe parent;
	
	/**
	 * Constructs the Recipe Interface Panel
	 */
	public PnlRecipeInterface(FrRecipe parent) {
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 12, Color.BLACK));
		this.parent = parent;
		
		//Constructs onscreen elements
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		addFrames();
	}
	
	/**
	 * Constructs the Recipe Interface Panel, but uses input data to prepopulate the text fields
	 * @param recipeInfo
	 */
	public PnlRecipeInterface(Recipe recipeInfo) {
		super();
		//TODO Preload data into boxes here
	}
	
	/**
	 * Constructs and adds sub-panels to the Recipe Interface panel
	 */
	public void addFrames() {
		//Constructs Panels for use and adds them to the screen
		pnlTitle = new PnlRecipeTitle();
		pnlRecipeIngredient = new PnlRecipeIngredient();
		pnlRecipeSubmit = new PnlRecipeSubmit();
		
		panels.add(pnlTitle);
		panels.add(pnlRecipeIngredient);
		panels.add(pnlRecipeSubmit);
		
		//Adds the constructed panels to their respective positions on the screen
		add(pnlTitle, BorderLayout.NORTH);
		add(pnlRecipeIngredient, BorderLayout.CENTER);
		add(pnlRecipeSubmit, BorderLayout.SOUTH);
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		pnlTitle.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*0.2)));
		pnlRecipeIngredient.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*0.6)));
		pnlRecipeSubmit.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*0.2)));
		
		for(CookPanel panel : panels) panel.resizeElements(frameSize, screenSize);
	}
}
