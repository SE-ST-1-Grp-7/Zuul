package worldofzuul.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import worldofzuul.Link;
import worldofzuul.People.Player;

/**
 *
 * @author Rasmus Willer
 */
public class EntityManager {
    private Link link;
    private Player player;
    private ArrayList<Entity> entities;
    
    public EntityManager(Link link, Player p) {
        this.link   = link;
        this.player = p;
        entities = new ArrayList();
        addEntity(player);
    }
    
    // ENTITY MANAMENT METHODS
    
    public void addEntity(Entity e) {
        entities.add(e);
    }
    
    // SETTERS & GETTERS
    
    public Link getLink() {
        return link;
    }
    
    public void setLink(Link link) {
        this.link = link;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public ArrayList<Entity> getEntities() {
        return entities;
    }
    
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
