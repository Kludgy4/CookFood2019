package cook.components;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

import cook.CookSettings;

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
	}
	
	public abstract void redraw();
	
	/**
	 * Refreshes the size of the frame stored in memory
	 */
	protected void refreshFrameSize() {
		Insets error = getInsets();
        frameSize.setSize(getWidth() - (error.left + error.right), getHeight() - (error.bottom + error.top));
	}
	
	/**
	 * Ensures the window closes and disposes of allocated system resources safely
	 */
	protected void processWindowEvent(final WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.out.println("Terminating Window");
			dispose();
		}
	}
}
