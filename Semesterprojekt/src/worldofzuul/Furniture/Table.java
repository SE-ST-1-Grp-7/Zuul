package worldofzuul.Furniture;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import worldofzuul.items.Item;
import worldofzuul.mapAndRooms.Room;

/**
 *a subclass of furniture that makes a table
 * @author Robin & Rasmus Willer
 */
public class Table extends Furniture /*implements Inspectable*/{
    private boolean canHaveItems; //a boolean value that indicates weather the table can have items or not
    private int currentAmountOfItems; // the current amount of items on the table
    private int maxAmountOfItems; // the max amount of items on the table
    private List<Item> itemsOnTable = new ArrayList<>(); // a list to hold the items on the table
    private Image tableImage = new Image("/texture/table.png");
    
    /**
     * Constructor with all the necessary parameters for the class
     * @param x                     int x coordinate
     * @param y                     int y coordinate
     * @param width                 int pixel width
     * @param height                int pixel height
     * @param currentRoom           Room currently in room ...
     * @param tableName             String Name of furniture
     * @param tableDescription      String Description of furniture
     * @param canHaveItems          boolean Can furniture contain items
     * @param maxAmountOfItems      int number of items it can contain
     */
    public Table(int x,
            int y,
            int width,
            int height,
            Room currentRoom,
            String tableName,
            String tableDescription,
            boolean canHaveItems,
            int maxAmountOfItems){
        
        // Pass arguments for superclass
        super(x,
                y,
                width,
                height,
                currentRoom,
                tableName,
                tableDescription);
        super.setEntityImage(tableImage);
        
        //the attributes are set equal to the parimeters
        this.canHaveItems = canHaveItems; 
        this.maxAmountOfItems = maxAmountOfItems;
        this.currentAmountOfItems = 0;
    }
    
    /**
     * A method to place an item on the table
     * @param item 
     */
    public void placeItem(Item item){
        //if the table can have items and if there is space on the table, then you can place the item
        if(this.canHaveItems == true && currentAmountOfItems < maxAmountOfItems){
            itemsOnTable.add(item); // the item is placed on the table and put into the list of items on the table
        }
    }
    
    /**
     * A method to take an item from the table
     * @param item
     * @return 
     */
    public Item takeItem(Item item){
        //not yet fully implementet - need code to remove the item from the table list
        return item; 
    }
}
