package worldofzuul;

<<<<<<< HEAD
import worldofzuul.input.KeyManager;
import worldofzuul.input.MouseManager;
=======
import worldofzuul.gfx.Assets;
>>>>>>> asset-room_to_entities
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
<<<<<<< HEAD


=======
>>>>>>> asset-room_to_entities

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

<<<<<<< HEAD

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

=======
    public void init() {

        // INITS & INSTANTIATIONS
        link = new Link(this);
        Assets.init();
    }
>>>>>>> asset-room_to_entities
}
