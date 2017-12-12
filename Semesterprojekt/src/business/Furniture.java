package business;

/**
 * Subclass of Entity, Furniture class.
 * 
 * @author Robin & Rasmus Willer
 */
public abstract class Furniture extends Entity {
    // Name of furniture.
    private String furnitureName;
    // Description of furniture.
    private String furnitureDescription;
    
    /**
     * Constructor for Furniture class. Instantiates entity with parameters.
     * 
     * @param id                    String, ID of specific instantiation.
     * @param x                     int, horizontal position in room grid.
     * @param y                     int, vertical position in room grid.
     * @param currentRoom           Room, furniture is currently in this room.
     * @param furnitureName         String, name of furniture.
     * @param furnitureDescription  String, description of furniture.
     */
    public Furniture(String id,
            int x,
            int y,
            Room currentRoom,
            String furnitureName, 
            String furnitureDescription) {
        
        // Pass arguments to superclass
        super(id, x, y, currentRoom);
       
        //the attributes are set equal to the parimeters
        this.furnitureName = furnitureName;
        this.furnitureDescription = furnitureDescription;
    }
}
