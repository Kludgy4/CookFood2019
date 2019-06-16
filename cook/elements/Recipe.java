package cook.elements;

import java.util.ArrayList;

public class Recipe {
	
	public String fileName;
	
	public String title;
	public String cookbook;
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
	 * @param pnlTitle
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
	public boolean isValid(String titlePlaceholder, String cookbookPlaceholder) {
		if (!title.equals("") && !cookbook.equals("") && !title.equals(titlePlaceholder) && !cookbook.equals(cookbookPlaceholder)) {
			return true;
		}
		return false;
	}
}
