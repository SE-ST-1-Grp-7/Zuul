package business;

/**
 * Subclass of Item, Key class.
 * 
 * @author Søren, Robin, Magnus, Niclas & Rasmus Willer
 */
public class Key extends Item {
    private String imagePath = "/textures/key1.png";
    
    /**
     * Constructor for Key class. Instantiates entity with parameters.
     * 
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param currentRoom       Room, currently in this room.
     */
    public Key(String id,
            int x,
            int y,
            Room currentRoom) {
        
        // Arguments for superclass
        super(id,
                x,
                y,
                currentRoom,
                // Name of item.
                "Key");
         
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
    }
    
    /**
     * Override; implement use in this class.
     * 
     * @param p     Player, player is the one using the item.
     * @return      boolean, will return true.
     */
    @Override
    public boolean use(Player p) {
        p.setHasKey(true);
        return true;
    }
}