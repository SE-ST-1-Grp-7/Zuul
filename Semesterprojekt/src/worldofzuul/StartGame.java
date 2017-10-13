package worldofzuul;

import worldofzuul.items.*;

/**
 * This class instantiates the game and is the starting point of the project.
 * 
 * @author Robin
 */
public class StartGame {
    /**
     * main method and where the game is "started" (called).
     * 
     * @param args
     * 
     */
    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        System.out.println(coffee.getName());
        System.out.println("Hello Søren");
        // Game instantiation
        Game game = new Game();
        // Call play method
        game.play();
    }
}
