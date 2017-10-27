/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.items;

import java.util.ArrayList;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer
 */
public class Inventory {

    private final int CAPACITY = 10;
    private final int MAX_WEIGHT = 200;
    private int currentWeight;
    private ArrayList<Item> items = new ArrayList<>(CAPACITY);

/** 
 * adds an item to the inventory
 * @param item 
 */
    public void addItem(Item item) {
        // check if theres less than 10 items & less than 200 weight in the inventory
        if (items.size() < CAPACITY 
                && 
                (currentWeight + item.getWeight() <= MAX_WEIGHT)) {
            items.add(item);
            currentWeight += item.getWeight();
        } else if(items.size() == CAPACITY){ // checks if theres less than 10 items
            System.out.println("Not enough space");
        } else {
            System.out.println("The item is too heavy");
        }
    }
    public Item getItem(int index) {
        return items.get(index);  
    }
    public void removeItem(int index) {
        currentWeight-= items.get(index).getWeight();
        items.remove(index);     
    }
    
    public ArrayList<Item> getInventory(){
        return items;
    }

}
