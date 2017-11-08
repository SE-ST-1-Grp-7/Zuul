package worldofzuul.items;

import java.awt.Graphics;
import worldofzuul.Link;
import worldofzuul.People.Player;

/**
 *
 * @author J & Rasmus Willer
 */
public class Key extends Item {
    
    public Key(Link link,
                float x,
                float y,
                int width,
                int height,
                String itemName,
                String itemDescription,
                int weight) {
        super(link, x, y, width, height, itemName, itemDescription, weight);
    }
    
    @Override
    public void use(Player p) {
        /*
        functionality needed
        pseudo code:
        check if players current room has a locked exit
        unlock the exit if so
        else do nothing
        */
    }
    
    // GAME LOOP METHODS
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }
    
    
}
