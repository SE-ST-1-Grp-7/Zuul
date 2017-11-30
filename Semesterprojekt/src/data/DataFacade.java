package data;

import Acq.IData;

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
    public void saveGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void loadXML(){
        highscore.loadXML();
    }
    
}
