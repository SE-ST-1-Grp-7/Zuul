package data;

import Acq.IData;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Niclas Johansen & Rasmus Willer
 */
public class DataFacade implements IData {

    private Highscore highscore;

    public DataFacade() {
        this.highscore = new Highscore();
    }
    
    
    /**
     * Method to return our highScore as an string with the toString method.
     *
     * @return string text
     */
    @Override
    public String displayHighscore() {
        return highscore.displayHighscore();
    }
    
    /**
     * Override, upon save game. Call the save process along with file path and
     * parsed data to be written to file.
     * 
     * @param path      String, file path to save file.
     * @param data      ArrayList<ArrayList<String>>, 2D list with parsed data.
     */
    @Override
    public void saveGame(String path, ArrayList<ArrayList<String>> data) {
        new Save(path, data);
    }
    
    /**
     * Override, upon load game. Make a new load object and let it gather data
     * from file, before afterwards calling a getter method to retrieve the
     * data and return it to the business layer through the interface.
     * 
     * @param filePath      String, file path to save file.
     * @return              ArrayList<ArrayList<String>>, 
     *                      2D list to contain save data.
     */
    @Override
    public ArrayList<ArrayList<String>> loadGame(String filePath) {
        // Instantiate load object.
        Load load = new Load(filePath);
        // Retrieve collected data from the getData method.
        ArrayList<ArrayList<String>> data = load.getData();
        // Return the retrieved data.
        return data;
    }
    /**
     *  Load the xml file.
     */
    @Override
    public void loadXML(){
        highscore.loadXML();
    }
    
    /**
     * 
     * @param playerName
     * @param seconds 
     */
    @Override
    public void saveHighscore(String playerName, int seconds){
        highscore.saveHighscore(playerName,seconds);
    }
    
    /**
     * Override; retrieve player name from save data.
     * 
     * @param path      String, file path of CSV file.
     * @return          String, retrieved data (name).
     */
    @Override
    public String retrieveName(String path) {
        // Instantiate load object.
        Load load = new Load(path);
        // Retrieve collected data from the getData method.
        ArrayList<ArrayList<String>> data = load.getData();
        // Return the retrieved data.
        return data.get(0).get(3);
    }
    
    /**
     * Override; upon load preset data. With a file path passed, the method call
     * for read of data and retrieve it before returning it to where it was
     * requested.
     * 
     * @param path      String, file path of CSV file.
     * @return          HashMap< String, String[][] >, key is name of room,
     *                  value is 10x10 grid data.
     */
    @Override
    public HashMap<String, String[][]> loadPresetData(String path) {
        ReadPreset presetData = new ReadPreset(path);
        return presetData.getData();
    }
}
