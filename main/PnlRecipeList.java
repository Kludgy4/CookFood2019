package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PnlRecipeList extends JPanel {
	
	public PnlRecipeList() {
		setBorder(BorderFactory.createLineBorder(Color.RED, 10));
		//setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.BLACK));
	}
}
