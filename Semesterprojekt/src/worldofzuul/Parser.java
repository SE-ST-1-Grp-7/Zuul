package worldofzuul;

// Import Scanner so we can get user inputs.
import java.util.Scanner;

/**
 * This class receives user input and manipulates it to a single or double word
 * command defined in CommandsWords class.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Parser 
{
    // Declare commands as a CommandWords object type.
    private CommandWords commands;
    // Declare reader as a Scanner object type.
    private Scanner reader;

    /**
     * No-args constructor.
     */
    public Parser() 
    {
        // Assign instantiation of CommandWords class to variable.
        commands = new CommandWords();
        // Assign instantiation of Scanner class to variable.
        reader = new Scanner(System.in);
    }

    /**
     * Method for getting user input and split the string at [space] if
     * applicable and then pass the manipulated user input to check user input
     * for matching defined command, before finally returning the further
     * manipulated user input through an instantiation of Command class.
     * 
     * @return      Command object return new instantiation.
     */
    public Command getCommand() 
    {
        // Declare inputLine String to contain user input later.
        String inputLine;
        // Declare String to link to first word of user input later.
        String word1 = null;
        // Declare String to link to second word of user input later if any.
        String word2 = null;

        // Print that will be viewed in front of user input area.
        System.out.print("> ");
        
        // Scanner collect next line of input.
        inputLine = reader.nextLine();

        // Pass the user input to new Scanner object.
        Scanner tokenizer = new Scanner(inputLine);
        
        /**
         * If the user input contains multiple entries, divided by [space],
         * assign each entry to separate variables.
         */
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        // Return the manipulated user input (command).
        return new Command(commands.getCommandWord(word1), word2);
    }

    /**
     * Call instance method to print all commands defined in CommandWords class.
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
