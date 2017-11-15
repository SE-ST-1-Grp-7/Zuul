package worldofzuul.items;

import java.awt.image.BufferedImage;
import worldofzuul.entities.Entity;
import worldofzuul.People.Player;
import worldofzuul.mapAndRooms.Room;

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
     * @param x
     * @param y
     * @param width
     * @param height
     * @param currentRoom
     * @param graphics
     * @param itemName
     * @param itemDescription 
     * @param weight 
     */
    public Item(int x,
                int y,
                int width,
                int height,
                Room currentRoom,
                BufferedImage graphics,
                String itemName,
                String itemDescription,
                int weight) {
        super(x, y, width, height, currentRoom, graphics);
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
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
