package cook.components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import cook.CookMain;

/**
 * StackOverflow class that allows JTextField input to be forced into a positive integer form
 */
public class IntegerFilter extends DocumentFilter {   
	
	public String allowedText, boxText = "";
	
	public IntegerFilter(String allowedText) {
		super();
		this.allowedText = allowedText;
	}
	
	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.insert(offset, string);
		
		if (test(sb.toString())) {
			super.insertString(fb, offset, string, attr);
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.replace(offset, offset + length, text);
		
		if (test(sb.toString())) {
			CookMain.recipe.pnlRecipeInterface.pnlRecipeIngredient.addButton.setEnabled(true);
			super.replace(fb, offset, length, text, attrs);
		} else {
			CookMain.recipe.pnlRecipeInterface.pnlRecipeIngredient.addButton.setEnabled(false);
		}
	}

	@Override
	public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.delete(offset, offset + length);
		
		if (test(sb.toString())) {
			setButtonEnabled(sb.toString());
			super.remove(fb, offset, length);
		} else {
			CookMain.recipe.pnlRecipeInterface.pnlRecipeIngredient.addButton.setEnabled(false);
		}
	}
	
	private boolean test(String text) {
		if (text.isEmpty() || text.equals(allowedText)) {
			return true;
		}
		try {
			if (Integer.parseInt(text) >= 0) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public void setButtonEnabled(String sb) {
		if (sb.toString().equals(allowedText) || sb.toString().isEmpty()) {
			CookMain.recipe.pnlRecipeInterface.pnlRecipeIngredient.addButton.setEnabled(false);
		} else {
			CookMain.recipe.pnlRecipeInterface.pnlRecipeIngredient.addButton.setEnabled(true);
		}
	}
}