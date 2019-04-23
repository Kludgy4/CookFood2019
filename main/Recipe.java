package main;

import java.util.ArrayList;

public class Recipe {
	
	String title;
	String cookbook;
	ArrayList<Ingredient> ingredients;
	
	/**
	 * Creates a new Recipe object
	 */
	public Recipe() {
		title = "";
		cookbook = "";
		ingredients = new ArrayList<>();
	}
	
	/**
	 * Stores loaded recipe data into memory
	 * @param title
	 * @param cookbook
	 * @param ingredients
	 */
	public Recipe(String title, String cookbook, ArrayList<Ingredient> ingredients) {
		this.title = title;
		this.cookbook = cookbook;
		this.ingredients = ingredients;
	}
	
	/**
	 * Verifies whether input recipe data is sufficient
	 * @return A boolean representative of this recipe's validity
	 */
	public boolean isValid() {
		if (!title.equals("") && !cookbook.equals("") && ingredients.size() > 0) {
			return true;
		}
		return false;
	}
}
