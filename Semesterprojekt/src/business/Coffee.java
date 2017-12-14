package business;

/**
 * Subclass of Item, Coffee class.
 *
 * @author Jonas Bjørstorp, Søren Bendtsen, Niclas Johansen, Magnus Mortensen & Rasmus Willer
 */
public class Coffee extends Item {

    private final int ENERGY_RESTORE = 20; // Energy restore value upon use.
    // Path of texture for coffee.
    private final String coffeeImage = "/textures/coffee1.png";

    /**
     * Constructor for Coffee class. Instantiates entity with parameters.
     *
     * @param id            String, ID of specific instantiation.
     * @param x             int, horizontal position in room grid.
     * @param y             int, vertical position in room grid.
     * @param currentRoom   Room, currently in this room.
     */
    public Coffee(String id,
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
                "Coffee");

        // Pass path of texture to superclass.
        super.setEntityImage(coffeeImage);
    }

    /**
     * Override; upon use of coffee.
     *
     * @param p     Player, player is the one using the item.
     * @return      boolean, if true restored energy, otherwise false and
     *              nothing had been done.
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