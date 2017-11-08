package worldofzuul.world;

import worldofzuul.Link;

/**
 *
 * @author Rasmus Willer
 */
public class World {
    private Link link;
    private int width, height;
    private int spawnX, spawnY;
    
    
    public World(Link link) {
        this.link = link;
    }
}
