package data;

// IMPORTS

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Read data layout from csv file, making it available for the rest of the code.
 * The class handles loading of csv file data for 10x10 rooms.
 * 
 * @author Rasmus Willer
 */
public class ReadPreset {
    HashMap<String, String[][]> data = new HashMap<>();
    
    /**
     * Constructor of ReadPreset class. Upon instantiation of object, take
     * passed file path and read in content from csv file and pack it to be
     * used in the business layer.
     * 
     * @param path      String, file path to csv file.
     */
    public ReadPreset(String path) {
        // Declare a buffer for the file reader.
        BufferedReader fileReader = null;
        // Define a String to contain the data from a single line in the CSV.
        String line = "";
        // The split operator.
        String splitBy = ",";
        // Declare variable for temp holding the room name.
        String roomName = "";
        // Start line count of room to 0.
        int lineNo = 0;

        // Try catch for file IO operation exception handling.
        try {
            // Instantiate file reader on top of a buffer with path to CSV file.
            fileReader = new BufferedReader(new FileReader(path));
            // While stil more lines left in file.
            while ((line = fileReader.readLine()) != null) {
                // Trim leading and tailing whitespaces.
                line = line.trim();
                // If line is not empty.
                if (!"".equals(line)) {
                    // Split the line by the defined split operator.
                    String[] segments = line.split(splitBy);

                    // If reached new room in csv file, new entry in hashmap.
                    if ("-".equals(segments[0])) {
                        // Reset room line count to 0.
                        lineNo = 0;
                        // Parse name of room from same line.
                        roomName = segments[1].trim();
                        /* Create a two-dimensional array and make a HashMap
                           entry with room String as key and the two dimensional
                           array as the value. */
                        data.put(roomName, new String[10][10]);
                    // Otherwise assign ID to grid position in hashmap value[][] 
                    } else {
                        /* Iterate through each x-coordinate in room grid in CSV
                           file and look for ID numbers. */
                        for (int i = 0; i < segments.length; i++) {
                            /* If parameter isn't blank assign ID number trimmed
                               to room grid. */
                            if (!"".equals(segments[i].trim())) {
                                data.get(roomName)[lineNo][i]
                                        = segments[i].trim();
                            }
                        }
                        // Increment line count after operation.
                        lineNo++;
                    }
                }
            }

        // Exception handling.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        // Finally try to close File IO operations.
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Retrieve collected data.
     * 
     * @return      HashMap< String, String[][] >, key is name of room it's
     *              destined for, value is data for the 10x10 room grid.
     */
    public HashMap<String, String[][]> getData() {
        return data;
    }
}
