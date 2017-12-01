package business;

/**
 * Subclass of Item, Adderall class.
 * 
 * @author J & Rasmus Willer
 */
public class Adderall extends Item{
    private final int ENERGY_RESTORE = 70; // Energy restore value upon use.
    private final int ENERGY_CAP_INCREASE = 20; // Energy cap increase.
    // Path of texture for adderall.
    private String adderallImage = "/textures/adderall1.png";
    
    /**
     * Constructor for Adderall class.
     * 
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of adderall.
     * @param height            int, pixel height of adderall.
     * @param currentRoom       Room, currently in this room.
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
                "Adderall",                                 // Name of item.
                "Energy restore and cap increase. Yay!",    // Item description.
                1);                                          // Weight of item.

        // Pass path of texture to superclass.
        super.setEntityImage(adderallImage);
    }
    
    /**
     * Override, upon use of adderall.
     * 
     * @param p     Player, player is the one using the item.
     */
    @Override
    public void use(Player p) {
        // Increase energy cap.
        p.setEnergyCap(p.getEnergyCap() + ENERGY_CAP_INCREASE);
        // Replenish energy.
        p.setEnergy(ENERGY_RESTORE + p.getEnergy());
    }
}
