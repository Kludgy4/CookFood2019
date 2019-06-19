package cook.elements;

import java.util.ArrayList;

/**
 * Holds recipe information in a helpful structure
 */
public class Recipe {
	
	public String fileName;
	
	public String title;
	public String cookbook;
	//NOTICE: Required SDD project structure - Use of an array of records
	public ArrayList<Ingredient> ingredients;
	
	/**
	 * Creates a new Recipe object
	 */
	public Recipe() {
		fileName = "";
		title = "";
		cookbook = "";
		ingredients = new ArrayList<>();
	}
	
	/**
	 * Stores loaded recipe data into memory
	 * @param title The title of the recipe
	 * @param cookbook the cookbook the recipe belongs to
	 * @param ingredients The ingredients attached to this recipe
	 */
	public Recipe(String title, String cookbook, ArrayList<Ingredient> ingredients) {
		this.title = title;
		this.cookbook = cookbook;
		this.ingredients = ingredients;
	}
}
