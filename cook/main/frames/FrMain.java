package cook.main.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import cook.components.CookFrame;
import cook.main.panels.PnlInterface;
import cook.main.panels.PnlRecipeList;

/**
 * The Frame in which the Main application panels are drawn
 * Completes basic setup
 */
@SuppressWarnings("serial")
public class FrMain extends CookFrame {
	//TODO Update references across the program to be static because they only exist once so why pass stuff everywhere if can just be static?
	public PnlInterface pnlInterface;
	public PnlRecipeList pnlRecipeList;
	
	/**
	 * Constructs a new window (In which the Main section of the CookFood program is run)
	 */
	public FrMain() {
		super();

		//Constructs the frame for displaying
		pnlInterface = new PnlInterface(this);
		pnlRecipeList = new PnlRecipeList();
		
		add(pnlInterface, BorderLayout.WEST);
		add(pnlRecipeList, BorderLayout.EAST);
		
		//Displays the frame to the user
		setBounds(10, 10, (int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
		setMinimumSize(new Dimension(1024, 512));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	/**
	 * Updates the positioning and sizing of all on-screen components
	 */
	public void redraw() {
		//Calculates the new adjusted size of the usable frame. Removes error introduced by 'automatic Window shading'
		refreshFrameSize();
        
        //Adjusts component scaling on the interface and list panes
        pnlInterface.setPreferredSize(new Dimension((int)(frameSize.getWidth()*0.6), (int)(frameSize.getHeight())));
        pnlInterface.resizeElements(frameSize, screenSize);
        pnlInterface.repaint();
        pnlRecipeList.setPreferredSize(new Dimension((int)(frameSize.getWidth()*0.4), (int)(frameSize.getHeight())));
        pnlRecipeList.resizeElements(frameSize, screenSize);
        pnlRecipeList.repaint();
	}
}
