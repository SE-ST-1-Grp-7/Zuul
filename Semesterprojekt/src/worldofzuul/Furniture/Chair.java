/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Furniture;

/**
 *
 * @author Robin
 */
public class Chair extends Furniture {
    
    /**
     * constructor for a chair
     * @param chairName
     * @param chairDescription 
     */
    public Chair(String chairName, String chairDescription){
        super.setFurnitureName(chairName);
        super.setFurnitureDiscription(chairDescription);
    }
    
    
    
}
