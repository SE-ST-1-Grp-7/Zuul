package worldofzuul;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import worldofzuul.gfx.Assets;
import worldofzuul.gfx.Display;
import worldofzuul.input.KeyManager;
import worldofzuul.input.MouseManager;
import worldofzuul.mapAndRooms.RoomManager;
import worldofzuul.states.GameState;
import worldofzuul.states.MainMenuState;
import worldofzuul.states.SettingState;
import worldofzuul.states.State;

/**
 * Game class - most of the game is handled from here.
 *
 * Contains: - Instantiation of RoomManager - Quit game options - Additional
 * class instantiations
 *
 * @author Rasmus Willer & Søren Bendtsen
 */
public class Game implements Runnable {

    // Game loop variable, initially as not running.
    private boolean running = false;
    private int width, height;
    public String title;

    // OBJECT DECLERATION
    private Link link;
    private Display display;
    private RoomManager rooms;
    private KeyManager keyManager;
    private MouseManager mouseManager;

    private BufferStrategy bs;
    private Graphics g;
    private Thread thread;

    // STATES
    public State gameState;
    public State settingsState;
    public State mainMenuState;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    public void tick() {
        // GAME LOGIC UPDATING

        keyManager.tick();

        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    public void render() {
        // GAME GRAPHIC UPDATING

        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear Screen
        g.clearRect(0, 0, width, height);

        if (State.getState() != null) {
            State.getState().render(g);
        }

        bs.show();
        g.dispose();
    }

    public void run() {
        // DISPLAY -section

        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        // same for canvas, so it will be handled no matter which is in focus
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        // INITS & INSTANTIATIONS
        link = new Link(this);
        Assets.init();

        // STATE INSTANTIATION & SETTING
        mainMenuState = new MainMenuState(link);
        gameState = new GameState(link);
        settingsState = new SettingState(link);
        State.setState(mainMenuState);

        // GAME TIME -section
        // Defining what the game's frames per second should strive for.
        int ticksPerSecond = 60;
        /* Time at last tick. Nanoseconds for precision.
         * Time assigned for this because it needs value pre game loop.
         */
        long timeThen = System.nanoTime();
        // Current time. Only defined, gets value in game loop.
        long timeNow;
        // Nanoseconds per frame is the tick rate.
        double tickTime = 1000000000 / ticksPerSecond;
        /* Will be counting the time since last tick divided by tick rate.
         * Will be reset after accummulating to 1.
         */
        double timeDiffFactor = 0;
        // Counter will reset after accumulating to a whole second.
        long secondKeeper = 0;
        // Tick count for the current second.
        int tickCount = 0;

        // Call the printout of the welcome message.
        PrintOut.printWelcome(link);

        // GAME LOOP
        while (running) {
            // GAME LOOP TIME -section

            // Assign currrent time.
            timeNow = System.nanoTime();
            // Add time difference percentage.
            timeDiffFactor += (timeNow - timeThen) / tickTime;
            // Add time difference since last tick.
            secondKeeper += timeNow - timeThen;
            // Update last tick time to this tick time.
            timeThen = timeNow;

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

                tickCount = 0;
                secondKeeper = 0;
            }
        }

        // Stop thread.
        stop();
    }

    // GETTERS
    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // THREAD
    /**
     * Start thread. Synchronized to insure actions don't overlap.
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
     * Stop thread. Synchronized to insure actions don't overlap.
     */
    public synchronized void stop() {
        // If game loop not running, don't do anything.
        if (!running) {
            return;
        }
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
