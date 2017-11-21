package business;

/**
 * Subclass of Item, EnergyDrink class.
 * 
 * @author Jonas Bjørstorp & Frederik Bauer & Rasmus Willer
 */
public class EnergyDrink extends Item {
    private final int ENERGY_RESTORE = 40; // Energy restore upon use.
    // Path of tecture for energy drink.
    private String energydrinkImage = "/texture/energydrik.png";
    
    /**
     * Constructor for EnergyDrink class.
     * 
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of energy drink.
     * @param height            int, pixel height of energy drink.
     * @param currentRoom       Room, currently in this room.
     */
    public EnergyDrink(int x,
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
                "Energy drink",             // Name of item.
                "A can of Monster. Yum!",   // Description of item.
                10);                        // Weight of item.
        // Pass path of texture to superclass.
        super.setEntityImage(energydrinkImage);
    }
    
    /**
     * Override, upon use of energy drink.
     * 
     * @param p     Player, player is the one using the item.
     */
    @Override
    public void use(Player p) {
        p.setEnergy(ENERGY_RESTORE + p.getEnergy());
    }
}
