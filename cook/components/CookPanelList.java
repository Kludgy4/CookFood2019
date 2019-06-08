package cook.components;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public abstract class CookPanelList extends CookPanel {

	protected JPanel listPanel;
	
	/**
	 * Constructs the gridbag layout for list panels to use, by automatically constructing 
	 * a scrollable panel to add things to (listPanel)
	 * Also constructs constraints object (layout) to be used when adding components to the listPanel
	 */
	public void createScrollableLayout() {
		//Creates the panel on which components will be drawn
		layout = new GridBagLayout();
		layout.preferredLayoutSize(this);
		listPanel = new JPanel(layout);
		
		//Creates the layout manager that is used to manage component creation
		layoutConstraints = new GridBagConstraints();
		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		
		//Adds a "JScrollPane" to the frame that automatically manages scrollbar usage for all Components added to the frame
		setLayout(new BorderLayout());
		JScrollPane scroller = new JScrollPane(listPanel);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.getVerticalScrollBar().setUnitIncrement(25);
		add(scroller, BorderLayout.CENTER);
	}
}
