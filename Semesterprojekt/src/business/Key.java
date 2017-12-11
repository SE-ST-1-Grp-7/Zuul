package business;

/**
 * Subclass of Item, Key class.
 * 
 * @author J & Rasmus Willer
 */
public class Key extends Item {
    private String imagePath = "/textures/key1.png";
    
    /**
     * Constructor for Key class.
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
<<<<<<< HEAD
        super(id,
                x,
                y,
                currentRoom,
                // Name of item.
                "Key",
                // Description of item.
                "Used to open doors");
=======
        super(id, x, y, currentRoom, 
                "Key",                  // Name of item.
                "Used to open doors");   // Description of item.
               
>>>>>>> 1b6f8b0203c1059792727e980499d62b692a745d
         
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
