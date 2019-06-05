package cook.recipe.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import cook.elements.Ingredient;
import cook.elements.Recipe;
import cook.recipe.frames.FrRecipe;

@SuppressWarnings("serial")
public class PnlRecipe extends JPanel {

	PnlRecipeTitle pnlTitle;
	PnlIngredient pnlIngredient;
	PnlSubmit pnlSubmit;
	
	FrRecipe parent;
	
	public PnlRecipe(FrRecipe parent) {
		this.parent = parent;
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 12, Color.BLACK));
		
		//Constructs onscreen elements
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		addFrames();
	}
	
	public void addFrames() {
		pnlTitle = new PnlRecipeTitle(this);
		pnlIngredient = new PnlIngredient(this);
		pnlSubmit = new PnlSubmit(this);
		
		add(pnlTitle, BorderLayout.NORTH);
		add(pnlIngredient, BorderLayout.CENTER);
		add(pnlSubmit, BorderLayout.SOUTH);
	}

	public PnlRecipe(Recipe recipeInfo) {
		super();
		//Preload data into boxes here
	}
	
	public void saveRecipe(Recipe recipe) {
		//Reserves/Creates the relevant directory and file for recipe output
		File file = new File((getClass().getResource("../../") + "CookFoodRecipes").substring(6));
		file.mkdirs();
		file = new File((getClass().getResource("../../") + "CookFoodRecipes/" + recipe.title + ".ckf").substring(6));
		
		//Writes recipe information to the given file
		try {
			FileWriter writer = new FileWriter(file);
			writer.append(recipe.title + "," + recipe.cookbook + "\n");
			for (Ingredient i : recipe.ingredients) {
				writer.append(i.name + "," + i.quantity + "," + i.quantityType.getMultipleType() + "\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("There was an error saving your recipe");
			e.printStackTrace();
		}
		
	}

	public void resizeElements(Dimension frameSize, Dimension screenSize) {
		
		pnlTitle.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*0.2)));
		pnlTitle.resizeElements(new Font("Arial", Font.ITALIC, (int)(frameSize.getHeight()*0.04)));
		pnlIngredient.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*0.6)));
		pnlIngredient.resizeElements(frameSize, screenSize);
		pnlSubmit.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*0.2)));
		pnlSubmit.resizeElements(frameSize, screenSize);
		updateUI();
		
		//Resizes the button font and buttonIcon sizes
		
		
		//Resizes the border spacing around the buttons
//		pnlBorder = new EmptyBorder((int)(frameSize.getHeight()*0.06), (int)(frameSize.getWidth()*0.08),
//				(int)(frameSize.getHeight()*0.06), (int)(frameSize.getWidth()*0.2));
//		pnlBtn.setBorder(pnlBorder);
		
		//Resizes the spacing between the buttons
//		pnlBtn.setLayout(new GridLayout(4, 1, 0, (int)(frameSize.getHeight()*0.02)));
		
		//Resizes the button icons
//		for (CookButton button : buttons) button.resizeIcon(frameSize, screenSize, 1);
	}

}
