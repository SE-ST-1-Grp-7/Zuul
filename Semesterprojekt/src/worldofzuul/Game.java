package worldofzuul;

import worldofzuul.mapAndRooms.RoomManager;

/**
 * Game class - most of the game is handled from here.
 *
 * Contains: 
 * - Instantiation of RoomManager 
 * - Call for user commands 
 * - Quit game options 
 * - Additional class instantiations
 * 
 * @author Rasmus Willer & Søren Bendtzen
 */
public class Game {

    // Declare private Parser and Room variables. 
    private Parser parser;
    private RoomManager rooms;

    /**
     * No-args constructor. 
     * Calls for the creation of the rooms and instantiates a Parser.
     */
    public Game() {
        parser = new Parser();
        rooms = new RoomManager();
    }

    /**
     * The play method is in normal terms how to start the game. The method
     * calls the welcome message, contains the game loop and exit message.
     */
    public void play() {
        // Call the printout of the welcome message.
        PrintOut.printWelcome();

        // Declaring the game continuation variable.
        boolean finished = false;
        // Game loop.
        while (!finished) {
            // Get user command input.
            Command command = parser.getCommand();
            // Process user command.
            finished = processCommand(command);
        }
        // Quit message
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Quit handler method. If the user command isn't just quit, when print
     * error message and return the quit boolean as false.
     *
     * @param command String argument of user command input.
     * @return Boolean return true if correct quit command, otherwise false.
     */
    private boolean quit(Command command) {
        // If user command contain a second word, the boolean return false.
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } 
        // Otherwise return true, intending to quit the game.
        else {
            return true;
        }
    }
}
