package worldofzuul.states;

import java.awt.Graphics;
import worldofzuul.Link;
import worldofzuul.People.Player;
import worldofzuul.entities.EntityManager;
import worldofzuul.mapAndRooms.RoomManager;

/**
 *
 * @author Rasmus Willer
 */
public class GameState extends State {
    private Link link;
    private Player player;
    private RoomManager rooms;
    private EntityManager entityManager;
    
    public GameState(Link link) {
        super(link);
        this.link = link;
        link.setGameState(this);
        player = new Player(link, 10, 10, "Random");
        entityManager = new EntityManager(link, player);
        rooms = new RoomManager(link);
        link.setRoomManager(rooms);

        // order of the following is important
    }

    @Override
    public void tick() {
        entityManager.tick();
    }


    @Override
    public void render(Graphics g) {
        //rooms.render(g);
        entityManager.render(g);
    }
    
    // GETTERS & SETTERS
    
    public RoomManager getRoomManager() {
        return rooms;
    }
    
}
