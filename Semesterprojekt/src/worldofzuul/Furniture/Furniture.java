package worldofzuul.Furniture;

import java.awt.image.BufferedImage;
import worldofzuul.entities.Entity;
import worldofzuul.mapAndRooms.Room;

/**
 * a class for all furniture
 * @author Robin & Rasmus Willer
 */
public abstract class Furniture extends Entity {
    private String furnitureName; //name of the furniture
    private String furnitureDescription; //description for the furniture
    
    /**
     * Constructor with all the necessary parameters for the class
     * @param x                     x coordinate
     * @param y                     y coordinate
     * @param width                 pixel width
     * @param height                pixel height
     * @param currentRoom           currently in room ...
     * @param graphics              graphic image
     * @param furnitureName         name of furniture
     * @param furnitureDescription  description of the furniture
     */
    public Furniture(int x,
            int y,
            int width,
            int height,
            Room currentRoom,
            BufferedImage graphics,
            String furnitureName,
            String furnitureDescription) {
        
        // Pass arguments to superclass
        super(x, y, width, height, currentRoom, graphics);

        //the attributes are set equal to the parimeters
        this.furnitureName = furnitureName;
        this.furnitureDescription = furnitureDescription;
    }
    
    // GETTERS & SETTERS
    
    /**
     * getter for furnitureName
     * @return 
     */
    public String getFurnitureName() {
        return this.furnitureName;
    }
    
    /**
     * setter for furnitureName
     * @param furnitureName 
     */
    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }
    
    /**
     * getter for furnitureDesciption
     * @return 
     */
    public String getFurnitureDescription() {
        return this.furnitureDescription;
    }
    
    /**
     * setter for furnitureDesciption
     * @param furnitureDescription 
     */
    public void setFurnitureDescription(String furnitureDescription) {
        this.furnitureDescription = furnitureDescription;
    }

    
}
