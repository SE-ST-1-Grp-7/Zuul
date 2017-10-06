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
 * @author Michael Kolling and David J. Barnes
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
        Room garden, relaxing_room, teacher_room, pub, outside, hallway_1;
        Room dininghall, hallway_2, lecturehall_1, hallway_3, lecturehall_2, toilet;

        // Instantiate the rooms and their descriptions.
        garden = new Room(" outside in the lovely garden... smells lovely of roses");
        relaxing_room = new Room("in a nice and cozy relaxing room");
        teacher_room = new Room("in your own room, finaly some peace...");
        pub = new Room("in the campus pub");
        outside = new Room("outside the main entrance of the university");
        hallway_1 = new Room("you are moving along the hallway");
        dininghall = new Room("in the dining hall, time to nom!");
        hallway_2 = new Room("you are moving along the hallway");
        lecturehall_1 = new Room("in a lecturehall, the lights are flickering...");
        hallway_3 = new Room("you are moving along the hallway");
        lecturehall_2 = new Room("in a lecturehall, everything is working... weird...");
        toilet = new Room("pooping");

        /* Define the exit-waypoints:
           From 'garden' Room instance. */
        garden.setExit("east", relaxing_room);
        garden.setExit("south", outside);

        /* Define the exit-waypoints:
           From 'relaxing room' Room instance. */
        relaxing_room.setExit("west", garden);
        relaxing_room.setExit("south", hallway_1);

        /* Define the exit-waypoints:
           From 'teachers' room' Room instance. */
        teacher_room.setExit("south", dininghall);
        teacher_room.setExit("east", pub);

        /* Define exit-waypoints:
           From 'pub' Room instance. */
        pub.setExit("west", teacher_room);
        pub.setExit("south", hallway_2);

        /* Define exit-waypoints:
           From 'outside' Room instance. */
        outside.setExit("north", garden);
        outside.setExit("south", lecturehall_1);

        /* Define exit-waypoints:
           From 'hallway_1' Room instance. */
        hallway_1.setExit("north", relaxing_room);
        hallway_1.setExit("east", dininghall);

        /* Define exit-waypoints:
           From 'dining hall' Room instance. */
        dininghall.setExit("north", teacher_room);
        dininghall.setExit("south", lecturehall_2);
        dininghall.setExit("west", hallway_1);

        /* Define exit-waypoints:
           From 'hallway 2' Room instance. */
        hallway_2.setExit("north", pub);
        hallway_2.setExit("south", toilet);

        /* Define exit-waypoints:
           From 'lecture hall 1' Room instance. */
        lecturehall_1.setExit("north", outside);
        lecturehall_1.setExit("east", hallway_2);

        /* Define exit-waypoints:
           From 'hallway 3' Room instance. */
        hallway_3.setExit("west", lecturehall_1);
        hallway_3.setExit("east", lecturehall_2);

        /* Define exit-waypoints:
           From 'lecture hall 2' Room instance. */
        lecturehall_2.setExit("west", hallway_3);
        lecturehall_2.setExit("north", dininghall);
        lecturehall_2.setExit("east", toilet);
        
        /* Define exit-waypoints:
           From 'toilet' Room instance. */
        toilet.setExit("west", lecturehall_2);
        toilet.setExit("north", hallway_2);

        // Assign the Room object reference 'teachers' room' as the currentRoom object.
        currentRoom = teacher_room;
    }

    /**
     * The play method is in normal terms how to start the game. The method
     * calls the welcome message, contains the game loop and exit message.
     */
    public void play() {
        // Call the printout of the welcome message.
        printWelcome();

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
            printHelp();
        } // Or if user prompts for move to another room.
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } // Or if user prompt to quit.
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
     * @param command String argument of user command input.
     */
    private void goRoom(Command command) {
        // If no direction after go-command, print line and return. 
        if (!command.hasSecondWord()) {
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
        } /* Update current room reference and print long description of new
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
