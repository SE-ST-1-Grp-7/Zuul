package worldofzuul.Furniture;

import worldofzuul.Entity;

/**
 * A superclass
 * @author Robin
 */
public abstract class Furniture extends Entity {
    private String furnitureName;
    private String furnitureDiscription;
    
    public Furniture(String furnitureName, String furnitureDiscription){
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
