package data;

import Acq.IData;
import java.util.ArrayList;

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
        String text = "";
        int i = 1;
        for (Score score : highscore.highScore) {
            text += i + ".\t\t" + score.getName() + "\t\t  " + score.getScore() + "\n";
            i++;
        }
        return text;
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
    
    @Override
    public void loadXML(){
        highscore.loadXML();
    }
    
}
