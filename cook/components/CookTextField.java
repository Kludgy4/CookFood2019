package cook.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import cook.CookSettings;

@SuppressWarnings("serial")
public class CookTextField extends JTextField {

	public int x, y;
	
	public String placeholderText;
	public Font appliedFont;
	
	/**
	 * A TextPane class that knows its screen grid location
	 * @param centered Whether the text should be centered in the box
	 * @param x The x position of the textpane on the screen
	 * @param y The y position of the textpane on the screen
	 */
	public CookTextField (boolean centered, int x, int y) {
		if (centered) setHorizontalAlignment(JTextField.CENTER);
		this.x = x;
		this.y = y;
		setBorder(BorderFactory.createEmptyBorder());
	}
	
	/**
	 * A TextPane class that knows its screen grid location and has a defaultTextValue
	 * @param centered Whether the text should be centered in the box
	 * @param placeholderText The text to be displayed when this textfield is empty and not selected
	 * @param x The x position of the textpane on the screen
	 * @param y The y position of the textpane on the screen
	 */
	public CookTextField (boolean centered, String placeholderText, int x, int y) {
		if (centered) setHorizontalAlignment(JTextField.CENTER);
		this.placeholderText = placeholderText;
		this.x = x;
		this.y = y;
		setBorder(BorderFactory.createEmptyBorder());
		
		addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if (getText().isEmpty()) {
					setPlaceholder();
				}
			}
			
			public void focusGained(FocusEvent e) {
				if(getText().equals(placeholderText)) {
					removePlaceholder();
				}
			}
		});
		appliedFont = getFont();
		setPlaceholder();
	}
	
	/**
	 * Sets the TextField placeholder text
	 */
	public void setPlaceholder() {
		setForeground(CookSettings.neutral4);
		setFont(appliedFont.deriveFont(Font.ITALIC));
		setText(placeholderText);
	}
	
	/**
	 * Removes the TextField placeholder text for user input
	 */
	public void removePlaceholder() {
		setForeground(Color.BLACK);
		setFont(appliedFont);
		setText("");
	}
	
	/**
	 * Changes the Font of the textfield 
	 * @param font
	 */
	public void changeFont(Font font) {
		appliedFont = font;
		if (getText().equals(placeholderText)) {
			setForeground(CookSettings.neutral4);
			setFont(appliedFont.deriveFont(Font.ITALIC));
		} else {
			setFont(font);
		}
	}
	
	/**
	 * Verifies whether the box is empty of text or not, taking into account the potential presence of placeholder text
	 * @return Whether or not the textfield is empty
	 */
	public boolean isEmpty() {
		if (placeholderText.equals(getText()) || getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
}
