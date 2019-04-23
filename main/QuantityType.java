package main;

public enum QuantityType {
	//Mass
	GRAMS("Grams"), KILOGRAMS("Kilograms"), OUNCES("Ounces"), POUNDS("Pounds"), 
	
	//Volume
	MILLILITRES("Millilitres"), LITRES("Litres"), PINTS("Pints"), GALLONS("Gallons"),
	DROPS("Drops"), SMIDGENS("Smidgens"), PINCHES("Pinches"), DASHES("Dashes"), SALTSPOONS("Saltspoons"), SCRUPLES("Scruples"), COFFEESPOONS("Coffeespoons"), 
	DRAMS("Drams"), TEASPOONS("Teaspoons"), DESSERTSPOONS("Dessertspoons"), TABLESPOONS("Tablespoons"), FLUIDOUNCES("Fluidounces"), 
	WINEGLASSES("Wineglasses"), TEACUPS("Teacups"), GILLS("Gills"), CUPS("Cups"), QUARTS("Quarts"), POTTLES("Pottles"),
	
	//Quantity
	WHOLE("Whole"), OTHER("Other");
	
	private String name = "";
	
	private QuantityType(String name) {
		this.name = name;
	}

	public String getType() {
		return name;
	}
	
}
