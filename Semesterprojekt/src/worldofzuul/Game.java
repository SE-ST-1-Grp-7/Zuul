package worldofzuul;

/**
 * Game class - most of the game is handled from here.
 * 
 * Contains:
 * - Which rooms are present in the game and their interconnections
 * - Call for user commands
 * - Game messages
 * - Quit game options
 * - Help print
 * - Additional class instantiations
 * 
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game {
    // Declare private Parser and Room variables. 
    private Parser parser;
    private Room currentRoom;
    
    /**
     * No-args constructor.
     * Calls for the creation of the rooms and instantiates a Parser.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Method declares and configure the rooms of the game.
     */
    private void createRooms() {
        // Declare room objects.
        Room outside, theatre, pub, lab, office, test;
        
        // Instantiate the rooms and their descriptions.
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // A special room instantiation intended for testing.
        test = new Room("in the testRoom");
        
        /* Define exit-waypoints:
           From 'outside' Room instance. */
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", test);

        /* Reverse-define the same exit-waypoints:
           To 'outside' Room instance. */
        theatre.setExit("west", outside);
        lab.setExit("north", outside);
        pub.setExit("east", outside);
        test.setExit("south", outside);
        
        // Define exit-waypoints between Room instances inside.
        lab.setExit("east", office);
        office.setExit("west", lab);

        // Assign the Room object reference 'outside' as the currentRoom object.
        currentRoom = outside;
    }

    /**
     * The play method is in normal terms how to start the game.
     * The method calls the welcome message, contains the game loop and exit
     * message.
     */
    public void play() {
        // Call the printout of the welcome message.
        printWelcome();

        // Declaring the game continuation variable.
        boolean finished = false;
        // Game loop.
        while (! finished) {
            // Get user command input.
            Command command = parser.getCommand();
            // Process user command.
            finished = processCommand(command);
        }
        // Quit message
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print method for providing a welcome message.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        // Finish off with the long description of the current room.
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Method processes user command to action.
     * If the user wants to get help print, go to a different room or quit.
     * 
     * @param command   String argument of user command input.
     * @return          Boolean return true if user wants to quit.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        // If user input is not a defined command.
        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        // If user input is request for help print.
        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        // Or if user prompts for move to another room.
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        // Or if user prompt to quit.
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        // Quit boolean, true if user wants to quit.
        return wantToQuit;
    }

    /**
     * Print help method. Writes an explanation of what situation the user is in
     * and what commands now are available.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Go to a different room and update current room.
     * 
     * @param command   String argument of user command input.
     */
    private void goRoom(Command command) {
        // If no direction after go-command, print line and return. 
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        // Get second parsed command word and assign it to String variable.
        String direction = command.getSecondWord();

        /* Assign next room according to matching direction defined in the
           createRooms method */
        Room nextRoom = currentRoom.getExit(direction);

        // If there is no such direction print message.
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        /* Update current room reference and print long description of new
           current room. */
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * Quit handler method. If the user command isn't just quit, when print 
     * error message and return the quit boolean as false.
     * 
     * @param command   String argument of user command input.
     * @return          Boolean return true if correct quit command, otherwise
     *                  false.
     */
    private boolean quit(Command command) {
        // If user command contain a second word, the boolean return false.
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        // Otherwise return true, intending to quit the game.
        else {
            return true;
        }
    }
}
