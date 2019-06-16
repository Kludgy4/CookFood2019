# CookFood

CookFood is a java program that exists to ease the weekly creation of shopping lists given frequently cooked recipes.
It does this by integrating three different features together. 
1. The program takes input recipe data  (like name and cookbook, along with their ingredients and relevant quantities) which can be saved locally for later use. 
2. This information attached to these recipes can be viewed and revised  at any point. 
3. The recipes a user wishes to buy ingredients for (those they are cooking in a given week) can be selected, and CookFood will automatically generate a shopping list for them populated with their ingredients and quantities 

## Getting Started
To run this project on your computer, download the latest version from this GitHub page under the releases tab, and follow the below instructions
### Prerequisites
This project currently requires the following to run:

* Java Version 1.8 >= 191
* Windows, OSX or Linux Operating Systems

### Installing
1. Download the latest version of this program from the releases tab - [Releases](https://github.com/Kludgy4/CookFood2019/releases)
2. Unzip the downloaded program folder
3. Move the unzipped folder to a convenient location on your computer
4. If you are using Windows, double-click "RUNME.bat", otherwise, just double-click "CookFood.jar"
5. CookFood should now open ready for you to use. If you encounter any issues during the installation process, please see the FAQ below

## Usage
This section assumes that you have been able to open the program as instructed above
### Creating/Editing a Recipe
* Select the "Add Recipe" button that is displayed on the main interface
* Click on the boxes labelled "Recipe Title" and "Cookbook", and input relevant recipe data into them
* To add ingredients to the recipe, see the "Adding/Removing Ingredients" section
* Select the "Submit Recipe" button to save the input data to the system
* The recipe creation screen should disappear and the recipe you just created should appear on the right hand side of the screen

### Adding/Removing Ingredients
* Click on the boxes labelled "Ingredient Name" input the name of the ingredient you want to add
* Type the positive integer quantity of the ingredient that you wish to add
* Select the quantity type you wish to be associated with this ingredient using the ComboBox that defaults to "GRAMS"
* Click the "Add Ingredient" button if you wish to save this ingredient into system memory. The added ingredient should appear on the right-hand side of the screen
* To remove ingredient(s), select the checkbox(es) next to their names on the right-hand side of the screen, and select the "Delete Ingredient(s)" button

### Deleting Recipes
* To delete recipes from CookFood, select the checkbox(es) next to their titles on the right-hand side of the main interface, and select the "Delete Recipe(s)" button
* Select "Yes" on the prompt to finalise the deletion of the selected recipe(s)

### Editing Recipes
* To edit a recipe saved in CookFood, select the checkbox next to the title of the recipe you wish to edit on the right-hand side of the main interface, then select the "Edit Recipe" button
* Edit the recipe information as in the "Creating/Editing a Recipe" section above. If you change the title of the recipe, CookFood will stop editing and instead save the recipe as a duplicate

### Generating a Shopping List
* To generate a shopping list containing the ingredients saved within saved CookFood recipes, select the checkbox(es) next to the title(s) of the recipe(s) you wish to edit on the right-hand side of the main interface, then select the "Generate List" button
* Navigate using the prompt to a location on your computer that you wish to save the recipe in, and then, after entering a "File Name" to save the shopping list with, select the "Save" button
* CookFood has now saved the generated Shopping List in the location you specified on your system. Open this file to view and print your List

## FAQ
### CookFood isn't opening when I double-click it
You probably haven't installed java correctly on your computer. To reinstall java, go to [this](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) website, and download the latest and correct version of java for you

### Where does CookFood keep saved recipes?
When you open CookFood for the first time, you should see a folder called "CookFoodRecipes" created on the same system level as where you keep the "CookFood.jar" you unzipped earlier. Created recipes are saved into this folder. Please don't introduce new files into this folder, or alter saved files, without prior knowledge of the saving and opening algorithm, as it will cause errors to occur

### If I delete a recipe, is it gone forever?
Yes! Don't delete recipes without careful thought first

### Why are some buttons greyed out?
If a button is greyed out, it means that you aren't able to use that button given the information you have currently provided the system with. For example, if you haven't selected a recipe on the main interface, you won't be able to use the "Delete", "Edit", or "Generate" buttons. Try selecting some boxes or inputting more data into the system to continue

### Why does the program keep on talking about Recipes?
Although a Recipe is technically "A set of instructions for preparing a particular dish, including a list of the ingredients required", for the purposes of this program a Recipe is simply the list of ingredients (and other key information) used to create a specific dish

## Authors and Acknowledgement
* **Matthew Brian** - *Initial Work* - [Kludgy4](https://github.com/Kludgy4)

## License
[MIT](https://choosealicense.com/licenses/mit/)

## Acknowledgements
* Barker College SDD Class of 2019