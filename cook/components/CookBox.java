package cook.components;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class CookBox extends JCheckBox {
	
	public int x, y;
	public Object target;
	
	/**
	 * A CheckBox class that knows its screen grid location
	 * @param x The x position of the checkbox on the screen
	 * @param y The y position of the checkbox on the screen
	 */
	public CookBox (int x, int y, Object target) {
		super();
		this.x = x;
		this.y = y;
		this.target = target;
	}
	
}
