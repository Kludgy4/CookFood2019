package cook.components;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

@SuppressWarnings("serial")
public class CookTextPane extends JTextPane {

	public int x, y;
	
	/**
	 * A TextPane class that knows its screen grid location
	 * @param centered Whether the text should be centered in the box
	 * @param x The x position of the textpane on the screen
	 * @param y The y position of the textpane on the screen
	 */
	public CookTextPane (boolean centered, int x, int y) {
		super(new DefaultStyledDocument(new StyleContext()));
		if (centered) StyleConstants.setAlignment(getStyle(StyleContext.DEFAULT_STYLE), StyleConstants.ALIGN_CENTER);
		this.x = x;
		this.y = y;
		setBorder(BorderFactory.createEmptyBorder());
	}
}
