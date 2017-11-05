package worldofzuul.states;

import java.awt.Graphics;
import worldofzuul.Link;
import worldofzuul.mapAndRooms.RoomManager;

/**
 *
 * @author Rasmus Willer
 */
public class GameState extends State {
    
    private RoomManager rooms;
    
    public GameState(Link link) {
        super(link);
        // order of the following is important
    }

    @Override
    public void tick() {
    }


    @Override
    public void render(Graphics g) {
    }
    
}
