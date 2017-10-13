package worldofzuul;

import mapAndRooms.RoomManager;

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
     * Method processes user command to action. 
     * If the user wants to get help printed, go to a different room or quit.
     *
     * @param command String argument of user command input.
     * @return Boolean return true if user wants to quit.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        // If user input is not a defined command.
        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        // If user input is request for help print.
        if (commandWord == CommandWord.HELP) {
            PrintOut.printHelp(parser);
        } // Or if user prompts for move to another room.
        else if (commandWord == CommandWord.GO) {
            rooms.goRoom(command);
        } // Or if user prompt to quit.
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        // Quit boolean, true if user wants to quit.
        return wantToQuit;
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
