package cook.recipe.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;

import cook.components.CookFrame;
import cook.elements.Recipe;
import cook.main.frames.FrMain;
import cook.recipe.panels.PnlIngredientsList;
import cook.recipe.panels.PnlRecipeInterface;

/**
 * The Frame in which the Recipe application panels are drawn
 * Completes basic setup
 */
@SuppressWarnings("serial")
public class FrRecipe extends CookFrame {
	
	public FrMain mainFrame;
	public PnlRecipeInterface pnlRecipeInterface;
	public PnlIngredientsList pnlIngredientsList;
	
	public FrRecipe() {}
	
	/**
	 * Constructs a new window (In which the Recipe section of the CookFood program is run)	 
	 * @param mainFrame The parent frame of this panel
	 * @param preloadRecipe The recipe to be preloaded onto this window when it opens
	 */
	public FrRecipe(FrMain mainFrame, Recipe preloadRecipe) {
		super();
		this.mainFrame = mainFrame;
		
		//Constructs the frame for displaying
		pnlRecipeInterface = new PnlRecipeInterface(this);
		pnlIngredientsList = new PnlIngredientsList(this);
		
		pnlRecipeInterface.pnlRecipeSubmit.updating = false;
		if (preloadRecipe != null) {
			pnlRecipeInterface.pnlTitle.titleField.setText(preloadRecipe.title);
			pnlRecipeInterface.pnlTitle.cookbookField.setText(preloadRecipe.cookbook);
			pnlRecipeInterface.pnlRecipeSubmit.updating = true;
			pnlRecipeInterface.pnlRecipeSubmit.updatingTitle = preloadRecipe.title;
			
			pnlIngredientsList.ingredients = preloadRecipe.ingredients;
			pnlIngredientsList.addComponents();
		}
		
		add(pnlRecipeInterface, BorderLayout.WEST);
		add(pnlIngredientsList, BorderLayout.EAST);
		
		//Displays the frame to the user
		setBounds(100, 100, (int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
		setMinimumSize(new Dimension(1400, 512));
		
		setVisible(true);
		requestFocusInWindow();
	}
	
	public void redraw() {
		//Calculates the new adjusted size of the usable frame. Removes error introduced by 'automatic Window shading'
		refreshFrameSize();
        
        //Adjusts scaling on the interface and list panes
        pnlRecipeInterface.setPreferredSize(new Dimension((int)(frameSize.getWidth()*0.55), (int)(frameSize.getHeight())));
        pnlRecipeInterface.resizeElements(frameSize, screenSize);
        pnlRecipeInterface.repaint();
        pnlIngredientsList.setPreferredSize(new Dimension((int)(frameSize.getWidth()*0.45), (int)(frameSize.getHeight())));
        pnlIngredientsList.resizeElements(frameSize, screenSize);
        pnlIngredientsList.repaint();
	}
}
