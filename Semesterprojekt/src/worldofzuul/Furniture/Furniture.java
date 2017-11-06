package worldofzuul.Furniture;

import worldofzuul.Entity;

/**
 * a class for all furniture
 * @author Robin
 */
public abstract class Furniture extends Entity {
    private String furnitureName; //name of the furniture
    private String furnitureDescription; //description for the furniture
    
    /**
     * constructor that sets the name and description for the furniture
     * @param furnitureName
     * @param furnitureDescription 
     */
    public Furniture(String furnitureName, String furnitureDescription){
        //the attributes are set equal to the parimeters
        this.furnitureName = furnitureName;
        this.furnitureDescription = furnitureDescription;
    }
    
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
    public void setFurnitureDescription(String furnitureDiscription) {
        this.furnitureDescription = furnitureDiscription;
    }

    
}
