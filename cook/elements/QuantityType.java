package cook.elements;

public enum QuantityType {
	//Mass
	GRAMS("Gram", "Grams"), KILOGRAMS("Kilogram", "Kilograms"), OUNCES("Ounce", "Ounces"), POUNDS("Pound", "Pounds"), 
	
	//Volume
	MILLILITRES("Millilitre", "Millilitres"), LITRES("Litre", "Litres"), PINTS("Pint", "Pints"), GALLONS("Gallon", "Gallons"),
	DROPS("Drop", "Drops"), SMIDGENS("Smidgen", "Smidgens"), PINCHES("Pinch", "Pinches"), DASHES("Dash", "Dashes"), 
	SALTSPOONS("Saltspoon", "Saltspoons"), SCRUPLES("Scruple", "Scruples"), COFFEESPOONS("Coffeespoon", "Coffeespoons"), 
	DRAMS("Dram", "Drams"), TEASPOONS("Teaspoon", "Teaspoons"), DESSERTSPOONS("Dessertspoon", "Dessertspoons"), 
	TABLESPOONS("Tablespoon", "Tablespoons"), FLUIDOUNCES("Fluidounce", "Fluidounces"), WINEGLASSES("Wineglass", "Wineglasses"), 
	TEACUPS("Teacup", "Teacups"), GILLS("Gill", "Gills"), CUPS("Cup", "Cups"), QUARTS("Quart", "Quarts"), POTTLES("Pottle", "Pottles"),
	
	//Quantity
	VATS("Vat", "Vats"), BOTTLES("Bottle", "Bottles"), BARRELS("Barrel", "Barrels"), CARTONS("Carton", "Cartons"), HINTS("Hint", "Hints"), 
	HANDFULS("Handful", "Handfuls"), CLOVES("Clove", "Cloves"), WHOLE("Whole", "Whole"), OTHER("Other", "Other");
	
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
