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
     * Constructor for Table class. Instantiates entity with parameters.
     * 
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param currentRoom       Room, currently in this room.
     * @param canHaveItems      boolean, true if furniture can contain items.
     * @param maxAmountOfItems  int, number of items furniture can contain.
     * @param imagePath         String, file path to texture.
     */
    public Table(String id,
            int x,
            int y,
            Room currentRoom,
            boolean canHaveItems,
            int maxAmountOfItems,
            String imagePath){
        
        // Pass arguments for superclass
        super(id,
                x,
                y,
                currentRoom,
                // Item name.
                "Table",
                // Item description.
                "It might contain items!");
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
        
        // Parameters are assigned to attributes.
        this.canHaveItems = canHaveItems; 
        this.maxAmountOfItems = maxAmountOfItems;
        this.currentAmountOfItems = 0;
    }
    
    /**
     * Override; upon interaction with table.
     *
     * @param p     Person, whom is interacting with furniture.
     */
    @Override
    public void onInteract(Person p) {
    }
}
