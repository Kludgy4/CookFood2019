package cook.components;

import javax.swing.JComboBox;

import cook.elements.QuantityType;

@SuppressWarnings("serial")
public class CookCombo extends JComboBox<QuantityType> {
	
	public CookCombo() {
		super(QuantityType.values());
	}
	
}
