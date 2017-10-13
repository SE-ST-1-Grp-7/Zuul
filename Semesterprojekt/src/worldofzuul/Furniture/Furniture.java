/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Furniture;

import worldofzuul.Entity;

/**
 * A superclass
 * @author Robin
 */
public abstract class Furniture extends Entity {
    private String furnitureName;
    private String furnitureDiscription;
    
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
