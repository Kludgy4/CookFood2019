package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * The Frame in which the application Panels are drawn
 * Completes basic setup
 */
@SuppressWarnings("serial")
public class CookManager extends JFrame {
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public JPanel gui;
	
	public CookManager() {
		super("CookFood");
		setBounds(0, 0, screenSize.width / 2, screenSize.height / 2);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		getContentPane().setBackground(CookSettings.colRed);

		setFocusTraversalKeysEnabled(false);

		gui = new PnlMain();
		add(gui, BorderLayout.CENTER);
		
		createMenuBar();
		setVisible(true);
	}

	protected void processWindowEvent(final WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.out.println("Terminating Program");
			this.dispose();
		}
	}
	
	public void createMenuBar() {
		JMenuBar menubar = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
		
		JMenuItem add = createMenuItem("New", KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK),	
				"Opens the Recipe Creation panel", Icons.FILE);
		add.addActionListener((ActionEvent event) -> {
			//Do stuff here
		});
		
		JMenuItem save = createMenuItem("Save As...", KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK),	
				"Saves the Current Recipe", Icons.FLOPPY_DISK);
		save.addActionListener((ActionEvent event) -> {
			//Do stuff here
		});
		
		JMenuItem generate = createMenuItem("Export...", KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK), 
				"Generates a Shopping List from selected recipes", Icons.EXPORT);
		generate.addActionListener((ActionEvent event) -> {
			//Do stuff here
		});
		
		JMenuItem exit = createMenuItem("Exit", KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK), 
				"Exits the application", Icons.EXIT);
		exit.addActionListener((ActionEvent event) -> {
			//Do stuff here
		});
		
		file.add(add);
		file.add(save);
		file.add(generate);
		file.add(exit);
		
		menubar.add(file);
		menubar.add(edit);
		menubar.add(help);
		setJMenuBar(menubar);
	}

	/**
	 * Creates a menu item from the specified parameters. To add functionality to
	 * the item you need to do it separately.
	 * 
	 * @param text The label for the menu.
	 * @param keyboardShortcut The shortcut to activate button. Use null if there isn't one.
	 * @param tip String provides information to user about the button.
	 * @param imagePath the path to the image used for the icon. Use null if there isn't a image.
	 * @return The JMenuItem from the specified parameters.
	 */
	public JMenuItem createMenuItem(String text, KeyStroke keyboardShortcut, String tip, Icons icon) {
		JMenuItem item;
		
		if (icon == null) item = new JMenuItem(text, MenuButton.createImageIcon(Icons.TEMP));
		else item = new JMenuItem(text, MenuButton.createImageIcon(icon));
		
		if (keyboardShortcut != null) item.setAccelerator(keyboardShortcut);
		item.setToolTipText(tip);
		return item;
	}
}
