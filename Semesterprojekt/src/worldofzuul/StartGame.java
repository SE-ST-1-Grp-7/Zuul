package worldofzuul;

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
        System.out.println("Hello jonas");
        // Game instantiation
        Game game = new Game();
        // Call play method
        game.play();
    }
}
