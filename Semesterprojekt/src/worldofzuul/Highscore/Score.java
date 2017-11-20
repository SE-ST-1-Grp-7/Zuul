/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Highscore;

import java.util.Comparator;

/**
 *
 * @author niclasjohansen
 */

public class Score implements Comparator<Score> {
    
    /**
     * Name of player.
     */
    private String name;

    /**
     * The int score for our HighScore.
     */
    private int score;

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