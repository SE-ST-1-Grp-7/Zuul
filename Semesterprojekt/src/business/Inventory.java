package business;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer
 */
public class Inventory {

    private final int CAPACITY = 5;
    private final int MAX_WEIGHT = 200;
    private int currentWeight;
    private ArrayList<Item> items = new ArrayList<>(CAPACITY);

    /**
     * adds an item to the inventory
     *
     * @param item
     */
    public boolean addItem(Item item) {
        // check if theres less than 10 items & less than 200 weight in the inventory
        if (items.size() < CAPACITY
                && (currentWeight + item.getWeight() <= MAX_WEIGHT)) {
            items.add(item);
            currentWeight += item.getWeight();
            return true;
        } else if (items.size() == CAPACITY) { // checks if theres less than 10 items
            System.out.println("Not enough space");
            return false;
        } else {
            System.out.println("The item is too heavy");
            return false;
        }
    }

    /**
     * get an item from an indexnumber
     *
     * @param index
     * @return
     */
    public Item getItem(int index) {
        return items.get(index);
    }

    /**
     * remove an item from the inventory
     *
     * @param item
     */
    public void removeItem(Item item) {
        currentWeight -= item.getWeight();
        items.remove(item);
    }

    /**
     * getter for the inventory
     *
     * @return
     */
    public ArrayList<Item> getInventory() {
        return items;
    }

    /**
     * prints the inventory list to the console
     */
    public void printInventory() {
        System.out.println(getInventory());
    }

    /**
     * a method to loot items on the ground
     *
     * @param i
     */
    public void lootItem(Item i) {
        addItem(i);
        System.out.println(i.getName() + " added to inventory!");
    }

    /**
     * method to drop an item
     *
     * @param index
     * @param p
     */
    public void dropItem(Item i, Player p) {
        if (p.getTempItem() == null) { //if the player has no tempItem
            removeItem(i); //remove the item from the inventory list
            i.setX(p.getX());//set the item's x to where the player is standing when dropping the item
            i.setY(p.getY());//sets the item's y just like with x
            p.setTempItem(i); //set the item you want to drop at the temp item
            //p.placeItem(); //call the placeItem that places the tempItem
        } else {
            System.out.println("You can't drop here");
        }
    }
}
