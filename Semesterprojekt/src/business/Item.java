package business;

import Acq.IItem;

/**
 * Subclass of Entity, Item class.
 *
 * @author Jonas Bjørstorp, Robin Petersen, Magnus Mortensen & Rasmus Willer
 */
public abstract class Item extends Entity implements IItem {

    private String itemName; // Name of item.

    /**
     * Constructor for Item class. Instantiates entity with parameters.
     *
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param currentRoom       Room, item is currently in this room.
     * @param itemName          String, name of item.
     */
    public Item(String id,
            int x,
            int y,
            Room currentRoom,
            String itemName) {

        // Pass arguments to superclass.
        super(id, x, y, currentRoom);

        // Pass path of texture to superclass.
        this.itemName = itemName;
    }

    // Apply functionality of use for this class.
    public abstract boolean use(Player p);

    /**
     * Override; getter for name of item.
     *
     * @return      String, name of item.
     */
    @Override
    public String getName() {
        return this.itemName;
    }
    
    /**
     * Override; to string method. Used to display text in GUI.
     * 
     * @return      String, return name of item.
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * Override; upon interaction with item.
     * 
     * @param p     Person, persom whom is interacting with the item.
     */
    @Override
    public void onInteract(Person p) {
        // If player, add to inventory.
        if (p instanceof Player) {
            if (((Player) p).inventory().addItem(this)) {
                getCurrentRoom().getEntities()[getY()][getX()] = null;
                ((Player) p).getEntityManager().getItemList().remove(this);
            }
        }
    }
}
