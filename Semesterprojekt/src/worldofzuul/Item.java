/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 * Item class - the class used to instantiate an item in the game
 *
 * @author Gruppe 7
 */
public class Item {
    private String itemName;
    private String itemDescription;
    
    /**
     * Two-arg constructor
     * 
     * @param itemName
     * @param itemDescription 
     */
    public Item(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }
    /**
     * Returns the name of the Item
     * @return 
     */
    public String getName() {
        return this.itemName;
    }
    /**
     * Returns the description of the Item
     * @return 
     */
    public String getDescription() {
        return this.itemDescription;
    }
}
