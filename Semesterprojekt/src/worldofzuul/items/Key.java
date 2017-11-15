package worldofzuul.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import worldofzuul.People.Player;
import worldofzuul.mapAndRooms.Room;

/**
 *
 * @author J & Rasmus Willer
 */
public class Key extends Item {
    
    public Key(int x,
                int y,
                int width,
                int height,
                Room currentRoom,
                BufferedImage graphics,
                String itemName,
                String itemDescription,
                int weight) {
        super(x, y, width, height, currentRoom, graphics, itemName, itemDescription, weight);
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
