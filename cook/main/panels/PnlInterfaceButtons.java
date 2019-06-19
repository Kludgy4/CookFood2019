package cook.main.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import cook.components.CookPanel;

@SuppressWarnings("serial")
public class PnlInterfaceButtons extends CookPanel {

	/**
	 * A subpanel used to hold the buttons in a nice structure
	 */
	public PnlInterfaceButtons() {
		createLayout();
	}
	
	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		Border pnlBorder = new EmptyBorder((int)(frameSize.getHeight()*0.06), (int)(frameSize.getWidth()*0.08),
				(int)(frameSize.getHeight()*0.06), (int)(frameSize.getWidth()*0.2));
		setBorder(pnlBorder);
		
		//Resizes the spacing between the buttons
		setLayout(new GridLayout(4, 1, 0, (int)(frameSize.getHeight()*0.02)));
	}

}
