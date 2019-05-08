package cook.components;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class CookBox extends JCheckBox {
	
	int x, y;
	
	public CookBox (int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
