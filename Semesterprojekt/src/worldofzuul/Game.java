package worldofzuul;

import worldofzuul.gfx.Assets;
import worldofzuul.mapAndRooms.RoomManager;

/**
 * Game class - most of the game is handled from here.
 *
 * Contains: - Instantiation of RoomManager - Quit game options - Additional
 * class instantiations
 *
 * @author Rasmus Willer & Søren Bendtsen
 */
public class Game {

    // Game loop variable, initially as not running.
    private boolean running = false;
    private int width, height;
    public String title;

    // OBJECT DECLERATION
    private Link link;
    private RoomManager rooms;

    public Game() {
    }
    public void init() {

        // INITS & INSTANTIATIONS
        link = new Link(this);
        Assets.init();
    }
}
