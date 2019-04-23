package main;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PnlRecipeList extends JPanel {
	
	public PnlRecipeList() {
		setBorder(BorderFactory.createLineBorder(Color.RED, 10));
		//setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.BLACK));
	}
}
