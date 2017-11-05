package worldofzuul;

import worldofzuul.input.KeyManager;
import worldofzuul.input.MouseManager;
import worldofzuul.mapAndRooms.RoomManager;

/**
 *
 * @author Rasmus Willer & Søren Bendtsen & Magnus Mortensen
 */
public class Link {
    private Game game;
    
    public Link(Game game) {
        this.game = game;
    }
    
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
}
