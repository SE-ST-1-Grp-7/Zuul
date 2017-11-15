package worldofzuul.items;

import java.awt.image.BufferedImage;
import worldofzuul.People.Player;
import worldofzuul.mapAndRooms.Room;

/**
 *
 * @author J & Rasmus Willer
 */
public class Key extends Item {
    /**
     * Constructor with all the necessary parameters for the class
     * @param x                 x coordinate
     * @param y                 y coordinate
     * @param width             pixel width
     * @param height            pixel height
     * @param currentRoom       currently in room ...
     * @param graphics          graphic image
     * @param itemName          name of the item
     * @param itemDescription   item description
     * @param weight            designated inventory weight
     */
    public Key(int x,
                int y,
                int width,
                int height,
                Room currentRoom,
                BufferedImage graphics,
                String itemName,
                String itemDescription,
                int weight) {
        
        // Arguments for superclass
        super(x,
                y,
                width,
                height,
                currentRoom,
                graphics,
                itemName,
                itemDescription,
                weight);
    }
    
    @Override
    public void use(Player p) {
    }
}
