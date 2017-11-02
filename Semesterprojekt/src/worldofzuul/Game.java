package worldofzuul;

import worldofzuul.People.Player;
import worldofzuul.userCommand.ProcessCommand;
import worldofzuul.mapAndRooms.RoomManager;

/**
 * Game class - most of the game is handled from here.
 *
 * Contains: 
 * - Instantiation of RoomManager 
 * - Quit game options 
 * - Additional class instantiations
 * 
 * @author Rasmus Willer & Søren Bendtsen
 */
public class Game implements Runnable {
    // Game loop variable, initially as not running.
    private boolean running = false;
    // Game process thread.
    private Thread thread;
    // Declare private RoomManager & ProcessCommand variables. 
    private RoomManager rooms;
    private ProcessCommand command;
    private Player player = new Player("Johammed",14,0,0);
    
    

    /**
     * No-args constructor. 
     * Calls for the creation of the rooms and instantiates a Parser.
     */
    public Game() {
        rooms = new RoomManager();
        command = new ProcessCommand();
    }
    
    public void tick() {
        // GAME LOGIC UPDATING
        
        // Get user command input.
        command.getCommand();
        // Process user command.
        running = command.process(rooms, player);
        // Update rooms object
        rooms = command.getRooms();
    }
    
    public void render() {
        // GAME GRAPHIC UPDATING
    }
    
    /**
     * The play method is in normal terms how to start the game. The method
     * calls the welcome message, contains the game loop and exit message.
     */
    public void run() {
        // GAME TIME -section
        
        // Defining what the game's frames per second should strive for.
        int ticksPerSecond      = 60;
        /* Time at last tick. Nanoseconds for precision.
         * Time assigned for this because it needs value pre game loop.
         */
        long timeThen           = System.nanoTime();
        // Current time. Only defined, gets value in game loop.
        long timeNow;
        // Nanoseconds per frame is the tick rate.
        double tickTime         = 1000000000 / ticksPerSecond;
        /* Will be counting the time since last tick divided by tick rate.
         * Will be reset after accummulating to 1.
         */
        double timeDiffFactor   = 0;
        // Counter will reset after accumulating to a whole second.
        long secondKeeper       = 0;
        // Tick count for the current second.
        int tickCount           = 0;
        
        
        // Call the printout of the welcome message.
        PrintOut.printWelcome();
        
        // GAME LOOP
        while (running) {
            // GAME LOOP TIME -section
            
            // Assign currrent time.
            timeNow         = System.nanoTime();
            // Add time difference percentage.
            timeDiffFactor  += (timeNow - timeThen) / tickTime;
            // Add time difference since last tick.
            secondKeeper    += timeNow - timeThen;
            // Update last tick time to this tick time.
            timeThen        = timeNow;
            
            /* If time difference percentage accumulates to 1:
             * Tick and render, and decrease time diff factor.
             */
            if (timeDiffFactor >= 1) {
                // Tick update.
                tick();
                // Render update.
                render();
                // Tick count increase.
                tickCount++;
                // Substract 1 from time difference percentage.
                timeDiffFactor--;
            }
            
            // If a second or more has passed reset counts.
            if (secondKeeper >= 1000000000) {
                // Printout frame rate.
                //System.out.println("Ticks and frames: " + tickCount);
                
                tickCount       = 0;
                secondKeeper    = 0;
            }
        }
        
        // Stop thread.
        stop();
        
        // Quit message
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    public RoomManager getRoomManager() {
        return rooms;
    }
    
    /**
     * Start thread.
     * Synchronized to insure actions don't overlap.
     */
    public synchronized void start() {
        // If game loop is already running, don't do anything.
        if (running) {
            return;
        }
        // Turn game loop variable to true.
        running = true;
        // Assign thread with this object.
        thread = new Thread(this);
        // Initialize thread.
        thread.start();
    }
    
    /**
     * Stop thread.
     * Synchronized to insure actions don't overlap.
     */
    public synchronized void stop() {
        // If game loop not running, don't do anything.
        if (!running)
            return;
        // Turn game loop variable to false.
        running = false;
        try {
            // End thread.
            thread.join();
        // Catch any possible interrupted exception and print to console.
        } catch (InterruptedException err) {
            System.out.println(err);
        }
    }
}
