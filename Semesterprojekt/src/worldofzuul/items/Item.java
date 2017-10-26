package worldofzuul.items;

import worldofzuul.Entity;
import worldofzuul.People.Player;

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

    // Getter & Setters
    public String getName() {
        return this.itemName;
    }
    public String getItemDescription() {
        return this.itemDescription;
    }
    public int getWeight() {
        return this.weight;
    }
    public void setName(String itemName) {
        this.itemName = itemName;
    }
    public void setDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public void setWeight(int Weight) {
        this.weight = weight;
    }
    public abstract void use(Player p);
}
