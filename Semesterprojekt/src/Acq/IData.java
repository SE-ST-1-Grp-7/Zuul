package Acq;

import java.util.ArrayList;

/**
 *
 * @author J
 */
public interface IData {
    // Display the highscore
    String displayHighscore();
    
    // Save game method
    void saveGame(String path, ArrayList<ArrayList<String>> data);
    
    ArrayList<ArrayList<String>> loadGame(String path);
    
    // Load the XML file
    void loadXML();
}
