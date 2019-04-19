package main;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public abstract class MenuButton extends JButton {
	public Icons icon, selectedIcon;
	
	public static ArrayList<MenuButton> instances = new ArrayList<MenuButton>();
	
	public MenuButton(Icons icon, Icons selectedIcon) {
		super();
		this.setIcon(createImageIcon(isSelected() ? selectedIcon : icon));
		this.icon = icon;
		this.selectedIcon = selectedIcon;
		this.setFocusPainted(false);
		this.setFocusable(false);
		this.addActionListener((ActionEvent event) -> {
			function();
			for(MenuButton btn : instances) btn.setIcon(createImageIcon(btn.isSelected() ? btn.selectedIcon : btn.icon));
		});
		instances.add(this);
	}
	
	public static ImageIcon createImageIcon(Icons icon) {
		return new ImageIcon(icon.getImage());
	}

	public abstract boolean isSelected();
	public abstract void function();
}
