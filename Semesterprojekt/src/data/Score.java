package data;

import java.util.Comparator;

/**
 * 
 * 
 * @author Niclas Johansen & Rasmus Willer
 */
public class Score implements Comparator<Score> {
    private String name;
    private int score;

    /**
     * Blank construtor.
     */
    public Score() {
    }

    /**
     * Construtor for score including String name and int score.
     *
     * @param name      String, name of player
     * @param score     int, seconds remaining at point of winning.
     */
    public Score(String name, int score) {
        // Assign to local attributes.
        this.name = name;
        this.score = score;
    }

    /**
     * This method is a accessor method for name.
     * 
     * @return name     String, previous player name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * This method is a accessor method for score.
     * 
     * @return score    int, value of seconds remaining at winning point.
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Method for comparing scores so we can arrange them in the xml file.
     * 
     * @param score1    int, first score to compare.
     * @param score2    int, second score to compare.
     * @return          int, if - first score is higher,
     *                  else if + second score is higher,
     *                  otherwise the scores are the same.
     */
    @Override
    public int compare(Score score1, Score score2) {
        int a = score1.getScore();
        int b = score2.getScore();

        
        if (a > b) {
            return -1;
            
        } else if( a < b){
            return +1;
            
        } else {
            return 0;
        }
    }
}