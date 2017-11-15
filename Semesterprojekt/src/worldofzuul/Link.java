package worldofzuul;

import worldofzuul.mapAndRooms.RoomManager;

/**
 *
 * @author Rasmus Willer & Søren Bendtsen & Magnus Mortensen
 */
public class Link {
    private Game game;
    private RoomManager rooms;
    
    public Link(Game game) {
        this.game = game;
    }
    
    // SETTERS & GETTERS
    
    public void setRoomManager(RoomManager rooms) {
        this.rooms = rooms;
    }
    
    public RoomManager getRoomManager() {
        return rooms;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
