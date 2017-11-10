/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Furniture;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import worldofzuul.Link;
import worldofzuul.items.Item;

/**
 *a subclass of furniture that makes a table
 * @author Robin & Rasmus Willer
 */
public class Table extends Furniture /*implements Inspectable*/{
    private boolean canHaveItems; //a boolean value that indicates weather the table can have items or not
    private int currentAmountOfItems; // the current amount of items on the table
    private int maxAmountOfItems; // the max amount of items on the table
    private List<Item> itemsOnTable = new ArrayList<>(); // a list to hold the items on the table
    
    /**
     * constructor for a table
     * @param tableName
     * @param tableDescription
     * @param canHaveItems
     * @param maxAmountOfItems 
     */
    public Table(Link link, float x, float y, int width, int height, String tableName, String tableDescription, boolean canHaveItems, int maxAmountOfItems){
        super(link, x, y, width, height, tableName, tableDescription); //a call to the super constructor - sets the name and description
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
    
    // GAME LOOP METHODS
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }
    
}
