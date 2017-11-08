package worldofzuul;

import worldofzuul.userCommand.Parser;
import worldofzuul.userCommand.CommandWord;
import worldofzuul.mapAndRooms.Room;
import worldofzuul.mapAndRooms.RoomManager;

/**
 *
 * @author Rasmus Willer
 */
public class PrintOut {
    /**
     * Print constructor for providing a welcome message.
     */
    public static void printWelcome(Link link) {
        Room currentRoom = new RoomManager(link).getCurrentRoom();
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        // Finish off with the long description of the current room.
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Print help method. Writes an explanation of what situation the user is in
     * and what commands now are available.
     */
    public static void printHelp(Parser parser) {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
}
