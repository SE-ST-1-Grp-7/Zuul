/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Furniture;

import java.util.ArrayList;
import java.util.List;
import worldofzuul.items.Item;

/**
 *
 * @author Robin
 */
public class Table extends Furniture /*implements Inspectable*/{
    private boolean canHaveItems;
    private int currentAmountOfItems;
    private int maxAmountOfItems;
    private List<Item> itemsOnTable = new ArrayList<>();
    
    /**
     * constructor for a table
     * @param tableName
     * @param tableDescription
     * @param canHaveItems
     * @param maxAmountOfItems 
     */
    public Table(String tableName, String tableDescription, boolean canHaveItems, int maxAmountOfItems){
        super(tableName, tableDescription);
        this.canHaveItems = canHaveItems;
        this.maxAmountOfItems = maxAmountOfItems;
        this.currentAmountOfItems = 0;
    }
    
    /**
     * place an item on the table
     * @param item 
     */
    public void placeItem(Item item){
        if(this.canHaveItems == true && currentAmountOfItems < maxAmountOfItems){
            itemsOnTable.add(item);
        }
    }
    
    /**
     * take an item from the table
     * @param item
     * @return 
     */
    public Item takeItem(Item item){
        return item;
    }
    
}
