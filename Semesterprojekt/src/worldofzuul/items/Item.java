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
     * Constructor with all the necessary parameters for the class
     * @param x                 x coordinate
     * @param y                 y coordinate
     * @param width             pixel width
     * @param height            pixel height
     * @param currentRoom       currently in room ...
     * @param graphics          graphic image
     * @param itemName          name of the item
     * @param itemDescription   item description
     * @param weight            designated inventory weight
     */
    public Item(int x,
                int y,
                int width,
                int height,
                Room currentRoom,
                String itemName,
                String itemDescription,
                int weight) {
        super(x, y, width, height, currentRoom);
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
