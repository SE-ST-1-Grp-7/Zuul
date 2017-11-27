package business;

/**
 * Subclass of Item, Coffee class.
 * 
 * @author Jonas Bjørstorp & Frederik Bauer & Rasmus Willer
 */
public class Coffee extends Item {
    private final int ENERGY_RESTORE = 15; // Energy restore value upon use.
    // Path of texture for coffee.
    private String coffeeImage = "/textures/tutor2.png";
    
    /**
     * Constructor for Coffee class.
     * 
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of coffee.
     * @param height            int, pixel height of coffee.
     * @param currentRoom       Room, currently in this room.
     */
    public Coffee(int x,
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
                "Coffee",                   // Name of item.
                "A cup of coffee. Yum!",    // Description of item.
                5);                         // Weight of item.
        // Pass path of texture to superclass.
        super.setEntityImage(coffeeImage);
    }
    
    /**
     * Override, upon use of coffee.
     * 
     * @param p     Player, player is the one using the item.
     */
    @Override
    public void use(Player p) {
        p.setEnergy(ENERGY_RESTORE + p.getEnergy());
    }
}
