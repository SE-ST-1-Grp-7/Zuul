package business;

import Acq.IItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory of player, containing items picked up by the player.
 * 
 * @author Jonas Bjørstorp
 * @author Robin Petersen
 */
public class Inventory {
    private final int CAPACITY = 5;
    /* ObservableList allows connection to javafx listview directly to
       inventory. */
    private ObservableList<IItem> items = FXCollections.observableArrayList();
    
    /**
     * Retrieve the inventory capacity.
     * 
     * @return      int, capacity of inventory.
     */
    public int getCapacity(){
        return CAPACITY;
    }
    
    /**
     * Adds an item to the inventory
     *
     * @param item      Item, item to be added.
     * @return          boolean, true if space to add item, otherwise false.
     */
    public boolean addItem(Item item) {
        // Check if theres less than 5 items 
        if (items.size() < CAPACITY) {
            items.add((IItem)item);
            return true;
            
        // Checks if there's less than 5 items.
        } else {
            System.out.println("Not enough space");
            return false;
        }
    }

    /**
     * Remove an item from inventory.
     *
     * @param item      Item, item to be removed.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Getter for the inventory.
     *
     * @return      ObservableList&lt;IItem&gt;, returned list of inventory.
     */
    public ObservableList<IItem> getInventory() {
        return items;
    }

    /**
     * Drop an item.
     *
     * @param i     Item, item to drop.
     * @param p     Player, player who's inventory it will drop from.
     */
    public void dropItem(Item i, Player p) {
        // If temp item attribute is null.
        if (p.getTempItem() == null) { //if the player has no tempItem
            removeItem(i); //remove the item from the inventory list
            // Place at player's X and Y.
            i.setX(p.getX());
            i.setY(p.getY());
            // Place the item to drop at temp item attribute.
            p.setTempItem(i);
            p.getEntityManager().getItemList().add(i);
        }
    }
}