package cook.recipe.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;

import cook.components.CookFrame;
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
	
	/**
	 * Constructs a new window (In which the Recipe section of the CookFood program is run)	 
	 */
	public FrRecipe(FrMain mainFrame) {
		super();
		this.mainFrame = mainFrame;
		//Constructs the frame for displaying
		pnlRecipeInterface = new PnlRecipeInterface(this);
		pnlIngredientsList = new PnlIngredientsList();
		
		add(pnlRecipeInterface, BorderLayout.WEST);
		add(pnlIngredientsList, BorderLayout.EAST);
		
		//Displays the frame to the user
		setBounds(100, 100, (int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
		setMinimumSize(new Dimension(1400, 512));
		setVisible(true);
	}
	
	/**
	 * Updates the positioning and sizing of all on-screen components
	 */
	public void redraw() {
		//Calculates the new adjusted size of the usable frame. Removes error introduced by 'automatic Window shading'
		refreshFrameSize();
        
        //Adjusts scaling on the cook, and the list, panes
        pnlRecipeInterface.setPreferredSize(new Dimension((int)(frameSize.getWidth()*0.55), (int)(frameSize.getHeight())));
        pnlRecipeInterface.resizeElements(frameSize, screenSize);
        pnlRecipeInterface.repaint();
        pnlIngredientsList.setPreferredSize(new Dimension((int)(frameSize.getWidth()*0.45), (int)(frameSize.getHeight())));
        pnlIngredientsList.resizeElements(frameSize, screenSize);
        pnlIngredientsList.repaint();
	}
}
