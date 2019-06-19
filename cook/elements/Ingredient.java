package cook.elements;

/**
 * Holds ingredient information in a helpful structure
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
	 * @param name The name of the ingredient
	 * @param quantity The number of ingredient quantities 
	 * @param quantityType The type of quantity of the ingredient
	 */
	public Ingredient(String name, int quantity, QuantityType quantityType) {
		this.name = name;
		this.quantity = quantity;
		this.quantityType = quantityType;
	}
}
