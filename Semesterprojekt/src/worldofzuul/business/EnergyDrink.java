package worldofzuul.business;

import javafx.scene.image.Image;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer & Rasmus Willer
 */
public class EnergyDrink extends Item {
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
     * Override use to let player use item.
     * @param p 
     */
    @Override
    public void use(Player p) {
        p.setEnergy(ENERGY_RESTORE + p.getEnergy());
    }
}
