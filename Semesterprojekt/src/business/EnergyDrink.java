package business;

/**
 * Subclass of Item, EnergyDrink class.
 * 
 * @author Jonas Bjørstorp
 * @author Søren Bendtsen
 * @author Niclas Johansen
 * @author Magnus Mortensen
 * @author Rasmus Willer
 */
public class EnergyDrink extends Item {
    // Energy restore upon use.
    private final int ENERGY_RESTORE = 30;
    // Path of tecture for energy drink.
    private final String energydrinkImage = "/textures/energydrink.png";
    
    /**
     * Constructor for EnergyDrink class. Instantiates entity with parameters.
     * 
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param currentRoom       Room, currently in this room.
     */
    public EnergyDrink(String id,
            int x,
            int y,
            Room currentRoom) {
        
        // Pass arguments to superclass
        super(id,
                x,
                y,
                // Placed in this room.
                currentRoom,
                // Name of item.
                "Energy drink");

                
        // Pass path of texture to superclass.
        super.setEntityImage(energydrinkImage);
    }
    
    /**
     * Override; upon use of energy drink.
     * 
     * @param p     Player, player is the one using the item.
     * @return      boolean, if true restore energy, otherwise false and do
     *              nothing.
     */
    @Override
    public boolean use(Player p) {
        // If it does not max out player energy, restore energy.
        if (p.getEnergyCap() > ENERGY_RESTORE + p.getEnergy()) {
            p.setEnergy(ENERGY_RESTORE + p.getEnergy());
            
        // Otherwise set energy to max of capacity.
        } else {
            p.setEnergy(p.getEnergyCap());
        }
    return true; 
    }
}