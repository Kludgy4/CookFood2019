package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import static main.CookManager.*; 

@SuppressWarnings("serial")
public class PnlMain extends JPanel {
	
	ArrayList<CookButton> buttons = new ArrayList<>();
	
	ArrayList<Ingredient> arrayRecipes = new ArrayList<>();
	JPanel pnlBtn = new JPanel();
	EmptyBorder pnlBorder;
	GridLayout pnlLayout;
	Font f;
	
	public PnlMain() {
		setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		addButtons();
		
		resizeElements(frameSize, screenSize, false);
		repaint();
	}
	
	public void addButtons() {
		CookButton addButton = new CookButton(Icon.ADD);
		addButton.setText("Add Recipe");
		addButton.setIcon(new ImageIcon(Icon.ADD.getImage()));
		buttons.add(addButton);
		
		CookButton removeButton = new CookButton(Icon.REMOVE);
		removeButton.setText("Remove Recipe(s)");
		removeButton.setIcon(new ImageIcon(Icon.REMOVE.getImage()));
		buttons.add(removeButton);
		
		CookButton editButton = new CookButton(Icon.EDIT);
		editButton.setText("Edit Recipe");
		editButton.setIcon(new ImageIcon(Icon.EDIT.getImage()));
		buttons.add(editButton);
		
		CookButton generateButton = new CookButton(Icon.GENERATE);
		generateButton.setText("Generate List");
		generateButton.setIcon(new ImageIcon(Icon.GENERATE.getImage()));
		buttons.add(generateButton);
		
		float x = 0f;
		for (CookButton b : buttons) {
			b.setAlignmentX(x);
			b.setHorizontalAlignment(SwingConstants.LEFT);
			pnlBtn.add(b);
		}
		
		add(pnlBtn);
	}
	
	public void resizeElements (Dimension frameSize, Dimension screenSize, boolean scaleWidth) {
		
		//Resizes the button font and buttonIcon sizes
		f = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.04));
		for (CookButton b : buttons) {
			b.setFont(f);
		}
		
		//Resizes the border spacing around the buttons
		pnlBorder = new EmptyBorder((int)(frameSize.getHeight()*0.06), (int)(frameSize.getWidth()*0.08),
				(int)(frameSize.getHeight()*0.06), (int)(frameSize.getWidth()*0.2));
		pnlBtn.setBorder(pnlBorder);
		
		//Resizes the spacing between the buttons
		pnlBtn.setLayout(new GridLayout(4, 1, 0, (int)(frameSize.getHeight()*0.02)));
		
		//Resizes the button icons
		for (CookButton b : buttons) {
			b.resizeIcon(frameSize, screenSize, scaleWidth, 1);
		}
	}
	
	/**
	 * Generates and saves a shopping list (populated with ingredients in alphabetical order) in an input location
	 * @param recipeArray The recipes whose ingredients should be used in the shopping list
	 */
	public void generateShoppingList(ArrayList<Recipe> recipeArray) {
		ArrayList<Ingredient> arrangedIngredients  = arrangeIngredients(recipeArray);
		File saveLocation = getSaveLocation("Text File", "txt");
		saveList(saveLocation, arrangedIngredients);
	}
	
	/**
	 * This method extracts all of the ingredients from input recipes and places them into an unsorted array.
	 * It then sorts all of these ingredients into alphabetical order by name, and then returns the sorted ingredient array
	 * @param recipeArray The array of recipes whose ingredients need to be ordered
	 * @return An array of input recipe ingredients, alphabetically arranged
	 */
	public ArrayList<Ingredient> arrangeIngredients(ArrayList<Recipe> recipeArray) {
		ArrayList<Ingredient> ingredientArray = new ArrayList<>();
		
		//Loads all of the recipe ingredients into an unsorted array
		for (int i = 0; i < recipeArray.size(); i++) {
			ArrayList<Ingredient> ingredients = recipeArray.get(i).ingredients;
			for (int j = 0; j < ingredients.size(); j++) {
				ingredientArray.add(ingredients.get(j));
			}
		}

		//Sorts the ingredients into alphabetical order and returns them
		for (int i = 0; i < ingredientArray.size(); i++) {
			//Find the lowest list element, or the first in the alphabet
			int lowestIndex = i;
			for (int j = i; j < ingredientArray.size(); j++) {
				if (ingredientArray.get(j).name.compareTo(ingredientArray.get(lowestIndex).name) < 0) {
					System.out.println("New lowest is " + ingredientArray.get(j).name);
					lowestIndex = j;
				}
			}
			//Move the lowest found list element to the sorted array
			System.out.println("Swapping " + ingredientArray.get(i).name + " with " + ingredientArray.get(lowestIndex).name);
			Ingredient temp = ingredientArray.get(i);
			ingredientArray.set(i, ingredientArray.get(lowestIndex));
			ingredientArray.set(lowestIndex, temp);
		}
		
		return ingredientArray;
	}
	
	/**
	 * Gets the location of a file that the user enters via a prompt
	 * @param fileExtensionDescription A description of the file extension that the file will be saved using
	 * @param fileExtension The file extension that the file will be saved using
	 * @return A File object representative of the save location
	 */
	public File getSaveLocation(String fileExtensionDescription, String fileExtension) {
		
		//Adjusts the settings of the file save location selector
		JFileChooser chooser = new JFileChooser();
		String saveDirectory = "";
	    chooser.transferFocus();
	    chooser.setDialogTitle("Save " + fileExtension);
	    
	    //Ensures the correct file extension is used
	    FileFilter filter = new FileNameExtensionFilter(fileExtensionDescription, fileExtension);
	    chooser.setAcceptAllFileFilterUsed(false);
	    chooser.setFileFilter(filter);
	    
	    //Determines the final determined save location
	    if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) { 
	    	saveDirectory = chooser.getSelectedFile().toString() + "." + fileExtension;
	    } else {
	    	saveDirectory = (getClass().getResource("../") + "CookFoodRecipes").substring(6);
	    	System.out.println("Defaulting list output location to recipe save folder: " + saveDirectory);
	    }
		
		return new File(saveDirectory);
	}
	
	/**
	 * Saves an array of ingredients into a text file (in a given location) as a "Shopping List"
	 * @param saveLocation
	 * @param arrangedIngredients
	 */
	public void saveList(File saveLocation, ArrayList<Ingredient> arrangedIngredients) {
		try {
			//Writes the shopping list header (Date and Title) to the provided file location
			FileWriter writer = new FileWriter(saveLocation);
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date dateobj = new Date();
			writer.append("----------------------------------------" + "\n");
			writer.append("Shopping List Generated " + df.format(dateobj) + "\n");
			writer.append("----------------------------------------" + "\n");
			
			//Writes the given ingredients to the shopping list in a syntactically correct manner
			for (Ingredient i : arrangedIngredients) {
				if (i.quantity > 1) {
					writer.append(i.quantity + " " + i.quantityType.getMultipleType() + " of " + i.name + "\n");
				} else {
					writer.append(i.quantity + " " + i.quantityType.getSingularType() + " of " + i.name + "\n");
				}
			}
			writer.append("----------------------------------------");
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("There was an error saving your shopping list");
			e.printStackTrace();
		}
	}
	
	
}
