package data;

// IMPORTS

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Fetch data from previous save game data.
 * 
 * @author Rasmus Willer
 * @author Søren Bendtsen
 */
public class Load {
    // 2D list the collected data will be placed in.
    private ArrayList<ArrayList<String>> data = new ArrayList<>();
    
    /**
     * 2D data read from file -constructor.
     * 
     * @param filePath  String, path of file to make/write in.
     */
    public Load(String filePath) {
        // File IO try/catch.
        try {
            // Buffer, reader, file-path.
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(filePath));
            
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
                    if (!dataSegment.isEmpty())
                        data.add(dataSegment);
                }
            }
            
            // Flush and then close file stream.
            fileReader.close();

        // File IO error catch.
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT LOAD DATA... "
                    + "please check the save directory in the code.");
        }
    }
    
    /**
     * Retrieve the data collected from file.
     * 
     * @return      ArrayList&lt;ArrayList&lt;String&gt;&gt;,
     *              2D list of data.
     */
    public ArrayList<ArrayList<String>> getData() {
        // Return 2D list of collected data from file.
        return data;
    }
}