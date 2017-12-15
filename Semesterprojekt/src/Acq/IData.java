package Acq;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface for data layer.
 * 
 * @author Niclas Johansen
 * @author Søren Bendtsen
 * @author Rasmus Willer
 */
public interface IData {
    /**
     * Retrieve highscore as a complex string.
     *
     * @return      String, highscore packed into a single String.
     */
    String displayHighscore();
    
    /**
     * Save game. Call the save process along with file path and
     * parsed data to be written to file.
     * 
     * @param path      String, file path to save file.
     * @param data      ArrayList&lt;ArrayList&lt;String&gt;&gt;,
     *                  2D list with parsed data.
     */
    void saveGame(String path, ArrayList<ArrayList<String>> data);
    
    /**
     * Load game. Make a new load object and let it gather data
     * from file and return it to where the method was called.
     * 
     * @param path      String, file path to save file.
     * @return              ArrayList&lt;ArrayList&lt;String&gt;&gt;, 
     *                      2D list to contain save data.
     */
    ArrayList<ArrayList<String>> loadGame(String path);
    
    /**
     *  Load the xml file.
     */
    void loadXML();
    
    /**
     * Call the save highscore method.
     * 
     * @param playerName        String, name of player.
     * @param seconds           int, time left at point of winning.
     */
    void saveHighscore(String playerName, int seconds);
    
    /**
     * Override; retrieve player name from save data.
     * 
     * @param path      String, file path of CSV file.
     * @return          String, retrieved data (name).
     */
    String retrieveName(String path);
    
    /**
     * Upon load preset data. With a file path passed, the method call
     * for read of data and retrieve it before returning it to where the method
     * was called.
     * 
     * @param path      String, file path of CSV file.
     * @return          HashMap&lt;String, String[][]&gt;, key is name of room,
     *                  value is 10x10 grid data.
     */
    HashMap<String, String[][]> loadPresetData(String path);
}