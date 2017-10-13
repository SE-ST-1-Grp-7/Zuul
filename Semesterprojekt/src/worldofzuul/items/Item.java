package worldofzuul.items;

import worldofzuul.Entity;

/**
 * Item class - the class used to instantiate an item in the game
 *
 * @author Gruppe 7
 */
public abstract class Item extends Entity {
    
    /**
     * Two-arg constructor
     * 
     * @param itemName
     * @param itemDescription 
     */

    // abstract methods to be implemented
    public abstract String getName();
    public abstract String getDescription();
    public abstract int getWeight();
}
