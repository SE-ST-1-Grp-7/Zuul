package business;

/**
 * Subclass of Entity, Furniture class.
 * 
 * @author Robin & Rasmus Willer
 */
public abstract class Furniture extends Entity {
    private String furnitureName; // Name of the furniture.
    private String furnitureDescription; // Description of furniture.
    private String imagePath;
    
    /**
     * Constructor for Furniture class.
     * 
     * @param x                     int, horizontal position in room grid.
     * @param y                     int, vertical position in room grid.
     * @param currentRoom           Room, furniture is currently in this room.
     * @param furnitureName         String, name of furniture.
     * @param furnitureDescription  String, description of furniture.
     */
    public Furniture(String id, int x, int y,
            Room currentRoom, String furnitureName, 
            String furnitureDescription) {
        
        // Pass arguments to superclass
        super(id, x, y, currentRoom);
       
        //the attributes are set equal to the parimeters
        this.furnitureName = furnitureName;
        this.furnitureDescription = furnitureDescription;
    }
    
    // GETTERS & SETTERS
    
    /**
     * Getter for name of furniture.
     * 
     * @return      String, name of furniture.
     */
    public String getFurnitureName() {
        return this.furnitureName;
    }
    
    /**
     * Getter for name of furniture.
     * 
     * @param furnitureName     String, set name of furniture to this String.
     */
    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }
    
    /**
     * Getter for description of furniture.
     * 
     * @return      String, description of furniture.
     */
    public String getFurnitureDescription() {
        return this.furnitureDescription;
    }
    
    /**
     * Setter for description of furniture.
     * 
     * @param furnitureDescription   String, set decsription of furniture to
     *                               this String.
     */
    public void setFurnitureDescription(String furnitureDescription) {
        this.furnitureDescription = furnitureDescription;
    }
    public String getImagePath(){
        return imagePath;
    }
}
