package Acq;

import java.util.ArrayList;

/**
 *
 * @author J & Rasmus Willer
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
}
