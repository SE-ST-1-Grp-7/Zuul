package worldofzuul.Furniture;

import worldofzuul.Link;
import worldofzuul.entities.Entity;

/**
 * a class for all furniture
 * @author Robin & Rasmus Willer
 */
public abstract class Furniture extends Entity {
    private String furnitureName; //name of the furniture
    private String furnitureDescription; //description for the furniture
    
    /**
     * Constructor that sets the name and description for the furniture.
     * @param link
     * @param x
     * @param y
     * @param width
     * @param height
     * @param furnitureName
     * @param furnitureDescription 
     */
    public Furniture(Link link, float x, float y, int width, int height, String furnitureName, String furnitureDescription) {
        super(link, x, y, width, height);

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
     * @param furnitureDiscription 
     */
    public void setFurnitureDescription(String furnitureDescription) {
        this.furnitureDescription = furnitureDescription;
    }

    
}
