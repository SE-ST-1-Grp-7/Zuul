package worldofzuul;

import worldofzuul.mapAndRooms.RoomManager;

/**
 *
 * @author Rasmus Willer & Søren Bendtsen & Magnus Mortensen
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
    /*Der skal tilføjes flere getter og setter metoder, som kan få returneret deres kald i Link.
      På den måde forlænger Link de informationer vi kan få fat i.
     */
}
