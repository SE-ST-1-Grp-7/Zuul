package business;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclass of furniture, Table class.
 * 
 * @author Robin & Rasmus Willer
 */
public class Table extends Furniture /*implements Inspectable*/{
    private boolean canHaveItems; // If true can contain items.
    private int currentAmountOfItems; // Amount of items on table.
    private int maxAmountOfItems; // Max amount of items on table.
    // List of items on table.
    private List<Item> itemsOnTable = new ArrayList<>();
     // Path to texture.
    
    /**
     * Constructor for Table class.
     * 
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of table.
     * @param height            int, pixel height of table.
     * @param currentRoom       Room, currently in this room.
     * @param canHaveItems      boolean, true if furniture can contain items.
     * @param maxAmountOfItems  int, number of items furniture can contain.
     */
    public Table(String id,
            int x,
            int y,
            Room currentRoom,
            boolean canHaveItems,
            int maxAmountOfItems, String imagePath){
        
        // Pass arguments for superclass
        super(id,
                x,
                y,
                currentRoom,
                "Table",                        // Item name.
                "It might contain items!");     // Item description.
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
        
        // Parameters are assigned to attributes.
        this.canHaveItems = canHaveItems; 
        this.maxAmountOfItems = maxAmountOfItems;
        this.currentAmountOfItems = 0;
    }
    
    /**
     * Method to place an item on table.
     * 
     * @param item      Item, give item to table.
     */
    public void placeItem(Item item){
        // If true and capable, place item in table.
        if(this.canHaveItems == true &&
                currentAmountOfItems < maxAmountOfItems){
            // Item is placed on table and added to item list of table.
            itemsOnTable.add(item);
        }
    }
    
    /**
     * Method to retrieve an item from table. (Not finished.)
     * 
     * @param item      Item, to be retrieved.
     * @return          Item, to be retrieved.
     */
    public Item takeItem(Item item){
        // Not yet fully implementet - need code to remove the item from list.
        return item; 
    }
    
    /**
     * Override, upon interaction with table.
     */
    @Override
    public void onInteract(Person p) {
        System.out.println("this is a table");
        if(currentAmountOfItems > 0) {
            System.out.println("this is what is on it ;)");
            System.out.println(itemsOnTable);
        }
    }
}
