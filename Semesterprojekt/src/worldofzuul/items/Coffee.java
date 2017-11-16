
package worldofzuul.items;
import java.awt.image.BufferedImage;
import worldofzuul.People.Player;
import worldofzuul.interfaces.IConsumable;
import worldofzuul.mapAndRooms.Room;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer & Rasmus Willer
 */
public class Coffee extends Item implements IConsumable {
    private final int ENERGY_RESTORE = 15;
    
    /**
     * Constructor with all the necessary parameters for the class
     * @param x                 x coordinate
     * @param y                 y coordinate
     * @param width             pixel width
     * @param height            pixel height
     * @param currentRoom       currently in room ...
     * @param graphics          graphic image
     */
    public Coffee(int x,
                    int y,
                    int width,
                    int height,
                    Room currentRoom,
                    BufferedImage graphics) {
        
        // Pass arguments to superclass
        super(x,
                y,
                width,
                height,
                currentRoom,
                graphics,
                "Coffee",
                "A cup of coffee. Yum!",
                5);
    }
    
    /**
     * Override consume to increase player energy.
     * @param p     Player object
     */
    @Override
    public void consume(Player p) {
        p.setEnergy(ENERGY_RESTORE + p.getEnergy());
    }
    
    /**
     * Override use to let player use item.
     * @param p     Player object
     */
    @Override
    public void use(Player p) {
        consume(p);
    }
}
