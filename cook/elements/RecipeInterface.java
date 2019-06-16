package cook.elements;

import java.io.File;
import java.util.ArrayList;

import cook.CookSettings;

public class RecipeInterface {
	
	/**
	 * Gets all of the files currently contained in the program recipe save folder
	 * @return An ArrayList of all the files contained in the save folder
	 */
	public ArrayList<File> getFolderFiles() {
		//Ensures that the file saving/opening location exists
		File file = new File(CookSettings.savePath);
		file.mkdirs();
		
		//Gets all of the files saved on the computer in the CookFood save folder
		ArrayList<File> files = new ArrayList<>();
		
		for (File fileEntry : file.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	files.add(fileEntry.getAbsoluteFile());
	        }
	    }
		return files;
	}
}
