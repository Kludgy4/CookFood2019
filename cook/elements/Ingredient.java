package cook.elements;

/**
 * Holds the Ingredient Information in a helpful structure
 */
public class Ingredient {
	
	public String name;
	public int quantity;
	public QuantityType quantityType;
	
	/**
	 * Creates a new Ingredient object
	 */
	public Ingredient() {
		name = "";
		quantity = 0;
		quantityType = null;
	}
	
	/**
	 * Stores loaded pnlIngredient data into memory
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
	 * Verifies whether input pnlIngredient data is sufficient
	 * @return A boolean representative of this pnlIngredient's validity
	 */
	public boolean isValid() {
		if (!name.equals("") && quantity > 0 && quantityType != null) {
			return true;
		}
		return false;
	}
}
