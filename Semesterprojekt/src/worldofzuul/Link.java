package worldofzuul;

import worldofzuul.input.KeyManager;
import worldofzuul.input.MouseManager;
import worldofzuul.mapAndRooms.RoomManager;
import worldofzuul.world.World;

/**
 *
 * @author Rasmus Willer & Søren Bendtsen & Magnus Mortensen
 */
public class Link {
    private Game game;
    private World world;
    
    public Link(Game game) {
        this.game = game;
    }
    
    // SETTERS & GETTERS
    
    public RoomManager getRoomManager() {
        return game.getRoomManager();
    }
    
    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }
    
    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }
    
    public int getWidth() {
        return game.getWidth();
    }
    
    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    public World getWorld() {
        return world;
    }
    
    public void setWorld(World world) {
        this.world = world;
    }
}
