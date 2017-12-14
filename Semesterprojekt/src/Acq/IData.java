package Acq;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface for data layer.
 * 
 * @author Niclas Johansen, Søren Bendtsen, & Rasmus Willer
 */
public interface IData {
    // Display the highscore
    String displayHighscore();
    
    // Save game method.
    void saveGame(String path, ArrayList<ArrayList<String>> data);
    
    // Load game method.
    ArrayList<ArrayList<String>> loadGame(String path);
    
    // Load the XML file
    void loadXML();
    
    //Save the highscore
    void saveHighscore(String playerName, int seconds);
    
    // Retrieve player name.
    String retrieveName(String path);
    
    // Load presets.
    HashMap<String, String[][]> loadPresetData(String path);
}