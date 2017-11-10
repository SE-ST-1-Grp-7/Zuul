package worldofzuul.items;

import worldofzuul.Link;
import worldofzuul.entities.Entity;
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
     * Item constructor with super class arguments.
     * @param link
     * @param x
     * @param y
     * @param width
     * @param height
     * @param itemName
     * @param itemDescription 
     * @param weight 
     */
    public Item(Link link,
                float x,
                float y,
                int width,
                int height,
                String itemName,
                String itemDescription,
                int weight) {
        super(link, x, y, width, height);
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.weight = weight;
    }
    
    public abstract void use(Player p);
    
    // GETTERS & SETTERS
    
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
}
