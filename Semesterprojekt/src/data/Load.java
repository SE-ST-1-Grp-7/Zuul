package data;

// IMPORTS

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Rasmus Willer
 */
public class Load {
    private ArrayList<ArrayList<String>> data = new ArrayList<>();
    
    /**
     * Two-dimensionel data read from file -constructor.
     * 
     * @param filePath  String, path of file to make/write in.
     * @return          ArrayList<ArrayList<String>>, list with gathered data.
     */
    public ArrayList<ArrayList<String>> Load(String filePath) {
        // File IO try/catch.
        try {
            // Buffer, reader, file-path.
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(System.getProperty("user.home")
                            + filePath));
            
            String line;
            // Continue to iterate as long as there is file content.
            while ((line = fileReader.readLine()) != null) {
                // Get all tokens available in line
                String[] tokens = line.split(",");
                // If not blank line.
                if (tokens.length > 0) {
                    // Create a new ArrayList to contain sub-list elements.
                    ArrayList<String> dataSegment = new ArrayList<>();
                    // For each data field, add to sub-list.
                    for (String str: tokens) {
                        dataSegment.add(str.trim());
                    }
                    // Finally add sub-list to top level list.
                    data.add(dataSegment);
                }
            }
            
            // Flush and then close file stream.
            fileReader.close();

        } catch (IOException e) { // File read error print.
            System.err.println("BEEP BOOP, COULDNT LOAD ITEMS... "
                    + "please check the save directory in the code.");
        }
        
        // Return 2D list of collected data from file.
        return data;
    }
}
