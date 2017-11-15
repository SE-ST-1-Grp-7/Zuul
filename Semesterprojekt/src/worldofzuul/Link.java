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
    
<<<<<<< HEAD
    
=======
>>>>>>> asset-room_to_entities
    public void setRoomManager(RoomManager rooms) {
        this.rooms = rooms;
    }
    
<<<<<<< HEAD
    public int getWidth() {
        return game.getWidth();
    }
    
    public int getHeight() {
        return game.getHeight();
=======
    public RoomManager getRoomManager() {
        return rooms;
>>>>>>> asset-room_to_entities
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
