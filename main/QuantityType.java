package main;

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
	WHOLE("Whole", "Whole"), OTHER("Other", "Other");
	
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
	
}
