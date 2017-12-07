package business;

import Acq.IItem;

/**
 * Subclass of Entity, Item class.
 *
 * @author Gruppe 7
 */
public abstract class Item extends Entity implements IItem {

    private String itemName; // Name of item.
    private String itemDescription; // Description of item.
    private int weight; // Weight of item, for inventory application.
    private String imagePath;

    /**
     * Constructor for Item class.
     *
     * @param x int, horizontal position in room grid.
     * @param y int, vertical position in room grid.
     * @param width int, pixel width of item.
     * @param height int, pixel height of item.
     * @param currentRoom Room, item is currently in this room.
     * @param itemName String, name of item.
     * @param itemDescription String, description of item.
     * @param weight int, weight of item.
     */
    public Item(int x,
            int y,
            int width,
            int height,
            Room currentRoom,
            String itemName,
            String itemDescription,
            int weight) {

        // Pass arguments to superclass.
        super(x, y, width, height, currentRoom);

        // Pass path of texture to superclass.
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.weight = weight;
    }

    // Apply functionality of use for this class.
    public abstract boolean use(Player p);

    // GETTERS & SETTERS
    /**
     * Getter for name of item.
     *
     * @return String, name of item.
     */
    public String getName() {
        return this.itemName;
    }

    public String getImagePath() {
        return imagePath;
    }

    /**
     * Getter for item description.
     *
     * @return String, description of item.
     */
    public String getItemDescription() {
        return this.itemDescription;
    }

    /**
     * Getter for item weight.
     *
     * @return int, weight of item.
     */
    public int getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * Setter for name of item.
     *
     * @param itemName String, new name of item.
     */
    public void setName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Setter for description of item.
     *
     * @param itemDescription String, new description of item.
     */
    public void setDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * Setter for weight of item.
     *
     * @param weight int, new weight value of item.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Override, upon interaction with item.
     * @param p
     */
    @Override
    public void onInteract(Person p) {
        if (p instanceof Player) {
            if (((Player) p).inventory().addItem(this)) {
                getCurrentRoom().getEntities()[getY()][getX()] = null;
                ((Player) p).getEntityManager().getItemList().remove(this);
            }
        }
    }

}
