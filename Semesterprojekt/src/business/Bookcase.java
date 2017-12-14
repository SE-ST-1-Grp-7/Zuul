package business;

/**
 * Subclass of furniture, Bookcase class.
 * 
 * @author Robin & Rasmus Willer
 */
public class Bookcase extends Furniture {

    /**
     * Constructor for Chair class. Instantiates entity with parameters.
     * 
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal postition in room grid.
     * @param y                 int, vertical position in room grid.
     * @param currentRoom       Room, currently in this room.
     * @param imagePath         String, file path of texture.
     */
    public Bookcase(String id,
            int x,
            int y,
            Room currentRoom, String imagePath){
            
        // Pass arguments to superclass.
        super(id,
                x,
                y,
                // Placed in this room.
                currentRoom);
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
    }
    
    /**
     * Override; upon interact with bookcase.
     * Can be used in the future for implementing furniture functionality.
     * 
     * @param p     Person, person interacting with furniture.
     */
    @Override
    public void onInteract(Person p) {
    }
}