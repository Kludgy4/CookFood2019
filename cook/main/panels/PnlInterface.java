package cook.main.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import cook.CookMain;
import cook.CookSettings;
import cook.components.CookBox;
import cook.components.CookButton;
import cook.components.CookIcon;
import cook.components.CookPanel;
import cook.elements.Ingredient;
import cook.elements.Recipe;
import cook.elements.RecipeInterface;
import cook.main.frames.FrMain;
import cook.recipe.frames.FrRecipe; 

@SuppressWarnings("serial")
public class PnlInterface extends CookPanel {
	
	ArrayList<CookButton> buttons = new ArrayList<>();
	
	JPanel pnlBtn = new JPanel();
	EmptyBorder pnlBorder;
	GridLayout pnlLayout;
	FrMain mainFrame;
	
	/**
	 * Constructs the Main Interface Panel
	 */
	public PnlInterface(FrMain mainFrame) {
		this.mainFrame = mainFrame;
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 12, Color.BLACK));
		
		//Constructs onscreen elements
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		addButtons();
	}
	
	/**
	 * Creates and adds specified buttons to the panel
	 */
	public void addButtons() {
		//Creates the relevant CookButtons to add to the JPanel
		CookButton addButton = CookIcon.ADD.getCookButton("Add Recipe");
		CookButton deleteButton = CookIcon.DELETE.getCookButton("Delete Recipe(s)");
		CookButton editButton = CookIcon.EDIT.getCookButton("Edit Recipe");
		CookButton generateButton = CookIcon.GENERATE.getCookButton("Generate List");
		
		//Adds relevant functionality to each of the buttons
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO swap focus to open frame if already existing rather then opening a second one
				CookMain.recipe = new FrRecipe(mainFrame);
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PnlRecipeList list = mainFrame.pnlRecipeList;
				
				for (CookBox box : list.getSelectedCheckboxes()) {
					Recipe cTarget = (Recipe)box.target;
					String fileName = (cTarget.fileName).substring(0, cTarget.fileName.length()-4);
					(new RecipeInterface()).deleteRecipe(fileName);
				}
				list.refreshRecipes();
				mainFrame.redraw();
			}
		});
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//CookMain.recipe = new FrRecipe(mainFrame, );
			}
		});
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Recipe> selectedRecipes = new ArrayList<>();
				
				for (CookBox c : mainFrame.pnlRecipeList.getSelectedCheckboxes()) {
					selectedRecipes.add((Recipe)c.target);
				}
				generateShoppingList(selectedRecipes);
			}
		});
		
		//Adds the buttons all to a list to be easily resized later
		buttons.add(addButton);
		buttons.add(deleteButton);
		buttons.add(editButton);
		buttons.add(generateButton);
		
		//Adds the buttons to the panel (contained within another panel with whitespace (blank borders) around it)
		for (CookButton button : buttons) pnlBtn.add(button);
		add(pnlBtn);
	}
	
	public void resizeElements (Dimension frameSize, Dimension screenSize) {
		//Resizes the button font and buttonIcon sizes
		Font newFont = new Font("Arial", Font.BOLD, (int)(frameSize.getHeight()*0.04));
		for (CookButton button : buttons) button.setFont(newFont);
		
		//Resizes the border spacing around the buttons
		pnlBorder = new EmptyBorder((int)(frameSize.getHeight()*0.06), (int)(frameSize.getWidth()*0.08),
				(int)(frameSize.getHeight()*0.06), (int)(frameSize.getWidth()*0.2));
		pnlBtn.setBorder(pnlBorder);
		
		//Resizes the spacing between the buttons
		pnlBtn.setLayout(new GridLayout(4, 1, 0, (int)(frameSize.getHeight()*0.02)));
		
		//Resizes the button icons
		for (CookButton button : buttons) button.resizeIcon(frameSize, screenSize, 1);
	}
	
	/**
	 * Generates and saves a shopping list (populated with ingredients in alphabetical order) in an input location
	 * @param recipeArray The ingredients whose ingredients should be used in the shopping list
	 */
	public void generateShoppingList(ArrayList<Recipe> recipeArray) {
		ArrayList<Ingredient> arrangedIngredients  = arrangeIngredients(recipeArray);
		File saveLocation = getSaveLocation("Text File", "txt");
		saveList(saveLocation, arrangedIngredients);
	}
	
	/**
	 * This method extracts all of the ingredients from input ingredients and places them into an unsorted array.
	 * It then sorts all of these ingredients into alphabetical order by name, and then returns the sorted pnlRecipeIngredient array
	 * @param recipeArray The array of ingredients whose ingredients need to be ordered
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
	    	saveDirectory = CookSettings.savePath;
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
