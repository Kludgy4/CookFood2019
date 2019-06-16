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
	 * Stores loaded pnlRecipeIngredient data in memory
	 * @param name
	 * @param quantity
	 * @param quantityType
	 */
	public Ingredient(String name, int quantity, QuantityType quantityType) {
		this.name = name;
		this.quantity = quantity;
		this.quantityType = quantityType;
	}
}
