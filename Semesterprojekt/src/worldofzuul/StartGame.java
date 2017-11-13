package worldofzuul;

import worldofzuul.Highscore.Highscore;

/**
 * This class instantiates the game and is the starting point of the project.
 * 
 * @author Robin & Rasmus Willer
 */
public class StartGame {
    /**
     * main method and where the game is "started" (called).
     * 
     * @param args
     * 
     */
    public static void main(String[] args) {
        // Game instantiation
        Game game = new Game("Professor Game!", 1920, 1080);
        
        // Start game thread
//        game.start();
        //Create highscores
         Highscore high = new Highscore();
        high.add("Niclas", 5000);
        high.add("Søren", 6000);
        high.add("Rasmus", 10000);
        high.createXML();
        high.printHighscore();
        
    }
}
