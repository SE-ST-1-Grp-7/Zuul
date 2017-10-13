package worldofzuul.items;

import worldofzuul.Entity;

/**
 * Item class - the class used to instantiate an item in the game
 *
 * @author Gruppe 7
 */
public abstract class Item extends Entity {
    private String itemName;
    private String itemDescription;
    private int weight;
    /**
     * Two-arg constructor
     * 
     * @param itemName
     * @param itemDescription 
     */

    // abstract methods to be implemented
    public String getName() {
        return this.itemName;
    }
    public void setName(String itemName) {
        this.itemName = itemName;
    }
}
