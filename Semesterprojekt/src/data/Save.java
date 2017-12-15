package data;

// IMPORTS

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Save class, writes data segments to file.
 * 
 * @author Rasmus Willer
 * @author Robin Petersen
 * @author Søren Bendtsen
 */
public class Save {
    
    // CONSTRUCTORS
    
    /**
     * No-arg constructor for Save class. Makes sure the save folder exist.
     */
    public Save() {
        // Create folder if it does not exist.
        makeSaveFolder();
    }
    
    /**
     * Two-dimensionel data write to file -constructor.
     * 
     * @param filePath  String, path of file to make/write in.
     * @param data      ArrayList&lt;ArrayList&lt;String&gt;&gt;,
     *                  list with data to save.
     */
    public Save(String filePath, ArrayList<ArrayList<String>> data) {
        this();
        // File IO try/catch.
        try {
            // Buffer, writer, file-path.
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath)));

            // Write data segments to file.
            for (ArrayList<String> dataSegment: data) {
                // Initial data segment.
                fileWriter.append(dataSegment.get(0));
                // Iterate through the remaining.
                for (int i = 1; i < dataSegment.size();i++) {
                    // Separator.
                    fileWriter.append(",");
                    // New data segment.
                    fileWriter.append(dataSegment.get(i));
                }
                // Finish player data off with a newline.
                fileWriter.append("\n");
            }
            
            // Flush and then close file stream.
            fileWriter.close();

        // File IO error catch.
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE DATA... "
                    + "please check the save directory in the code.");
        }
    }
    
    /**
     * Create save folder if it does not already exist.
     */
    public final void makeSaveFolder() {
        File folder = new File("saveFiles\\");
        // If folder does not exist, create directory.
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}