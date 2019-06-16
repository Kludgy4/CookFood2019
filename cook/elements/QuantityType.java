package cook.elements;

public enum QuantityType {
	//Metric
	//Mass
	GRAMS("Gram", "Grams"), KILOGRAMS("Kilogram", "Kilograms"), 
	//Volume
	MILLILITRES("Millilitre", "Millilitres"), LITRES("Litre", "Litres"), 
	
	//Quantity
	BOTTLES("Bottle", "Bottles"), CANS("Can", "Cans"), CARTONS("Carton", "Cartons"), HANDFULS("Handful", "Handfuls"), WHOLE("Whole", "Whole"), 
	
	//Imperial
	//Mass
	OUNCES("Ounce", "Ounces"), POUNDS("Pound", "Pounds"), 
	//Volume
	PINTS("Pint", "Pints"), GALLONS("Gallon", "Gallons"),
	DROPS("Drop", "Drops"), SMIDGENS("Smidgen", "Smidgens"), PINCHES("Pinch", "Pinches"), DASHES("Dash", "Dashes"), 
	SALTSPOONS("Saltspoon", "Saltspoons"), SCRUPLES("Scruple", "Scruples"), COFFEESPOONS("Coffeespoon", "Coffeespoons"), 
	DRAMS("Dram", "Drams"), TEASPOONS("Teaspoon", "Teaspoons"), DESSERTSPOONS("Dessertspoon", "Dessertspoons"), 
	TABLESPOONS("Tablespoon", "Tablespoons"), FLUIDOUNCES("Fluidounce", "Fluidounces"), WINEGLASSES("Wineglass", "Wineglasses"), 
	TEACUPS("Teacup", "Teacups"), GILLS("Gill", "Gills"), CUPS("Cup", "Cups"), QUARTS("Quart", "Quarts"), POTTLES("Pottle", "Pottles"),
	
	//Quantity (Less Frequent)
	CLOVES("Clove", "Cloves"), BARRELS("Barrel", "Barrels"), BAGS("Bag", "Bags"), HINTS("Hint", "Hints"), SACHETS("Sachet", "Sachets"), 
	VATS("Vat", "Vats"), OTHER("Other", "Other");
	
	private String singularName = "", multiName = "";
	
	private QuantityType(String singularName, String multiName) {
		this.singularName = singularName;
		this.multiName = multiName;
	}
	
	/**
	 * Retrieves the singular form of the quantity type
	 * @return Singular form of the quantity type
	 */
	public String getSingularType() {
		return singularName;
	}

	/**
	 * Retrieves the plural form of the quantity type
	 * @return Plural form of the quantity type
	 */
	public String getMultipleType() {
		return multiName;
	}
	
	/**
	 * Used when loading/saving saved recipes in memory, converts the plural form of a quantity to its singular form
	 * @param name The singular name to be converted to multiple
	 * @return The multiple form of the quantity name
	 */
	public static String convertMultipleType(String name) {
		for (QuantityType q : values()) {
			if (q.singularName.toUpperCase().equals(name) || q.multiName.toUpperCase().equals(name)) {
				return q.multiName.toUpperCase();
			}
		}
		System.out.println("No valid enum value found for saved ingredient type");
		return name.toUpperCase();
	}
	
}
