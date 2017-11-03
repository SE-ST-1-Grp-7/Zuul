package worldofzuul.userCommand;

/**
 * Enum type for commands assigned as constants.
 * Can be called to see if a defined constant is in the passed argument.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord
{
    // commands to check for.
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), MOVE("move"), LOOT("loot");
    
    private String commandString;
    
    /**
     * One-arg constructor method for assigning argument to parameter.
     * 
     * @param commandString     Private String argument checked for match
     *                          amongst defined enum constants.
     */
    CommandWord(String commandString) {
        this.commandString = commandString;
    }
    
    /**
     * Getter method for commandString.
     * The returning this is either null never passed to this enum or String of
     * a parsed user command.
     * 
     * @return  String return of parsed user command.
     */
    public String toString() {
        return commandString;
    }
}
