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
	
}
