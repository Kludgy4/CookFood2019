package cook.components;

import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class CookTextPane extends JTextPane {

	int x, y;
	
	public CookTextPane (StyledDocument document, int x, int y) {
		super(document);
		this.x = x;
		this.y = y;
	}
}
