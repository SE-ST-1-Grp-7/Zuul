package worldofzuul;

import worldofzuul.input.KeyManager;
import worldofzuul.input.MouseManager;
import worldofzuul.mapAndRooms.RoomManager;
import worldofzuul.states.State;

/**
 *
 * @author Rasmus Willer & Søren Bendtsen & Magnus Mortensen
 */
public class Link {
    private Game game;
    private State gameState;
    private RoomManager rooms;
    
    public Link(Game game) {
        this.game = game;
    }
    
    // SETTERS & GETTERS
    
    public void setGameState(State gameState) {
        this.gameState = gameState;
    }
    
    public void setRoomManager(RoomManager rooms) {
        this.rooms = rooms;
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
