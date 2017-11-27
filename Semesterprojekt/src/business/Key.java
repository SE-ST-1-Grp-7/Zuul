package business;

/**
 * Subclass of Item, Key class.
 * 
 * @author J & Rasmus Willer
 */
public class Key extends Item {
    private String keyImage = "/textures/skeleton1.png"; // Path to texture of key.
    
    /**
     * Constructor for Key class
     * 
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of key.
     * @param height            int, pixel height of key.
     * @param currentRoom       Room, currently in this room.
     */
    public Key(int x, int y, int width, int height, Room currentRoom) {
        
        // Arguments for superclass
        super(x, y, width, height, currentRoom, 
                "Key",                  // Name of item.
                "Used to open doors",   // Description of item.
                1);                     // Weight of item.
        // Pass path of texture to superclass.
        super.setEntityImage(keyImage);
    }
    
    /**
     * Override, implement use in this class.
     * 
     * @param p     Player, player is the one using the item.
     */
    @Override
    public void use(Player p) {
    }
}
