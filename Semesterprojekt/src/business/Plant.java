package business;

/**
 * Subclass of furniture, Plant class.
 * 
 * @author Robin & Rasmus Willer
 */
public class Plant extends Furniture {

    /**
     * Constructor for Chair class.
     * 
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal postition in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of chair.
     * @param height            int, pixel height of chair.
     * @param currentRoom       Room, currently in this room.
     * @param imagePath
     */
    public Plant(String id,
            int x,
            int y,
            int width,
            int height,
            Room currentRoom, String imagePath){
            
        // Pass arguments to superclass.
        super(id,
                x,
                y,
                currentRoom,
                // Item name.
                "Plant",
                // Item description.
                "Looks so lovely");
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
    }
    
    /**
     * Override; upon interaction with furniture. Does nothing.
     * 
     * @param p    Person, whom is interacting.
     */
    @Override
    public void onInteract(Person p) {
    }
    
}
