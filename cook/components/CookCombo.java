package cook.components;

import javax.swing.JComboBox;

import cook.elements.QuantityType;

/**
 * Extends the functionality of the default JComboBox to create a CheckBox that only holds QuantityTypes
 */
@SuppressWarnings("serial")
public class CookCombo extends JComboBox<QuantityType> {
	
	/**
	 * Creates a ComboBox pre-populated with values inside the QuantityType enum
	 */
	public CookCombo() {
		super(QuantityType.values());
	}
	
}
