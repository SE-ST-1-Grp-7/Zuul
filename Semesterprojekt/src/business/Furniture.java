package business;

/**
 * Subclass of Entity, Furniture class.
 * Does not currently implement more than its super class, but keeps the option
 * for future functionality implementation open.
 * 
 * @author Robin
 * @author Rasmus Willer
 */
public abstract class Furniture extends Entity {
    /**
     * Constructor for Furniture class. Instantiates entity with parameters.
     * 
     * @param id                    String, ID of specific instantiation.
     * @param x                     int, horizontal position in room grid.
     * @param y                     int, vertical position in room grid.
     * @param currentRoom           Room, furniture is currently in this room.
     */
    public Furniture(String id,
            int x,
            int y,
            Room currentRoom) {
        
        // Pass arguments to superclass
        super(id, x, y, currentRoom);
    }
}