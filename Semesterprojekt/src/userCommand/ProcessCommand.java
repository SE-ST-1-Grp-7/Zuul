package userCommand;

import mapAndRooms.RoomManager;
import worldofzuul.PrintOut;
import worldofzuul.Quit;

/**
 *
 * @author Rasmus Willer & Magnus Mortensen
 */
public class ProcessCommand {
    private Parser parser;
    private Command command;
    private RoomManager rooms;
    
    public ProcessCommand() {
        parser = new Parser();
    }
    
    /**
     * Method processes user command to action. 
     * If the user wants to get help printed, go to a different room or quit.
     *
     * @param rooms     Room Manager object with room definitions.
     * @return          Boolean return true if user wants to quit.
     */
    public boolean process(RoomManager rooms) {
        this.parser = parser;
        this.rooms = rooms;
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        // If user input is not a defined command.
        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (null != commandWord) { // If user input is request for help print.
            switch (commandWord) {
            // Or if user prompts for move to another room.
                case HELP:
                    PrintOut.printHelp(parser);
                    break;
            // Or if user prompt to quit.
                case GO:
                    rooms.goRoom(command);
                    break;
                case QUIT:
                    wantToQuit = Quit.quit(command);
                    break;
                default:
                    break;
            }
        }
        
        // Quit boolean, true if user wants to quit.
        return wantToQuit;
    }
    
    public void getCommand() {
        command = parser.getCommand();
    }
    
    public RoomManager getRooms() {
        return rooms;
    }
}
