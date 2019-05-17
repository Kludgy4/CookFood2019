package cook.components;

import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import cook.elements.RoundBorder;

@SuppressWarnings("serial")
public class CookTextPane extends JTextPane {

	int x, y;
	
	public CookTextPane (int x, int y) {
		this.x = x;
		this.y = y;
		setBorder(new RoundBorder(10));
	}
	
	public CookTextPane (StyledDocument document, int x, int y) {
		super(document);
		this.x = x;
		this.y = y;
		setBorder(new RoundBorder(10));
	}
}
