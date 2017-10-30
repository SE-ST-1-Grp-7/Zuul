package worldofzuul;

import worldofzuul.userCommand.ProcessCommand;
import worldofzuul.mapAndRooms.RoomManager;

/**
 * Game class - most of the game is handled from here.
 *
 * Contains: 
 * - Instantiation of RoomManager 
 * - Quit game options 
 * - Additional class instantiations
 * 
 * @author Rasmus Willer & Søren Bendtsen
 */
public class Game {

    // Declare private Parser and Room variables. 
    private RoomManager rooms;
    private ProcessCommand command;

    /**
     * No-args constructor. 
     * Calls for the creation of the rooms and instantiates a Parser.
     */
    public Game() {
        rooms = new RoomManager();
        command = new ProcessCommand();
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
            command.getCommand();
            // Process user command.
            finished = command.process(rooms);
            // Update rooms object
            rooms = command.getRooms();
        }
        // Quit message
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    public RoomManager getRoomManager() {
        return rooms;
    }
    
}
