package data;

import java.util.Comparator;

/**
 *
 * @author Niclas Johansen
 */

public class Score implements Comparator<Score> {
    
    private String name; // Instantiate players name
    private int score; // Instantiate score for player

    /**
     * No args construtor.
     */
    public Score() {

    }

    /**
     * Construtor for score including String name and int score.
     *
     * @param name
     * @param score
     */
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * This method is a accessor method for name.
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * This method is a accessor method for score.
     * @return score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * This method is a mutator method for name. 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * This method is a mutator method for score. 
     * @param score 
     */
    public void setScore(int score) {
        this.score = score;
    }
    /**
     * method for comparing scores so we can arrange them in the xml file.
     * @param score1
     * @param score2
     * @return 
     */
    @Override
    public int compare(Score score1, Score score2) {
        int a = score1.getScore();
        int b = score2.getScore();

        if (a > b) {
            return -1;
        }
        else if( a < b){
            return +1;
        }
        else {
            return 0;
        }

    }

}