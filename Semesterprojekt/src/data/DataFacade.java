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
    
    @Override
    /**
     * Method to return our highScore as an string with the toString method.
     *
     * @return string text
     */
    public String toString() {
        String text = "";
        int i = 1;
        for (Score score : highscore.highScore) {
            text += i + ".\t\t" + score.getName() + "\t\t  " + score.getScore() + "\n";
            i++;
        }
        return text;
    }
}
