package worldofzuul.items;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import worldofzuul.People.Player;
import worldofzuul.interfaces.IConsumable;
import worldofzuul.mapAndRooms.Room;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer & Rasmus Willer
 */
public class EnergyDrink extends Item implements IConsumable {
    private final int ENERGY_RESTORE = 40;
    private Image energydrinkImage = new Image("/texture/energydrik.png");
    
    /**
     * Constructor with all the necessary parameters for the class
     * @param x                 x coordinate
     * @param y                 y coordinate
     * @param width             pixel width
     * @param height            pixel height
     * @param currentRoom       currently in room ...
     */
    public EnergyDrink(int x,
                       int y,
                       int width,
                       int height,
                       Room currentRoom) {
        
        // Pass arguments to super class
        super(x,
                y,
                width,
                height,
                currentRoom,
                "Energy drink",
                "A can of Monster. Yum!",
                10);
        super.setEntityImage(energydrinkImage);
    }
    
    /**
     * Override consume to increase player energy
     * @param p     Player object
     */
    @Override
    public void consume(Player  p) {
        p.setEnergy(ENERGY_RESTORE + p.getEnergy());
    }
    
    /**
     * Override use to let player use item.
     * @param p 
     */
    @Override
    public void use(Player p) {
        consume(p);
    }
}
