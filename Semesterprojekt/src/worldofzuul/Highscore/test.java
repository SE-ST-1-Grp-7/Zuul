/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Highscore;

import worldofzuul.Game;

/**
 *
 * @author niclasjohansen
 */
public class test {
     public static void main(String[] args) {
        //Create highscores
         Highscore high = new Highscore();
        high.add("Niclas", 5000);
        high.add("Søren", 6000);
        high.add("Rasmus", 10000);
        high.createXML();
        high.printHighscore();
        
    }
     
private void winGame(){
    
    
}
}
