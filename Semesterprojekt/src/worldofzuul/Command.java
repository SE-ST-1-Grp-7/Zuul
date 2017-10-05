package worldofzuul;

/** 
 * This class handles user-given commands. Contains methods to determine:
 * - If the user command is two words
 * - If the user command is an 'unknown' -> "?" entry
 * - Getter methods for commandWord and secondWord
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Command {
    private CommandWord commandWord;
    private String secondWord;

    /**
     * Two-args constructor. Assigns passed arguments to parameters.
     * 
     * @param commandWord   First command word from user
     * @param secondWord    Second command word from user. null if none.
     */
    public Command(CommandWord commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /**
     * Getter method for commandWord.
     * 
     * @return  CommandWord return of first command word.
     */
    public CommandWord getCommandWord() {
        return commandWord;
    }

    /**
     * Getter method for secondWord.
     * 
     * @return  String return of second command word.
     */
    public String getSecondWord() {
        return secondWord;
    }

    /**
     * Check if first command word is questionmark command, boolean return.
     * 
     * @return  boolean return true if "?" command, otherwise false.
     */
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * Check for second command word String.
     * 
     * @return  boolean true if second command word is notnull, otherwise false.
     */
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}