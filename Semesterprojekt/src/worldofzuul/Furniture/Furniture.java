package worldofzuul.Furniture;

import worldofzuul.Entity;

/**
 * a class for all furniture
 * @author Robin
 */
public abstract class Furniture extends Entity {
    private String furnitureName; //name of the furniture
    private String furnitureDiscription; //description for the furniture
    
    /**
     * constructor that sets the name and description for the furniture
     * @param furnitureName
     * @param furnitureDiscription 
     */
    public Furniture(String furnitureName, String furnitureDiscription){
        //the attributes are set equal to the parimeters
        this.furnitureName = furnitureName;
        this.furnitureDiscription = furnitureDiscription;
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
    public String getFurnitureDiscription() {
        return this.furnitureDiscription;
    }
    
    /**
     * setter for furnitureDesciption
     * @param furnitureDiscription 
     */
    public void setFurnitureDiscription(String furnitureDiscription) {
        this.furnitureDiscription = furnitureDiscription;
    }

    
}
