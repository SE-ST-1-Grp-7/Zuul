package userCommand;

// imported for declaring and assigning 'key:value' pairs.
import java.util.HashMap;

/**
 * Class for presenting and small manipulation of commands defined
 * in the enum object CommandWord in a key-value pair map object.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class CommandWords {
    /* 
     * Declaring variable of HashMap type.
     * Ex of data pair:
     * Key: String object , Value: CommandWord object
     */
    private HashMap<String, CommandWord> validCommands;

    /**
     * No-args constructor. Assign commands in CommandWord as key-value pairs
     * except for UNKNOWN.
     */
    public CommandWords() {
        // Assigning instance of HashMap to variable.
        validCommands = new HashMap<String, CommandWord>();
        
        // Iterate through map's key-value pairs.
        for(CommandWord command : CommandWord.values()) {
            // If command is not constant of UNKNOWN, add entry to map object.
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Method to return command from CommandWord; if command is not null,
     * or return UNKNOWN constant from CommandWord; if command object is null.
     * 
     * @param commandWord   String argument with parsed user command.
     * @return              String return of a command amongst CommandWord the
     *                      defined constants (commands).
     */
    public CommandWord getCommandWord(String commandWord)
    {
        // Declare and assign value from CommandWord object key to variable.
        CommandWord command = validCommands.get(commandWord);
        // If value of commandWord-key is not null, return value.
        if(command != null) {
            return command;
        }
        // Else return constant of CommandWord.UNKNOWN instead of key-value.
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Getter method for checking if valid command, returns boolean.
     * 
     * @param aString   String argument to be authenticated.
     * @return          Boolean return true if validCommands contains aString as
     *                  a key, otherwise false.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Print method; iterate through all keys in map object and print each key
     * separated by a [space].
     */
    public void showAll() 
    {
        // Print each key followed by a [space] (no newline)
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        // Print empty line (solution for a final newline)
        System.out.println();
    }
}
