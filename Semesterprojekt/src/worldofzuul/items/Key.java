package worldofzuul.items;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import worldofzuul.People.Player;
import worldofzuul.mapAndRooms.Room;

/**
 *
 * @author J & Rasmus Willer
 */
public class Key extends Item {
    private Image keyImage = new Image("/texture/key.png");
    /**
     * Constructor with all the necessary parameters for the class
     * @param x                 x coordinate
     * @param y                 y coordinate
     * @param width             pixel width
     * @param height            pixel height
     * @param currentRoom       currently in room ...
     * @param itemName          name of the item
     * @param itemDescription   item description
     * @param weight            designated inventory weight
     */
    public Key(int x,
                int y,
                int width,
                int height,
                Room currentRoom,
                String itemName,
                String itemDescription,
                int weight) {
        
        // Arguments for superclass
        super(x,
                y,
                width,
                height,
                currentRoom,
                itemName,
                itemDescription,
                weight);
        super.setEntityImage(keyImage);
    }
    
    @Override
    public void use(Player p) {
    }
}
