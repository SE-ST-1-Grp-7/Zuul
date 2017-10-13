package worldofzuul;

/**
 *
 * @author Rasmus Willer
 */
public class ProcessCommand {
    /**
     * Method processes user command to action. 
     * If the user wants to get help printed, go to a different room or quit.
     *
     * @param command String argument of user command input.
     * @return Boolean return true if user wants to quit.
     */
    private boolean process(Parser parser, Command command) {
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
            wantToQuit = Quit.quit(command);
        }
        // Quit boolean, true if user wants to quit.
        return wantToQuit;
    }
}
