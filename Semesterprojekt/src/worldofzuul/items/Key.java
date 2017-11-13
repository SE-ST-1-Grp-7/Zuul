package worldofzuul.items;

import java.awt.Graphics;
import worldofzuul.People.Player;

/**
 *
 * @author J & Rasmus Willer
 */
public class Key extends Item {
    
    public Key(float x,
                float y,
                int width,
                int height,
                String itemName,
                String itemDescription,
                int weight) {
        super(x, y, width, height, itemName, itemDescription, weight);
    }
    
    @Override
    public void use(Player p) {
    }
    
    // GAME LOOP METHODS
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }
}
