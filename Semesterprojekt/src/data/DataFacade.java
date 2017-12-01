package data;

import Acq.IData;
import java.util.ArrayList;

/**
 *
 * @author Niclas Johansen
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

    @Override
    public void saveGame(String path, ArrayList<ArrayList<String>> data) {
        new Save(path, data);
    }
    
    @Override
    public ArrayList<ArrayList<String>> loadGame(String filePath) {
        Load load = new Load(filePath);
        ArrayList<ArrayList<String>> data = load.getData();
        return data;
    }
    
    @Override
    public void loadXML(){
        highscore.loadXML();
    }
    
}
