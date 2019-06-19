package cook.components;

import javax.swing.JCheckBox;

import cook.CookSettings;

/**
 * Extends the functionality of the default JCheckBox to hold a selectable, and thus deletable, array object reference, and the screen coordinates of the box
 */
@SuppressWarnings("serial")
public class CookBox extends JCheckBox {
	
	public int x, y;
	public Object target;
	
	/**
	 * A CheckBox class that knows its screen grid location
	 * @param x The x position of the checkbox on the screen
	 * @param y The y position of the checkbox on the screen
	 * @param target The object linked to this checkbox
	 */
	public CookBox (int x, int y, Object target) {
		super();
		this.x = x;
		this.y = y;
		this.target = target;
		setBackground(CookSettings.colourBackground);
	}
	
}
