package worldofzuul.items;

import java.awt.image.BufferedImage;
import worldofzuul.People.Player;
import worldofzuul.interfaces.IConsumable;
import worldofzuul.mapAndRooms.Room;

/**
 *
 * @author J & Rasmus Willer
 */
public class Adderall extends Item implements IConsumable {
    private final int ENERGY_RESTORE = 70;
    private final int ENERGY_CAP_INCREASE = 20;
    
    /**
     * Constructor with all the necessary parameters for the class
     * @param x                 x coordinate
     * @param y                 y coordinate
     * @param width             pixel width
     * @param height            pixel height
     * @param currentRoom       currently in room ...
     * @param graphics          graphic image
     */
    public Adderall(int x,
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
                "Adderall",
                "Adderall - Energy restore and cap increase. Yay!",
                1);
    }

    @Override
    public void consume(Player p) {
        p.setEnergy(ENERGY_RESTORE + p.getEnergy());
        p.setEnergyCap(p.getEnergyCap() + ENERGY_CAP_INCREASE);
    }

    @Override
    public void use(Player p) {
        consume(p);
    }
}
