package cook.components;

import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import cook.elements.RoundBorder;

@SuppressWarnings("serial")
public class CookTextPane extends JTextPane {

	int x, y;
	
	public CookTextPane (int x, int y) {
		this.x = x;
		this.y = y;
		setBorder(new RoundBorder(10));
	}
	
	public CookTextPane (boolean centered, int x, int y) {
		super(new DefaultStyledDocument(new StyleContext()));
		StyleConstants.setAlignment(getStyle(StyleContext.DEFAULT_STYLE), StyleConstants.ALIGN_CENTER);
		this.x = x;
		this.y = y;
		setBorder(new RoundBorder(10));
	}
}
