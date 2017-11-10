package worldofzuul;

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
        game.start();
        
    }
}
