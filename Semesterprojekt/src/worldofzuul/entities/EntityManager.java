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
    
    public EntityManager(Link link, Player player) {
        this.link   = link;
        this.player = player;
        entities = new ArrayList();
        addEntity(player);
    }
    
    // GAME LOOP METHODS
    
    public void tick() {
        // for loop to have the option to implement sorting of list later.
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).tick();
        }
    }
    
    public void render(Graphics g) {
        for (Entity e : entities) {
            e.render(g);
        }
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
