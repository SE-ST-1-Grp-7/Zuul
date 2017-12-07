package business;

/**
 * Subclass of Item, Coffee class.
 *
 * @author Jonas Bjørstorp & Frederik Bauer & Rasmus Willer
 */
public class Coffee extends Item {

    private final int ENERGY_RESTORE = 30; // Energy restore value upon use.
    // Path of texture for coffee.
    private final String coffeeImage = "/textures/coffee1.png";

    /**
     * Constructor for Coffee class.
     *
     * @param x int, horizontal position in room grid.
     * @param y int, vertical position in room grid.
     * @param width int, pixel width of coffee.
     * @param height int, pixel height of coffee.
     * @param currentRoom Room, currently in this room.
     */
    public Coffee(int x,
            int y,
            int width,
            int height,
            Room currentRoom) {

        // Pass arguments to superclass
        super(x, // X grid position in room.
                y, // Y grid position in room.
                width, // Pixel width.
                height, // Pixel height.
                currentRoom, // Placed in this room.
                "Coffee", // Name of item.
                "A cup of coffee. Yum!", // Description of item.
                5);                          // Weight of item.

        // Pass path of texture to superclass.
        super.setEntityImage(coffeeImage);
    }

    /**
     * Override, upon use of coffee.
     *
     * @param p Player, player is the one using the item.
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
