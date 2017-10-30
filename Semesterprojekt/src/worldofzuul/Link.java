package worldofzuul;

import worldofzuul.mapAndRooms.RoomManager;

/**
 *
 * @author Rasmus Willer
 */
public class Link {
    private Game game;
    private RoomManager roomManager;
    
    public Link(Game game) {
        this.game = game;
    }
    
    public RoomManager getRoomManager() {
        return game.getRoomManager();
    }
    
}
