package worldofzuul.items;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
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
    private Image adderallImage = new Image("/texture/adderall.png");
    
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
            Room currentRoom) {
        
        // Pass arguments to superclass
        super(x,
                y,
                width,
                height,
                currentRoom,
                "Adderall",
                "Adderall - Energy restore and cap increase. Yay!",
                1);
        super.setEntityImage(adderallImage);
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
