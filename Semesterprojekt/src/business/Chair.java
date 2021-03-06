package business;

/**
 * Subclass of furniture, Chair class.
 * 
 * @author Robin
 * @author Rasmus Willer
 */
public class Chair extends Furniture {

    /**
     * Constructor for Chair class. Instantiates entity with parameters.
     * 
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal postition in room grid.
     * @param y                 int, vertical position in room grid.
     * @param currentRoom       Room, currently in this room.
     * @param imagePath         String, path of texture.
     */
    public Chair(String id,
            int x,
            int y,
            Room currentRoom,
            String imagePath){
            
        // Pass arguments to superclass.
        super(id,
                x,
                y,
                // Currently in this room.
                currentRoom);
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
    }
    
    /**
     * Override; upon interaction with furniture.
     * Can be used in the future for implementing furniture functionality.
     * 
     * @param p     Player, effect of interacting with this.
     */
    @Override
    public void onInteract(Person p) {
    }
}