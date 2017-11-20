
package worldofzuul.business;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import worldofzuul.business.Player;
import worldofzuul.business.Room;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer & Rasmus Willer
 */
public class Coffee extends Item {
    private final int ENERGY_RESTORE = 15;
    private Image coffeeImage = new Image("/texture/coffee.png");
    
    /**
     * Constructor with all the necessary parameters for the class
     * @param x                 x coordinate
     * @param y                 y coordinate
     * @param width             pixel width
     * @param height            pixel height
     * @param currentRoom       currently in room ...
     */
    public Coffee(int x,
                    int y,
                    int width,
                    int height,
                    Room currentRoom) {
        
        // Pass arguments to superclass
        super(x,
                y,
                width,
                height,
                currentRoom,
                "Coffee",
                "A cup of coffee. Yum!",
                5);
        super.setEntityImage(coffeeImage);
    }
    
    
    /**
     * Override use to let player use item.
     * @param p     Player object
     */
    @Override
    public void use(Player p) {
        p.setEnergy(ENERGY_RESTORE + p.getEnergy());
    }
}
