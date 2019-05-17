package cook.main.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import cook.CookSettings;
import cook.components.CookIcon;
import cook.main.panels.PnlMain;
import cook.main.panels.PnlRecipeList;

/**
 * The Frame in which the application Panels are drawn
 * Completes basic setup
 */
@SuppressWarnings("serial")
public class FrMain extends JFrame {
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static Dimension frameSize = new Dimension();
	
	public PnlMain guiMain;
	public PnlRecipeList guiList;
	
	/**
	 * Constructs a new window in which the CookFood program is run
	 */
	public FrMain() {
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
		getContentPane().setBackground(CookSettings.colGreenTrans);
		
		//Constructs the frame for displaying
		guiMain = new PnlMain();
		guiList = new PnlRecipeList();
		
		add(guiMain, BorderLayout.WEST);
		add(guiList, BorderLayout.EAST);
		createMenuBar();
		
		//Displays the frame to the user
		setBounds(10, 10, screenSize.width / 2, screenSize.height /2);
		setMinimumSize(new Dimension(1024, 512));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		redraw();
	}

	/**
	 * Constructs the menubar displayed at the top of the screen
	 */
	public void createMenuBar() {
		JMenuBar menubar = new JMenuBar();

		//Constructs individual menubar subsections
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
		
		//Adds event handling to each menubar button
		JMenuItem add = createMenuItem("New", KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK),	
				"Opens the Recipe Creation panel", null);
		add.addActionListener((ActionEvent event) -> {
			//Do stuff here
		});
		
		JMenuItem save = createMenuItem("Save As...", KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK),	
				"Saves the Current Recipe", null);
		save.addActionListener((ActionEvent event) -> {
			//Do stuff here
		});
		
		JMenuItem generate = createMenuItem("Generate...", KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK), 
				"Generates a Shopping List from selected recipes", null);
		generate.addActionListener((ActionEvent event) -> {
			
		});
		
		JMenuItem exit = createMenuItem("Exit", KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK), 
				"Exits the application", null);
		exit.addActionListener((ActionEvent event) -> {
			this.dispose();
		});
		
		//Adds constructed buttons into their relevant subsections
		file.add(add);
		file.add(save);
		file.add(generate);
		file.add(exit);
		
		//Adds the constructed subsections into the menubar
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
	 * @param imagePath the path to the buttonIcon used for the buttonIcon. Use null if there isn't a buttonIcon.
	 * @return The JMenuItem from the specified parameters.
	 */
	public JMenuItem createMenuItem(String text, KeyStroke keyboardShortcut, String tip, CookIcon cookIcon) {
		JMenuItem item;
		
		if (cookIcon == null) item = new JMenuItem(text);
		else item = new JMenuItem(text);
		
		if (keyboardShortcut != null) item.setAccelerator(keyboardShortcut);
		item.setToolTipText(tip);
		return item;
	}
	
	/**
	 * Updates the positioning and sizing of all on-screen components
	 */
	public void redraw() {
		//Calculates the new adjusted size of the usable frame. Removes error introduced by 'automatic Window shading'
		Insets error = getInsets();
        frameSize.setSize(getWidth() - (error.left + error.right), getHeight() - (error.bottom + error.top));
        
        //Adjusts scaling on the cook, and the list, panes
        guiMain.setPreferredSize(new Dimension((int)(FrMain.frameSize.getWidth()*0.6), (int)(FrMain.frameSize.getHeight())));
        guiMain.resizeElements(frameSize, screenSize);
        guiMain.repaint();
        guiList.setPreferredSize(new Dimension((int)(FrMain.frameSize.getWidth()*0.4), (int)(FrMain.frameSize.getHeight())));
        guiList.resizeElements(frameSize, screenSize);
        guiList.repaint();
	}
	
	/**
	 * Ensures the window closes and disposes of allocated system resources safely
	 */
	protected void processWindowEvent(final WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.out.println("Terminating Program");
			this.dispose();
		}
	}
}
