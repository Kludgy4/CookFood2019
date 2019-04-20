package main;

//import static main.QuantityType.*;

public class Ingredient {
	
	String name;
	int quantity;
	QuantityType quantityType;
	
	/**
	 * Creates a new Ingredient object
	 */
	public Ingredient() {
		name = "";
		quantity = 0;
		quantityType = null;
	}
	
	/**
	 * Stores loaded ingredient data into memory
	 * @param name
	 * @param quantity
	 * @param quantityType
	 */
	public Ingredient(String name, int quantity, QuantityType quantityType) {
		this.name = name;
		this.quantity = quantity;
		this.quantityType = quantityType;
	}
	
	/**
	 * Verifies whether input ingredient data is sufficient
	 * @return A boolean representative of this ingredient's validity
	 */
	public boolean isValid() {
		if (!name.equals("") && quantity != 0 && quantityType != null) {
			return true;
		}
		return false;
	}
}
