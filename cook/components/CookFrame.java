package cook.components;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import cook.CookSettings;

/**
 * Extends the functionality of the default JFrame to create a frame that knows the size of the screen it is on as well as the scaled size of its frame. 
 * It is also able to automatically call methods on all of the components added to it that cause them to "redraw" (or rescale) their contained components.
 * It also has Online Help automatically added to it in a menubar at the top of every Frame
 */
@SuppressWarnings("serial")
public abstract class CookFrame extends JFrame {
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public Dimension frameSize = new Dimension();
	
	public CookFrame() {
		super("CookFood");
		//Sets, tracks, and appropriately resizes, the Frame and its elements during program use
		addComponentListener(new ComponentAdapter() {  
	        public void componentResized(ComponentEvent e) {
	            redraw();
	        }
        });
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
	            redraw();
			}
		});
		
		//Other miscellaneous setup tasks
		getContentPane().setBackground(CookSettings.colourBackground);
		createMenubar();
	}
	
	/**
	 * Creates the menubar for in-program online help
	 */
	private void createMenubar() {
		//Creates the menubar and contained objects
		JMenuBar menubar = new JMenuBar();
		JMenu help = new JMenu("Help");
		JMenuItem helper = createMenuItem("Help", KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK), "Get Online Help for this program");
		
		//Adds functionality to the online-help button when it is triggered either by clicking or through the Ctrl+H keybinding
		helper.addActionListener((ActionEvent event) -> {
			//Checks if the program is connected to the internet to open the latest Online Help if possible, and otherwise open a locally stored version
			try {
				final URL url = new URL("http://www.google.com.au");
				URLConnection connection = url.openConnection();
				connection.connect();
				Desktop.getDesktop().browse(new URI("https://github.com/Kludgy4/CookFood2019"));
			} catch (Exception e) {
				//Opens a locally stored version of the README if the computer is not connected to the internet
				System.out.println("There was a problem getting the latest 'Online Help'. Make sure you are connected to the internet and try again");
				System.out.println("Now attempting to open the locally stored Online Help file");
				try {
					Desktop.getDesktop().open(new File(new File(CookSettings.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "/README.md"));
				} catch (IOException e1) {
					System.out.println("No local 'Online Help' available either. Maybe you moved the downloaded 'README.md' file?");
				}
			}
		});
		
		//Completes the final setup and adds the created online-help menubar to the frame
		help.add(helper);
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
	 * @return The JMenuItem from the specified parameters.
	 */
	public JMenuItem createMenuItem(String text, KeyStroke keyboardShortcut, String tip) {
		//Sets the hover text and keybinding for the
		JMenuItem item = new JMenuItem(text);
		if (keyboardShortcut != null) item.setAccelerator(keyboardShortcut);
		item.setToolTipText(tip);
		return item;
	}
	
	/**
	 * Updates the positioning and sizing of all on-screen components
	 */
	public abstract void redraw();
	
	/**
	 * Refreshes the size of the frame stored in memory
	 */
	protected void refreshFrameSize() {
		Insets error = getInsets();
        frameSize.setSize(getWidth() - (error.left + error.right), getHeight() - (error.bottom + error.top));
	}
	
	/**
	 * Disposes correctly of the frame if the user decides to close it
	 */
	protected void processWindowEvent(final WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			dispose();
		} 
	}
}
