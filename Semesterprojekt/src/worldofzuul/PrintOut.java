package worldofzuul;

import worldofzuul.People.Player;
import worldofzuul.People.Student;
import worldofzuul.items.Coffee;
import worldofzuul.items.Item;
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
        displayRoom(currentRoom);
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
    /** --for testing purposes, can & should be refined later--
     * displays the room in a 10x10, 2d space
     * objects in the room are displayed by the first
     * letter of the objects type.
     * empty space equals null - aka no object is in that location
     * @param currentRoom 
     */
    public static void displayRoom(Room currentRoom) {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j< 10; j++) {
                if(currentRoom.roomArray[i][j] instanceof Player) {
                    System.out.print("|" + "p");
                } else if(currentRoom.roomArray[i][j] instanceof Item) {
                    System.out.print("|" + "i");
                }
                 else if(currentRoom.roomArray[i][j] instanceof Student) {
                    System.out.print("|" + "s");
                }
                 else {
                  System.out.print("| ");  
                }
            }
            System.out.println("");
        }
        
    }
}
