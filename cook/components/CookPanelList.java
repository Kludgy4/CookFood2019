package cook.components;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import cook.CookSettings;

/**
 * Extends the functionality of the CookPanel to automatically construct a scrollable panel layout that suits the list form
 * Pre-constructs checkboxes and is able to find/return the CookBoxes that have been checked
 */
@SuppressWarnings("serial")
public abstract class CookPanelList extends CookPanel {

	protected JPanel listPanel;
	//NOTICE: Required SDD project structure - Use of an array of records
	protected ArrayList<CookBox> checkboxes = new ArrayList<>();
	
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
		scroller.setBorder(BorderFactory.createEmptyBorder());
		
		add(scroller, BorderLayout.CENTER);
		
		//Sets the background of the layout
		listPanel.setBackground(CookSettings.colourBackground);
		setBackground(CookSettings.colourBackground);
	}
	
	/**
	 * Goes through the checkboxes added to the panel and returns those that have been selected
	 * @param shouldUncheck Whether this method should automatically uncheck all of the checkboxes
	 * @return An array of the selected checkboxes
	 */
	public ArrayList<CookBox> getSelectedCheckboxes(boolean shouldUncheck) {
		//NOTICE: Required SDD project structure - Use of an array of records
		ArrayList<CookBox> selectedBoxes = new ArrayList<>();
		
		for (CookBox box : checkboxes) {
			if (box.isSelected()) {
				//Automatically unchecks checkboxes if instructed
				if (shouldUncheck) {
					box.setSelected(false);
				}
				selectedBoxes.add(box);
			}
		}
		return selectedBoxes;
	}
}
