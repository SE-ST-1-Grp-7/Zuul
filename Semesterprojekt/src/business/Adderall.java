package business;

/**
 * Subclass of Item, Adderall class.
 * 
 * @author Jonas Bjørnstorp
 * @author Magnus Mortensen
 * @author Robin Petersen
 * @author Rasmus Willer
 */
public class Adderall extends Item{
    private final int ENERGY_RESTORE = 70; // Energy restore value upon use.
    private final int ENERGY_CAP_INCREASE = 20; // Energy cap increase.
    // Path of texture for adderall.
    private String adderallImage = "/textures/adderall1.png";
    
    /**
     * Constructor for Adderall class.
     * 
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param currentRoom       Room, currently in this room.
     */
    public Adderall(String id,
            int x,
            int y,
            Room currentRoom) {
        
        // Pass arguments to superclass
        super(id,
                x,
                y,
                currentRoom,
                // Name of item.
                "Adderall");

        // Pass path of texture to superclass.
        super.setEntityImage(adderallImage);
    }
    
    /**
     * Override, upon use of adderall.
     * 
     * @param p     Player, player is the one using the item.
     * @return      boolean, false if added to energy, otherwise true and
     *              nothing is done. 
     */
    @Override
    public boolean use(Player p) {
        // Increase energy cap.
        p.setEnergyCap(p.getEnergyCap() + ENERGY_CAP_INCREASE);
        
        if (p.getEnergyCap() > ENERGY_RESTORE + p.getEnergy()) {
            // Replenish energy.
            p.setEnergy(ENERGY_RESTORE + p.getEnergy());

            // Otherwise set energy to max of capacity.
        } else {
            p.setEnergy(p.getEnergyCap());
        }
        return true;
    }
}