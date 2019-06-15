package cook.components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import cook.CookSettings;

@SuppressWarnings("serial")
public abstract class CookPanel extends JPanel {

	protected GridBagLayout layout;
	protected GridBagConstraints layoutConstraints;
	
	/**
	 * Constructs the gridbag layout for each panel to use and sets the layout for the panel
	 * Also constructs constraints object (layout) to be used when adding components to the panel
	 */
	public void createLayout() {
		//Creates the layout constraints object
		layoutConstraints = new GridBagConstraints();
		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		//Creates the panel on which components will be drawn
		layout = new GridBagLayout();
		layout.preferredLayoutSize(this);
		
		setLayout(layout);
		
		//Sets the colour of the background
		setBackground(CookSettings.colourBackground);
	}
	
	/**
	 * Resizes all of the Components contained within this panel
	 * @param frameSize The size of the containing frame
	 * @param screenSize The size of the entire screen
	 */
	public abstract void resizeElements(Dimension frameSize, Dimension screenSize);
	
}
